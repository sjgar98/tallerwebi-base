package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.ServicioJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorInventarioTest {
    private ControladorInventario controladorInventario;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioJugador servicioJugadorMock;

    @BeforeEach
    public void init() {
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioJugadorMock = mock(ServicioJugador.class);
        controladorInventario = new ControladorInventario(servicioJugadorMock);
    }

    @Test
    public void devuelveModelAndView() {
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(servicioJugadorMock.getJugadorActual(1L)).thenReturn(new Jugador());
        ModelAndView modelAndView = controladorInventario.getInventario(requestMock);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("inventario"));
    }
}
