package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidad.Anuncio;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
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

public class ControladorInventarioTest {
    private ControladorInventario controladorInventario;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioJugador servicioJugadorMock;
    private Jugador jugadorMock;

    @BeforeEach
    public void init() {
        jugadorMock = new Jugador().setId(1L).setNombre("MockJugador");
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        servicioJugadorMock = mock(ServicioJugador.class);
        controladorInventario = new ControladorInventario(servicioJugadorMock);
    }

    @Test
    public void debieraMostrarHomeUsuarioLoggeado() {
        givenUsuarioEstaLoggeado();
        ModelAndView resultado = whenAccedoAPantallaInventario();
        thenDebieraVerInventario(resultado);
    }
    private void givenUsuarioEstaLoggeado() {
        when(sessionMock.getAttribute("userId")).thenReturn(1L);
        when(servicioJugadorMock.getJugadorActual(1L)).thenReturn(jugadorMock);
        when(servicioJugadorMock.getObjetosJugador(jugadorMock)).thenReturn(
            List.of(
                new ObjetoInventario()
                    .setObjeto(new Objeto().setNombre("MockObjeto"))
                    .setJugador(jugadorMock)
                    .setCantidad(1)
                )
        );
    }
    private ModelAndView whenAccedoAPantallaInventario() {
        return controladorInventario.getInventario(requestMock);
    }
    private void thenDebieraVerInventario(ModelAndView resultado) {
        assertThat(resultado.getViewName(), equalToIgnoringCase("inventario"));
        assertThat(((List<ObjetoInventario>) resultado.getModel().get("objetos")).size(), equalTo(1) );
        assertThat(((List<ObjetoInventario>) resultado.getModel().get("objetos")).get(0).getObjeto().getNombre(), equalTo("MockObjeto") );
        assertThat(resultado.getModel().get("emptySlots"), equalTo(39));
    }

    @Test
    public void debieraRedireccionarALoginSinSesion() {
        givenUsuarioNoEstaLoggeado();
        ModelAndView resultado = whenAccedoAPantallaInventario();
        thenDebieraRedireccionarALogin(resultado);
    }
    private void givenUsuarioNoEstaLoggeado() {
        when(sessionMock.getAttribute("userId")).thenReturn(null);
    }
    private void thenDebieraRedireccionarALogin(ModelAndView resultado) {
        assertThat(resultado.getViewName(), equalToIgnoringCase("redirect:/login"));
    }
}
