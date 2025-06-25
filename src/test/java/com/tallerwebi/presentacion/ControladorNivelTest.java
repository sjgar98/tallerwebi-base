package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioNivel;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.NivelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpSession;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ControladorNivelTest {

    private ServicioNivel servicioNivel;
    private ServicioJugador servicioJugador;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        servicioNivel = mock(ServicioNivel.class);
        servicioJugador = mock(ServicioJugador.class);

        ControladorNivel controlador = new ControladorNivel(servicioNivel, servicioJugador);

        // Esto es necesario para que las rutas de las vistas funcionen correctamente en el test
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controlador)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void queRedirijaALoginSiNoHayUserId() throws Exception {
        mockMvc.perform(get("/seleccion-nivel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void queRendericeSeleccionNivelSiHaySesion() throws Exception {
        Long userId = 1L;
        Jugador jugadorMock = new Jugador();
        jugadorMock.setNombre("JugadorTest");

        when(servicioJugador.getJugadorActual(userId)).thenReturn(jugadorMock);
        when(servicioNivel.obtenerNivelesDTO(anyList(), isNull())).thenReturn(Collections.emptyList());
        when(servicioNivel.obtenerTodosLosNiveles()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/seleccion-nivel")
                        .sessionAttr("userId", userId))
                .andExpect(status().isOk())
                .andExpect(view().name("seleccion-nivel"))
                .andExpect(model().attributeExists("jugador"))
                .andExpect(model().attribute("jugador", jugadorMock))
                .andExpect(model().attribute("niveles", Collections.emptyList()));
    }

    @Test
    public void queDevuelvaNivelSeleccionadoCorrectamente() throws Exception {
        Long userId = 1L;
        Long nivelId = 2L;
        Jugador jugadorMock = new Jugador();
        jugadorMock.setNombre("JugadorTest");

        when(servicioJugador.getJugadorActual(userId)).thenReturn(jugadorMock);
        when(servicioNivel.obtenerTodosLosNiveles()).thenReturn(Collections.emptyList());
        when(servicioNivel.obtenerNivelesDTO(anyList(), eq(nivelId))).thenReturn(Collections.emptyList());
        when(servicioNivel.obtenerNivelPorId(nivelId)).thenReturn(new NivelDTO(1L,"NivelDto",1,5,"nivel",true));
        when(servicioNivel.obtenerObjetosInventario(nivelId)).thenReturn(Collections.emptyList());
        when(servicioNivel.obtenerLosEnemigosDeUnNivel(nivelId)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/seleccion-nivel/seleccionarNivel/" + nivelId)
                        .sessionAttr("userId", userId))
                .andExpect(status().isOk())
                .andExpect(view().name("seleccion-nivel"))
                .andExpect(model().attribute("jugador", jugadorMock))
                .andExpect(model().attribute("textoEnemigo", "Enemigos a enfrentar: "))
                .andExpect(model().attribute("textoRecompensas", "Recompensas del Nivel: "))
                .andExpect(model().attribute("nivel", "nivel"));

        verify(servicioNivel).seleccionarNivel(nivelId);
    }
}
