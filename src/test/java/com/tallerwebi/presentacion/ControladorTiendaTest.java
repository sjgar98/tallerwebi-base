package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioTienda;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import com.tallerwebi.dominio.excepcion.DineroInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;

public class ControladorTiendaTest {
    private ControladorTienda controlador;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioTienda servicioTiendaMock;
    private ServicioJugador servicioJugadorMock;
    private Jugador jugadorMock;
    private List<Objeto> productosDisponiblesMock;
    private List<ObjetoInventario> objetosInventarioMock;
    private RedirectAttributes redirectAttributesMock;

    @BeforeEach
    public void init() {
        productosDisponiblesMock = List.of(
            new Objeto().setId(1L).setNombre("Pocion de Curacion Menor").setValor(100L).setComprable(true).setImagenSrc("potion-1.jpg"),
            new Objeto().setId(1L).setNombre("Pocion de Curacion").setValor(200L).setComprable(true).setImagenSrc("potion-2.jpg"),
            new Objeto().setId(1L).setNombre("Pocion de Curacion Mayor").setValor(500L).setComprable(true).setImagenSrc("potion-3.jpg")
        );
        objetosInventarioMock = List.of(new ObjetoInventario().setObjeto(productosDisponiblesMock.get(0)).setCantidad(1));
        jugadorMock = new Jugador().setId(1L).setNombre("MockJugador").setDinero(500L).setObjetos(objetosInventarioMock);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        servicioTiendaMock = mock(ServicioTienda.class);
        servicioJugadorMock = mock(ServicioJugador.class);
        controlador = new ControladorTienda(servicioTiendaMock, servicioJugadorMock);
        redirectAttributesMock = mock(RedirectAttributes.class);
    }

    @Test
    public void debieraMostrarTiendaConJugadorLoggeado() {
        givenUsuarioEstaLoggeado();
        givenHayObjetosComprables();
        ModelAndView resultado = whenAccedoAPantallaTienda();
        thenDebieraVerTienda(resultado);
    }
    private void givenUsuarioEstaLoggeado() {
        when(sessionMock.getAttribute("userId")).thenReturn(1L);
        when(servicioJugadorMock.getJugadorActual(1L)).thenReturn(jugadorMock);
    }
    private void givenHayObjetosComprables() {
        when(servicioTiendaMock.obtenerProductosDisponibles()).thenReturn(productosDisponiblesMock);
        when(servicioTiendaMock.buscarObjetoPorId(1L)).thenReturn(productosDisponiblesMock.get(0));
    }
    private ModelAndView whenAccedoAPantallaTienda() {
        return controlador.mostrarTienda(requestMock);
    }
    private void thenDebieraVerTienda(ModelAndView resultado) {
        assertThat(resultado.getViewName(), equalToIgnoringCase("tienda"));
        assertThat(((List<Objeto>) resultado.getModel().get("productos")).size(), equalTo(3));
        assertThat(resultado.getModel().get("saldo"), equalTo(jugadorMock.getDinero()));
        assertThat(((List<Objeto>) resultado.getModel().get("inventario")).size(), equalTo(0));
        assertThat(resultado.getModel().get("emptySlots"), equalTo(40));
    }

    @Test
    public void debieraPoderComprarProducto() {
        givenUsuarioEstaLoggeado();
        givenHayObjetosComprables();
        ModelAndView resultado = whenComproProducto();
        thenDebieraRedirigirATiendaAlComprar(resultado);
    }
    private ModelAndView whenComproProducto() {
        return controlador.comprarProducto(1L, requestMock, redirectAttributesMock);
    }
    private void thenDebieraRedirigirATiendaAlComprar(ModelAndView resultado) {
        verify(servicioJugadorMock).removerDinero(jugadorMock, 100L);
        verify(servicioJugadorMock).agregarObjeto(jugadorMock, productosDisponiblesMock.get(0));
        assertThat(resultado.getViewName(), equalToIgnoringCase("redirect:/tienda"));
    }

    @Test
    public void debieraPoderVenderObjeto() {
        givenUsuarioEstaLoggeado();
        givenHayObjetosComprables();
        givenHayObjetosVendibles();
        ModelAndView resultado = whenVendoProducto();
        thenDebieraRedirigirATiendaAlVender(resultado);
    }
    private void givenHayObjetosVendibles() {
        when(servicioJugadorMock.getObjetoInventarioPorId(1L)).thenReturn(objetosInventarioMock.get(0));
    }
    private ModelAndView whenVendoProducto() {
        return controlador.venderObjeto(1L, requestMock, redirectAttributesMock);
    }
    private void thenDebieraRedirigirATiendaAlVender(ModelAndView resultado) {
        verify(servicioJugadorMock).removerObjeto(jugadorMock, jugadorMock.getObjetos().get(0));
        verify(servicioJugadorMock).agregarDinero(jugadorMock, 50L);
        assertThat(resultado.getViewName(), equalToIgnoringCase("redirect:/tienda"));
    }

    @Test
    public void debieraRedirigirALoginSiUsuarioNoEstaLoggeado() {
        when(sessionMock.getAttribute("userId")).thenReturn(null);
        ModelAndView resultado = controlador.mostrarTienda(requestMock);
        assertThat(resultado.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    @Test
    public void noDebePermitirComprarSiSaldoInsuficiente() {
        givenUsuarioEstaLoggeado();
        givenHayObjetosComprables();

        // Simular que no hay saldo suficiente
        doThrow(new DineroInsuficienteException("Saldo insuficiente"))
                .when(servicioJugadorMock)
                .removerDinero(any(), anyLong());


        ModelAndView resultado = controlador.comprarProducto(1L, requestMock, redirectAttributesMock);

        // Verificar que NO se agregó el objeto porque falló el removerDinero
        verify(servicioJugadorMock, never()).agregarObjeto(any(), any());
        verify(redirectAttributesMock).addFlashAttribute("error", "No tienes suficiente dinero para comprar este objeto.");

        // Confirmar que redirige a tienda igual (esto puede variar según tu diseño)
        assertThat(resultado.getViewName(), equalToIgnoringCase("redirect:/tienda"));
    }


    @Test
    public void noDebePermitirVenderObjetoInexistente() {
        givenUsuarioEstaLoggeado();
        when(servicioJugadorMock.getObjetoInventarioPorId(1L)).thenReturn(null);

        ModelAndView resultado = controlador.venderObjeto(1L, requestMock, redirectAttributesMock);

        verify(servicioJugadorMock, never()).removerObjeto(any(), any());
        verify(servicioJugadorMock, never()).agregarDinero(any(), anyLong());
        assertThat(resultado.getViewName(), equalToIgnoringCase("redirect:/tienda"));
    }

    @Test
    public void debeMostrarTiendaVaciaSiNoHayProductos() {
        givenUsuarioEstaLoggeado();
        when(servicioTiendaMock.obtenerProductosDisponibles()).thenReturn(List.of());

        ModelAndView resultado = controlador.mostrarTienda(requestMock);

        assertThat(resultado.getViewName(), equalToIgnoringCase("tienda"));
        assertThat(((List<?>) resultado.getModel().get("productos")).size(), equalTo(0));
    }





}
