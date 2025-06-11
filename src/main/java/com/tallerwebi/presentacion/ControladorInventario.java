package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
            List<ObjetoInventario> objetosJugadorActual = this.servicioJugador.getObjetosJugador(jugadorActual);
            ModelMap model = new ModelMap();
            model.addAttribute("objetos", objetosJugadorActual);
            model.addAttribute("emptySlots", 40 - objetosJugadorActual.size());
            return new ModelAndView("inventario", model);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
}
