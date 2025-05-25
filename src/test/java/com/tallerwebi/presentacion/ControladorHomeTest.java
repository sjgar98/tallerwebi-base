package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.ServicioHome;
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

public class ControladorHomeTest {
    private ControladorHome controladorHome;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioHome servicioHomeMock;
    private ServicioJugador servicioJugadorMock;

    @BeforeEach
    public void init() {
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioHomeMock = mock(ServicioHome.class);
        servicioJugadorMock = mock(ServicioJugador.class);
        controladorHome = new ControladorHome(servicioHomeMock, servicioJugadorMock);
    }

    @Test
    public void devuelveModelAndView() {
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(servicioJugadorMock.getJugadorActual()).thenReturn(new Jugador());
        ModelAndView modelAndView = controladorHome.getHome(requestMock);
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("home"));
    }
}
