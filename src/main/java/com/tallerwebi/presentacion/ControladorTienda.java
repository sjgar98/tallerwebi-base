package com.tallerwebi.presentacion;

import com.mercadopago.resources.Preference;
import com.tallerwebi.dominio.InterfaceServicioPago;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioTienda;
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

@Controller
@RequestMapping("/tienda")
public class ControladorTienda {
    private final ServicioTienda servicioTienda;
    private final ServicioJugador servicioJugador;
    private final InterfaceServicioPago servicioPago;

    @Autowired
    public ControladorTienda(ServicioTienda servicioTienda, ServicioJugador servicioJugador, InterfaceServicioPago servicioPago) {
        this.servicioTienda = servicioTienda;
        this.servicioJugador = servicioJugador;
        this.servicioPago = servicioPago;
    }

    @GetMapping()
    public ModelAndView mostrarTienda(HttpServletRequest request,
                                       @RequestParam(value = "monto", required = false) Integer monto,
                                       @RequestParam(value = "quantity", required = false) Integer quantity,
                                       @RequestParam(value = "error", required = false) Boolean error) {
        var userId = request.getSession().getAttribute("userId");
        if (userId != null) {
            Jugador jugador = servicioJugador.getJugadorActual((Long) userId);
            List<Objeto> productos = servicioTienda.obtenerProductosDisponibles();
            List<ObjetoInventario> inventario = servicioJugador.getObjetosJugador(jugador);
            ModelMap model = new ModelMap();
            if (quantity != null) {
                if (error == null || !error) {
                    servicioPago.procesarCompraExitosa(quantity, (Long) userId);
                    jugador = servicioJugador.getJugadorActual((Long) userId);
                    model.addAttribute("compraExitosa", true);
                    model.addAttribute("cantidadComprada", quantity);
                } else {
                    model.addAttribute("compraExitosa", false);
                }
            }
            if (Boolean.TRUE.equals(error) && !model.containsAttribute("compraExitosa")) {
                model.addAttribute("compraExitosa", false);
            }
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
    public ModelAndView comprarOro(@RequestParam(value = "monto", required = false) Integer cantidadOro,
                                   HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {
        if (cantidadOro == null || cantidadOro < 1) {
            redirectAttributes.addFlashAttribute("error", "Cantidad de oro inválida.");
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
