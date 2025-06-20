package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioHome;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidad.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            Jugador jugadorActual = this.servicioJugador.getJugadorActual((Long) userId);
            model.addAttribute("anuncios", this.servicioHome.getAnuncios());
            model.addAttribute("jugador", jugadorActual);
            return new ModelAndView("home", model);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
}
