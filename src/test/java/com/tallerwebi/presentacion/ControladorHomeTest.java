package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Anuncio;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.ServicioHome;
import com.tallerwebi.dominio.ServicioJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
        when(requestMock.getSession()).thenReturn(sessionMock);
        servicioHomeMock = mock(ServicioHome.class);
        servicioJugadorMock = mock(ServicioJugador.class);
        controladorHome = new ControladorHome(servicioHomeMock, servicioJugadorMock);
    }

    @Test
    public void debieraMostrarHomeUsuarioLoggeado() {
        givenUsuarioEstaLoggeado();
        ModelAndView resultado = whenAccedoAPantallaHome();
        thenDebieraVerHomeConAnunciosYEstadisticas(resultado);
    }
    private void givenUsuarioEstaLoggeado() {
        when(sessionMock.getAttribute("userId")).thenReturn(1L);
        when(servicioJugadorMock.getJugadorActual(1L)).thenReturn(new Jugador().setNombre("MockPlayer"));
        when(servicioHomeMock.getAnuncios()).thenReturn(List.of(new Anuncio().setTitle("MockAnuncio")));
    }
    private ModelAndView whenAccedoAPantallaHome() {
        return controladorHome.getHome(requestMock);
    }
    private void thenDebieraVerHomeConAnunciosYEstadisticas(ModelAndView resultado) {
        assertThat(resultado.getViewName(), equalToIgnoringCase("home"));
        assertThat( ((Jugador) resultado.getModel().get("jugador")).getNombre(), equalTo("MockPlayer") );
    }

    @Test
    public void debieraRedireccionarALoginSinSesion() {
        givenUsuarioNoEstaLoggeado();
        ModelAndView resultado = whenAccedoAPantallaHome();
        thenDebieraRedireccionarALogin(resultado);
    }
    private void givenUsuarioNoEstaLoggeado() {
        when(sessionMock.getAttribute("userId")).thenReturn(null);
    }
    private void thenDebieraRedireccionarALogin(ModelAndView resultado) {
        assertThat(resultado.getViewName(), equalToIgnoringCase("redirect:/login"));
    }
}
