package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidad.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/inventario")
public class ControladorInventario {
    private final ServicioJugador servicioJugador;

    @Autowired
    public ControladorInventario(ServicioJugador servicioJugador) {
        this.servicioJugador = servicioJugador;
    }

    @GetMapping()
    public ModelAndView getInventario(HttpServletRequest request) {
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            Jugador jugadorActual = this.servicioJugador.getJugadorActual((Long) userId);
            if (jugadorActual != null) {
                ModelMap model = new ModelMap();
                model.addAttribute("objetos", this.servicioJugador.getObjetosJugadorActual((Long) userId));
                model.addAttribute("emptySlots", 40 - this.servicioJugador.getObjetosJugadorActual((Long) userId).size());
                return new ModelAndView("inventario", model);
            } else {
                return new ModelAndView("redirect:/home/new");
            }
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
}
