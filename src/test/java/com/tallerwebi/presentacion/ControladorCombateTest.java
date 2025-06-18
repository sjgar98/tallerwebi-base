package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioNivel;
import com.tallerwebi.dominio.entidad.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ControladorCombateTest {

    private ControladorCombate controladorCombate;
    private ServicioNivel mockServicioNivel;
    private ServicioJugador mockServicioJugador;
    private HttpServletRequest mockRequest;
    private HttpSession mockSession;
    private Model mockModel;

    private Combate mockCombate;
 /*

    @BeforeEach
    public void init() {
        mockServicioNivel = mock(ServicioNivel.class);
        mockServicioJugador = mock(ServicioJugador.class);
        mockRequest = mock(HttpServletRequest.class);
        mockSession = mock(HttpSession.class);
        mockModel = mock(Model.class);

        when(mockRequest.getSession()).thenReturn(mockSession);


        mockCombate = new Combate();

        controladorCombate = new ControladorCombate(mockServicioNivel, mockServicioJugador);
    }



    @Test
    public void getCombateSiUsuarioNoEstaLogueadoDeberiaRedirigirALogin() {

        when(mockSession.getAttribute("userId")).thenReturn(null);

        ModelAndView modelAndView = controladorCombate.getCombate(mockRequest, mockModel);

        assertNotNull(modelAndView);
        assertEquals("redirect:/login", modelAndView.getViewName());

    }


    @Test
    public void getCombateSiUsuarioEstaLogueadoDeberiaDevolverVistaCombateYPrepararModelo() {

        Long userId = 1L;
        Jugador jugadorMock = new Jugador();
        jugadorMock.setId(userId);
        jugadorMock.setNombre("Heroe");

        List<Enemigo> enemigosMock = Arrays.asList(new Enemigo(), new Enemigo());
        List<EnemigoDTO> enemigosDtoMock = Arrays.asList(new EnemigoDTO(), new EnemigoDTO());
        List<ObjetoInventario> recompensaObjetosMock = Arrays.asList(new ObjetoInventario(), new ObjetoInventario());
        Nivel nivelSeleccionadoMock = new Nivel();
        nivelSeleccionadoMock.setId(1L);

        when(mockSession.getAttribute("userId")).thenReturn(userId);
        when(mockServicioJugador.getJugadorActual(userId)).thenReturn(jugadorMock);
        when(mockServicioNivel.devolverNivelSeleccionado()).thenReturn(nivelSeleccionadoMock);
        when(mockServicioNivel.obtenerLosEnemigosDeUnNivel(nivelSeleccionadoMock.getId())).thenReturn(enemigosMock);
        when(mockServicioNivel.obtenerEnemigosDto(enemigosMock)).thenReturn(enemigosDtoMock);
        when(mockServicioNivel.obtenerObjetosDeUnNivel(nivelSeleccionadoMock.getId())).thenReturn(recompensaObjetosMock);

        ModelAndView modelAndView = controladorCombate.getCombate(mockRequest, mockModel);


        assertNotNull(modelAndView);
        assertEquals("combate", modelAndView.getViewName());

    }

     */

}