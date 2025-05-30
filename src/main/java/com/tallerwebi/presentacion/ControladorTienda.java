package com.tallerwebi.presentacion;


import java.util.List;
import com.tallerwebi.dominio.excepcion.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorTienda {

    @RequestMapping("/tienda")
    public ModelAndView mostrarTienda() {
        List<Producto> productos = List.of(
                new Producto("Espada Mágica", 150, "css/imagenes/producto.png"),
                new Producto("Escudo de Hierro", 120, "css/imagenes/escudo.jpg"),
                new Producto("Armadura de Diamante", 300, "css/imagenes/armadura.png"),
                new Producto("Poción de Vida", 50, "css/imagenes/curacion.png"),
                new Producto("Arco", 200, "css/imagenes/arco.png")
        );

        ModelAndView mav = new ModelAndView("tienda");
        mav.addObject("productos", productos);

        return mav;
    }
}