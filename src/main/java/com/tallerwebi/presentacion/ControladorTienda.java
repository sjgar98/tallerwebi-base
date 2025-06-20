package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioTienda;
import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.dominio.excepcion.DineroInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/tienda")
public class ControladorTienda {
    private final ServicioTienda servicioTienda;
    private final ServicioJugador servicioJugador;

    @Autowired
    public ControladorTienda(ServicioTienda servicioTienda, ServicioJugador servicioJugador) {
        this.servicioTienda = servicioTienda;
        this.servicioJugador = servicioJugador;
    }

    @GetMapping()
    public ModelAndView mostrarTienda(HttpServletRequest request) {
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            Jugador jugador = servicioJugador.getJugadorActual((Long) userId);
            List<Objeto> productos = servicioTienda.obtenerProductosDisponibles();
            List<ObjetoInventario> inventario = servicioJugador.getObjetosJugador(jugador);
            ModelMap model = new ModelMap();
            model.addAttribute("productos", productos);
            model.addAttribute("saldo", jugador.getDinero());
            model.addAttribute("inventario", inventario);
            model.addAttribute("emptySlots", Constants.MAX_INVENTORY_SLOTS - inventario.size());
            return new ModelAndView("tienda", model);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("comprar")
    public ModelAndView comprarProducto(@RequestParam Long objetoId, HttpServletRequest request) {
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            try {
                Jugador jugador = servicioJugador.getJugadorActual((Long) userId);
                Objeto objetoAComprar = servicioTienda.buscarObjetoPorId(objetoId);
                servicioJugador.removerDinero(jugador, objetoAComprar.getValor());
                servicioJugador.agregarObjeto(jugador, objetoAComprar);
            } catch (DineroInsuficienteException e) {
            } catch (Exception e) {
            }
            return new ModelAndView("redirect:/tienda");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("vender")
    public ModelAndView venderObjeto(@RequestParam Long objetoInventarioId, HttpServletRequest request) {
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            try {
                Jugador jugador = servicioJugador.getJugadorActual((Long) userId);
                ObjetoInventario objetoAVender = servicioJugador.getObjetoInventarioPorId(objetoInventarioId);
                Long oroARecibir = objetoAVender.getObjeto().getValor() / 2;
                servicioJugador.removerObjeto(jugador, objetoAVender);
                servicioJugador.agregarDinero(jugador, oroARecibir);
            } catch (DineroInsuficienteException e) {
            } catch (Exception e) {
            }
            return new ModelAndView("redirect:/tienda");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
}
