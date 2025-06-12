package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.Producto;
import com.tallerwebi.dominio.ServicioTienda;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorTienda {

    private final ServicioTienda servicioTienda;

    public ControladorTienda(ServicioTienda servicioTienda) {
        this.servicioTienda = servicioTienda;
    }

    @RequestMapping("/tienda")
    public ModelAndView mostrarTienda() {
        List<Producto> productos = servicioTienda.obtenerProductosDisponibles();

        ModelAndView mav = new ModelAndView("tienda");
        mav.addObject("productos", productos);

        return mav;
    }
}
