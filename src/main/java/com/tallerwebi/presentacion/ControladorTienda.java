package com.tallerwebi.presentacion;


import com.mercadopago.resources.Preference;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioTienda;
import com.tallerwebi.dominio.ServicioPago;
import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.dominio.excepcion.DineroInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequestMapping("/tienda")
public class ControladorTienda {
    private final ServicioTienda servicioTienda;
    private final ServicioJugador servicioJugador;
    private final ServicioPago servicioPago;

    @Autowired
    public ControladorTienda(ServicioTienda servicioTienda, ServicioJugador servicioJugador) {
        this.servicioTienda = servicioTienda;
        this.servicioJugador = servicioJugador;
        this.servicioPago = new ServicioPago();
    }

    @GetMapping()
    public ModelAndView mostrarTienda(HttpServletRequest request, @RequestParam(value = "monto", required = false) Integer monto) {
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
            if (monto != null) {
                model.addAttribute("monto", monto);
            }
            return new ModelAndView("tienda", model);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("comprar")
    public ModelAndView comprarProducto(@RequestParam Long objetoId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            try {
                Jugador jugador = servicioJugador.getJugadorActual((Long) userId);
                Objeto objetoAComprar = servicioTienda.buscarObjetoPorId(objetoId);
                servicioJugador.removerDinero(jugador, objetoAComprar.getValor());
                servicioJugador.agregarObjeto(jugador, objetoAComprar);
            } catch (DineroInsuficienteException e) {
                redirectAttributes.addFlashAttribute("error", "No tienes suficiente dinero para comprar este objeto.");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado.");
            }
            return new ModelAndView("redirect:/tienda");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("vender")
    public ModelAndView venderObjeto(@RequestParam Long objetoInventarioId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            try {
                Jugador jugador = servicioJugador.getJugadorActual((Long) userId);
                ObjetoInventario objetoAVender = servicioJugador.getObjetoInventarioPorId(objetoInventarioId);
                Long oroARecibir = objetoAVender.getObjeto().getValor() / 2;
                servicioJugador.removerObjeto(jugador, objetoAVender);
                servicioJugador.agregarDinero(jugador, oroARecibir);
            } catch (DineroInsuficienteException e) {
                redirectAttributes.addFlashAttribute("error", "No tienes suficiente dinero para vender este objeto.");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado.");
            }
            return new ModelAndView("redirect:/tienda");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }


    @GetMapping("/comprar-oro")
    public ModelAndView comprarOro(
            @RequestParam(value = "monto", required = false) Integer cantidadOro,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        if (cantidadOro == null || cantidadOro < 1) {
            redirectAttributes.addFlashAttribute("error", "Por favor ingresa una cantidad válida de oro.");
            return new ModelAndView("redirect:/tienda");
        }

        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        try {
            Preference preferencia = servicioPago.generarPreferencia(cantidadOro, (Long) userId);
            String initPoint = preferencia.getInitPoint();

            if (initPoint == null || initPoint.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "No se pudo generar el link de pago.");
                return new ModelAndView("redirect:/tienda");
            }

            return new ModelAndView("redirect:" + initPoint);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al procesar el pago: " + e.getMessage());
            return new ModelAndView("redirect:/tienda");
        }
    }
}





































   /* @GetMapping("/compra-exitosa")
    public ModelAndView compraExitosa(@RequestParam("oro") int oro, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Object userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            Jugador jugador = servicioJugador.getJugadorActual((Long) userId);
            servicioJugador.agregarDinero(jugador, (long) oro);
            redirectAttributes.addFlashAttribute("exito", "¡Compra exitosa! Recibiste " + oro + " de oro.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo confirmar la compra.");
        }
        return new ModelAndView("redirect:/tienda");
    }


    @GetMapping("/compra-fallida")
    public ModelAndView compraFallida(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "La compra fue cancelada o falló.");
        return new ModelAndView("redirect:/tienda");
    }

    @GetMapping("/compra-pendiente")
    public ModelAndView compraPendiente(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("info", "El pago está pendiente. Verificá tu cuenta de MercadoPago.");
        return new ModelAndView("redirect:/tienda");
    }


}*/
