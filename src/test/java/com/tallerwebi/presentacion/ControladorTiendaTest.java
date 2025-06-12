package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.Producto;
import com.tallerwebi.dominio.ServicioTienda;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorTiendaTest {

 @Test
 void queSeMuestreLaVistaTiendaConLosProductos() {
  // Arrange: crear un mock del servicio
  ServicioTienda servicioTiendaMock = mock(ServicioTienda.class);

  List<Producto> productosMockeados = List.of(
          new Producto("Espada Mágica", 150, "css/imagenes/producto.png"),
          new Producto("Escudo de Hierro", 120, "css/imagenes/escudo.jpg"),
          new Producto("Armadura de Diamante", 300, "css/imagenes/armadura.png"),
          new Producto("Poción de Vida", 50, "css/imagenes/curacion.png"),
          new Producto("Arco", 200, "css/imagenes/arco.png")
  );

  when(servicioTiendaMock.obtenerProductosDisponibles()).thenReturn(productosMockeados);

  // Act: inyectar el mock en el controlador
  ControladorTienda controlador = new ControladorTienda(servicioTiendaMock);
  ModelAndView mav = controlador.mostrarTienda();

  // Assert
  assertEquals("tienda", mav.getViewName(), "La vista debe llamarse 'tienda'");

  List<Producto> productos = (List<Producto>) mav.getModel().get("productos");
  assertNotNull(productos, "La lista de productos no debe ser nula");
  assertEquals(5, productos.size(), "La lista debe contener 5 productos");

  Producto primero = productos.get(0);
  assertEquals("Espada Mágica", primero.getNombre());
  assertEquals(150, primero.getPrecio());
  assertEquals("css/imagenes/producto.png", primero.getRutaImagen());
 }
}
