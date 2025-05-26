package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControladorTiendaTest {

 @Test
 void queSeMuestreLaVistaTiendaConLosProductos() {
  ControladorTienda controlador = new ControladorTienda();


  ModelAndView mav = controlador.mostrarTienda();


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