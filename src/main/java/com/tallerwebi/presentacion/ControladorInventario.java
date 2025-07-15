package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.entidad.Constants;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import com.tallerwebi.dominio.excepcion.ObjetoNoEncontrado;
import com.tallerwebi.dominio.excepcion.ObjetoNoEquipable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            model.addAttribute("jugador", jugadorActual);
            model.addAttribute("objetos", objetosJugadorActual);
            model.addAttribute("ataqueAdicional", servicioJugador.getAtaqueAdicional(jugadorActual));
            model.addAttribute("defensaAdicional", servicioJugador.getDefensaAdicional(jugadorActual));
            model.addAttribute("emptySlots", Constants.MAX_INVENTORY_SLOTS - objetosJugadorActual.size());
            model.addAttribute("equipoArma", servicioJugador.getObjetoEquipadoPorTipo(jugadorActual, "Arma"));
            model.addAttribute("equipoArmadura", servicioJugador.getObjetoEquipadoPorTipo(jugadorActual, "Armadura"));
            model.addAttribute("equipoAccesorio", servicioJugador.getObjetoEquipadoPorTipo(jugadorActual, "Accesorio"));
            return new ModelAndView("inventario", model);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("/equipar")
    public ModelAndView equiparObjeto(@RequestParam Long objetoInventarioId, HttpServletRequest request) {
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            try {
                Jugador jugadorActual = this.servicioJugador.getJugadorActual((Long) userId);
                this.servicioJugador.equiparObjeto(jugadorActual, objetoInventarioId);
            } catch (ObjetoNoEquipable e) {
            } catch (ObjetoNoEncontrado e) {
            }
            return new ModelAndView("redirect:/inventario");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
}
