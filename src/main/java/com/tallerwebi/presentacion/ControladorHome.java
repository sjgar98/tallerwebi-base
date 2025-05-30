package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioHome;
import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class ControladorHome {
    private final ServicioHome servicioHome;
    private final ServicioJugador servicioJugador;

    @Autowired
    public ControladorHome(ServicioHome servicioHome, ServicioJugador servicioJugador) {
        this.servicioHome = servicioHome;
        this.servicioJugador = servicioJugador;
    }

    @GetMapping()
    public ModelAndView getHome(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        model.addAttribute("anuncios", this.servicioHome.getAnuncios());
        model.addAttribute("jugador", this.servicioJugador.getJugadorActual());
        return new ModelAndView("home", model);
    }
}
