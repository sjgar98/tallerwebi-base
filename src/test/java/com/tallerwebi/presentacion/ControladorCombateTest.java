package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioCombate;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioNivel;
import com.tallerwebi.dominio.entidad.Enemigo;
import com.tallerwebi.dominio.entidad.EnemigoDTO;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.presentacion.ControladorCombate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorCombateTest {

    /*
    @Mock
    private ServicioNivel servicioNivel;
    @Mock
    private ServicioJugador servicioJugador;
    @Mock
    private ServicioCombate servicioCombate;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private Model model;

    @InjectMocks
    private ControladorCombate controladorCombate;

    private final Long userId = 1L;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userId")).thenReturn(userId);
        when(servicioCombate.getJugador()).thenReturn(new Jugador());
        when(servicioCombate.getEnemigos()).thenReturn(Collections.singletonList(new EnemigoDTO()));
    }

    @Test
    public void testGetCombateConSesion() {
        ModelAndView mav = controladorCombate.getCombate(request, model);

        assertEquals("combate", mav.getViewName());
        verify(model).addAttribute(eq("panel"), any());
        verify(model).addAttribute(eq("objetos"), any());
        verify(model).addAttribute(eq("jugador"), any());
        verify(model).addAttribute(eq("enemigos"), any());
    }

    @Test
    public void testGetCombateSinSesion() {
        when(session.getAttribute("userId")).thenReturn(null);

        ModelAndView mav = controladorCombate.getCombate(request, model);
        assertEquals("redirect:/login", mav.getViewName());
    }

    @Test
    public void testUsarObjeto() {
        ModelAndView mav = controladorCombate.usarObjeto(1L, model);

        verify(servicioCombate).usarObjeto(1L);
        verify(model).addAttribute(eq("panel"), any());
        verify(model).addAttribute(eq("objetos"), any());
        verify(model).addAttribute(eq("jugador"), any());
        verify(model).addAttribute(eq("enemigos"), any());
        verify(model).addAttribute(eq("url"), any());

        assertEquals("combate", mav.getViewName());
    }

    @Test
    public void testProcesarAccionAtacar() {
        when(servicioCombate.estaVivo()).thenReturn(true);
        when(servicioCombate.gano()).thenReturn(false);

        ModelAndView mav = controladorCombate.procesarAccion(0, "atacar", model, request);

        verify(servicioCombate).ataqueJugador(0);
        verify(servicioCombate).ataqueEnemigo();
        assertEquals("combate", mav.getViewName());
    }

    @Test
    public void testProcesarAccionDefender() {
        when(servicioCombate.estaVivo()).thenReturn(true);
        when(servicioCombate.gano()).thenReturn(false);

        ModelAndView mav = controladorCombate.procesarAccion(null, "defenderse", model, request);

        verify(servicioCombate).defensaJugador();
        assertEquals("combate", mav.getViewName());
    }

    @Test
    public void testProcesarAccionVictoria() {
        when(servicioCombate.gano()).thenReturn(true);
        ModelAndView mav = controladorCombate.procesarAccion(0, "atacar", model, request);
        assertEquals("derrota", mav.getViewName());
    }

    @Test
    public void testProcesarAccionDerrota() {
        when(servicioCombate.estaVivo()).thenReturn(false);
        ModelAndView mav = controladorCombate.procesarAccion(0, "atacar", model, request);
        assertEquals("derrota", mav.getViewName());
    }

    @Test
    public void testVictoriaConSesion() {
        when(servicioNivel.devolverNivelSeleccionado()).thenReturn(new com.tallerwebi.dominio.entidad.Nivel());
        ModelAndView mav = controladorCombate.victoria(request);
        assertEquals("victoria", mav.getViewName());
    }

    @Test
    public void testVictoriaSinSesion() {
        when(session.getAttribute("userId")).thenReturn(null);
        ModelAndView mav = controladorCombate.victoria(request);
        assertEquals("redirect:/login", mav.getViewName());
    }

    @Test
    public void testDerrotaConSesion() {
        ModelAndView mav = controladorCombate.derrota(request);
        assertEquals("derrota", mav.getViewName());
    }

    @Test
    public void testDerrotaSinSesion() {
        when(session.getAttribute("userId")).thenReturn(null);
        ModelAndView mav = controladorCombate.derrota(request);
        assertEquals("redirect:/login", mav.getViewName());
    }

    @Test
    public void testAgarrarRecompensa() {
        when(servicioNivel.devolverNivelSeleccionado()).thenReturn(new com.tallerwebi.dominio.entidad.Nivel());
        ModelAndView mav = controladorCombate.agarrarRecompensa(request);
        verify(servicioJugador).agregarObjetosAlJugador(any(), eq(userId));
        verify(servicioJugador).agregarOroAlJugador(eq(userId), anyLong());
        verify(servicioJugador).subirDeNivel(anyInt(), eq(userId));
        assertEquals("redirect:/home", mav.getViewName());
    }

     */
}
