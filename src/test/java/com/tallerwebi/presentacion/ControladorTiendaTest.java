package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioTienda;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Producto;
import com.tallerwebi.infraestructura.RepositorioObjetosImpl;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorTiendaTest {

 @Test
 void queSeMuestreLaVistaTiendaConLosProductos() {
  // Arrange
  ServicioTienda servicioTiendaMock = mock(ServicioTienda.class);
  ServicioJugador servicioJugadorMock= mock(ServicioJugador.class);
  RepositorioObjetosImpl repositorioObjetosMock = mock(RepositorioObjetosImpl.class);

  List<Producto> productosMockeados = List.of(
          new Producto(1L, "Espada Mágica", 150L, "css/imagenes/producto.png"),
          new Producto(2L, "Escudo de Hierro", 120L, "css/imagenes/escudo.jpg"),
          new Producto(3L, "Armadura de Diamante", 300L, "css/imagenes/armadura.png"),
          new Producto(4L, "Poción de Vida", 50L, "css/imagenes/curacion.png"),
          new Producto(5L, "Arco", 200L, "css/imagenes/arco.png")
  );

  when(servicioTiendaMock.obtenerProductosDisponibles()).thenReturn(productosMockeados);

  Jugador jugador = new Jugador();
  jugador.setDinero(500L);




  ControladorTienda controlador = new ControladorTienda(servicioTiendaMock,servicioJugadorMock,repositorioObjetosMock);
  // Act
  ModelAndView mav = controlador.mostrarTienda(jugador);

  // Assert
  assertEquals("tienda", mav.getViewName(), "La vista debe llamarse 'tienda'");

  List<Producto> productos = (List<Producto>) mav.getModel().get("productos");
  assertNotNull(productos, "La lista de productos no debe ser nula");
  assertEquals(5L, productos.size(), "La lista debe contener 5 productos");

  Producto primero = productos.get(0);
  assertEquals("Espada Mágica", primero.getNombre());
  assertEquals(150L, primero.getPrecio());
  assertEquals("css/imagenes/producto.png", primero.getRutaImagen());

  Object saldo = mav.getModel().get("saldo");
  assertEquals(500L, saldo, "El saldo del jugador debe estar disponible en el modelo");
 }

 @Test
 void queSeRealiceLaCompraSiHaySaldoSuficiente() {
  // Arrange
  ServicioTienda servicioTiendaMock = mock(ServicioTienda.class);
  ServicioJugador servicioJugadorMock= mock(ServicioJugador.class);
  RepositorioObjetosImpl repositorioObjetosMock = mock(RepositorioObjetosImpl.class);
  ControladorTienda controlador = new ControladorTienda(servicioTiendaMock,servicioJugadorMock, repositorioObjetosMock);

  Producto producto = new Producto(1L, "Espada Mágica", 150L, "imagen.png");
  List<Producto> productosDisponibles = List.of(producto);

  Jugador jugador = new Jugador();
  jugador.setDinero(200L); // tiene dinero suficiente

  when(servicioTiendaMock.buscarProductoPorNombre("Espada Mágica")).thenReturn(producto);
  when(servicioTiendaMock.obtenerProductosDisponibles()).thenReturn(productosDisponibles);

  // Act
  ModelAndView mav = controlador.comprarProducto("Espada Mágica", jugador);

  // Assert
  assertEquals("tienda", mav.getViewName());
  assertEquals("Compra exitosa de: Espada Mágica", mav.getModel().get("mensaje"));
  assertEquals(50L, mav.getModel().get("saldo")); // 200 - 150
  assertEquals(productosDisponibles, mav.getModel().get("productos"));
 }

 @Test
 void queNoSeRealiceLaCompraSiNoHaySaldoSuficiente() {
  // Arrange
  RepositorioObjetosImpl repositorioObjetosMock = mock(RepositorioObjetosImpl.class);
  ServicioTienda servicioTiendaMock = mock(ServicioTienda.class);
  ServicioJugador servicioJugadorMock= mock(ServicioJugador.class);
  ControladorTienda controlador = new ControladorTienda(servicioTiendaMock,servicioJugadorMock, repositorioObjetosMock);

  Producto producto = new Producto(1L, "Espada Mágica", 150L, "imagen.png");
  List<Producto> productosDisponibles = List.of(producto);

  Jugador jugador = new Jugador();
  jugador.setDinero(100L); // no tiene dinero suficiente

  when(servicioTiendaMock.buscarProductoPorNombre("Espada Mágica")).thenReturn(producto);
  when(servicioTiendaMock.obtenerProductosDisponibles()).thenReturn(productosDisponibles);

  // Act
  ModelAndView mav = controlador.comprarProducto("Espada Mágica", jugador);

  // Assert
  assertEquals("tienda", mav.getViewName());
  assertEquals("Saldo insuficiente para comprar: Espada Mágica", mav.getModel().get("mensaje"));
  assertEquals(100L, mav.getModel().get("saldo")); // saldo no cambia
  assertEquals(productosDisponibles, mav.getModel().get("productos"));
 }

 @Test
 void queSeVendaUnObjetoCorrectamente() {
  // Arrange
  ServicioTienda servicioTiendaMock = mock(ServicioTienda.class);
  ServicioJugador servicioJugadorMock = mock(ServicioJugador.class);
  RepositorioObjetosImpl repositorioObjetosMock = mock(RepositorioObjetosImpl.class);
  ControladorTienda controlador = new ControladorTienda(servicioTiendaMock, servicioJugadorMock, repositorioObjetosMock);

  Jugador jugador = new Jugador();
  jugador.setDinero(300L);

  Long objetoId = 1L;

  List<Producto> productosDisponibles = List.of(
          new Producto(1L, "Espada Mágica", 150L, "imagen.png")
  );
  when(servicioTiendaMock.obtenerProductosDisponibles()).thenReturn(productosDisponibles);

  when(servicioJugadorMock.getObjetosJugador(jugador)).thenReturn(List.of());

  // Act
  ModelAndView mav = controlador.venderObjeto(objetoId, jugador);

  // Assert
  verify(servicioJugadorMock).venderObjeto(jugador, objetoId);
  assertEquals("tienda", mav.getViewName());
  assertEquals("Objeto vendido", mav.getModel().get("mensaje"));
  assertEquals(productosDisponibles, mav.getModel().get("productos"));
  assertEquals(40, mav.getModel().get("emptySlots"));
  assertEquals(jugador.getDinero(), mav.getModel().get("saldo"));
 }





}
