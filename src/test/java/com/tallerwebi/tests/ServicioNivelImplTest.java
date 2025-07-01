package com.tallerwebi.tests;

import com.tallerwebi.dominio.ServicioNivelImpl;
import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.infraestructura.RepositorioNivel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioNivelImplTest {

     /*
    RepositorioNivel repositorioNivel;
    ServicioNivelImpl servicio;

    private Nivel nivel(long id) {
        Nivel n = new Nivel();
        n.setId(id);
        return n;
    }

    private Objeto objeto(long id, String nombre) {
        Objeto o = new Objeto();
        o.setId(id);
        o.setNombre(nombre);
        return o;
    }

    private Enemigo enemigo(long id, String nombre) {
        Enemigo e = new Enemigo();
        e.setId(id);
        e.setNombre(nombre);
        e.setVidaActual(100);
        e.setVidaMaxima(100);
        e.setAtaque(5);
        e.setDefensa(1);
        e.setImagenSrc("img.png");
        return e;
    }

    @BeforeEach
    void setUp() {
    repositorioNivel = mock(RepositorioNivel.class);
    servicio = new ServicioNivelImpl(repositorioNivel);
    }

    @Test
    void deberiaDevolverLosNivelesIntermediosDelRepositorio() {
        var niveles = List.of(new NivelIntermedio(), new NivelIntermedio());
        when(repositorioNivel.devolverTodosLosNivelesIntermedio()).thenReturn(niveles);

        List<NivelIntermedio> resultado = servicio.obtenerNivelesIntermedios();

        assertSame(niveles, resultado);
        verify(repositorioNivel).devolverTodosLosNivelesIntermedio();
    }


    /*
    @Test
    void deberiaMapearObjetosDeUnNivelAObjetosInventarioConJugadorNull() {
        long idNivel = 7L;
        var objeto1 = objeto(1, "Botella");
        var objeto2 = objeto(2, "Pocion");

        when(repositorioNivel.devolverTodosLosObjetosDeUnNivel(idNivel))
                .thenReturn(List.of(objeto1, objeto2));

        List<Objeto> lista = servicio.obtenerObjetosDeUnNivel(idNivel);

        assertEquals(2, lista.size());
        assertEquals(objeto1, lista.get(0).getObjeto());
        assertNull(lista.get(0).getJugador());   // jugador debe estar a null
        verify(repositorioNivel).devolverTodosLosObjetosDeUnNivel(idNivel);
    }



    @Test
    void deberiaObtenerTodosLosNivelesDelRepositorio() {
        var niveles = List.of(nivel(1), nivel(2));
        when(repositorioNivel.devolverTodosLosNiveles()).thenReturn(niveles);

        assertEquals(niveles, servicio.obtenerTodosLosNiveles());
        verify(repositorioNivel).devolverTodosLosNiveles();
    }


    @Test
    void deberiaObtenerLosEnemigosDeUnNivelDelRepositorio() {
        long idNivel = 3L;
        var enemigos = List.of(enemigo(10, "Goblin"));
        when(repositorioNivel.devolverTodosLosEnemigosDeUnNivel(idNivel)).thenReturn(enemigos);

        assertEquals(enemigos, servicio.obtenerLosEnemigosDeUnNivel(idNivel));
        verify(repositorioNivel).devolverTodosLosEnemigosDeUnNivel(idNivel);
    }


    @Test
    void deberiaMapearEnemigosAEnemigosDto() {
        var e = enemigo(55, "Orco");
        List<EnemigoDTO> dto = servicio.obtenerEnemigosDto(List.of(e));

        assertEquals(1, dto.size());
        EnemigoDTO d = dto.get(0);
        assertEquals(e.getId(), d.getId());
        assertEquals("Orco", d.getNombre());
        assertEquals(100, d.getVidaMaxima());
    }

    @Test
    void seleccionarNivelDebeMarcarNivelComoSeleccionadoYLiberarAnterior() {

        Nivel n1 = nivel(1); Nivel n2 = nivel(2);
        when(repositorioNivel.devolverNivelPorId(1L)).thenReturn(n1);
        when(repositorioNivel.devolverNivelPorId(2L)).thenReturn(n2);
        when(repositorioNivel.devolverTodosLosNiveles()).thenReturn(List.of(n1, n2));


        servicio.seleccionarNivel(1L);
        assertTrue(n1.getSeleccionado());
        assertSame(n1, servicio.devolverNivelSeleccionado());


        servicio.seleccionarNivel(2L);
        assertFalse(n1.getSeleccionado());
        assertTrue(n2.getSeleccionado());
        assertSame(n2, servicio.devolverNivelSeleccionado());
    }

    @Test
    void seleccionarNivelConIdInexistenteNoCambiaEstado() {
        when(repositorioNivel.devolverTodosLosNiveles()).thenReturn(emptyList());
        when(repositorioNivel.devolverNivelPorId(99L)).thenReturn(null);

        servicio.seleccionarNivel(99L);  // nada sucede

        assertNull(servicio.devolverNivelSeleccionado());
    }
      */
}