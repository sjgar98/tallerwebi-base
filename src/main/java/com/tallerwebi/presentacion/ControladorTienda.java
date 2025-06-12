package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioTienda;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import com.tallerwebi.dominio.entidad.Producto;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes("jugador")
public class ControladorTienda {

    private final ServicioTienda servicioTienda;
    private final ServicioJugador servicioJugador;
    private final RepositorioObjetos repositorioObjetos;

    public ControladorTienda(ServicioTienda servicioTienda, ServicioJugador servicioJugador, RepositorioObjetos repositorioObjetos) {
        this.servicioTienda = servicioTienda;
        this.servicioJugador = servicioJugador;
        this.repositorioObjetos = repositorioObjetos;
    }

    @ModelAttribute("jugador")
    public Jugador crearJugador() {
        Jugador jugador = new Jugador();
        jugador.setDinero(500L);
        jugador.setNombre("Jugador1");
        return jugador;
    }

    @RequestMapping("/tienda")
    public ModelAndView mostrarTienda(@ModelAttribute("jugador") Jugador jugador) {
        List<Producto> productos = servicioTienda.obtenerProductosDisponibles();
        List<ObjetoInventario> inventario = servicioJugador.getObjetosJugador(jugador);
        List<Objeto> objetos = repositorioObjetos.getObjetosIniciales();

        ModelAndView mav = new ModelAndView("tienda");
        mav.addObject("productos", productos);
        mav.addObject("saldo", jugador.getDinero());
        mav.addObject("inventario", inventario);
        mav.addObject("emptySlots", 40 - inventario.size());
        mav.addObject("objetos", objetos);

        return mav;
    }

    @RequestMapping(value = "/comprar", method = RequestMethod.GET)
    public ModelAndView comprarProducto(@RequestParam String nombreProducto,
                                        @ModelAttribute("jugador") Jugador jugador) {
        Producto producto = servicioTienda.buscarProductoPorNombre(nombreProducto);
        ModelAndView mav = new ModelAndView("tienda");


        List<Producto> productos = servicioTienda.obtenerProductosDisponibles();
        List<ObjetoInventario> inventario = servicioJugador.getObjetosJugador(jugador);

        mav.addObject("productos", productos);
        mav.addObject("inventario", inventario);
        mav.addObject("emptySlots", 40 - inventario.size());

        if (jugador.puedeComprar(producto.getPrecio())) {
            jugador.debitar(producto.getPrecio());
            // Agregar objeto al inventario del jugador
            Objeto objetoComprado = repositorioObjetos.getAllObjetos().stream()
                .filter(o -> o.getId().equals(producto.getId()))
                .findFirst()
                .orElse(null);

            if (objetoComprado != null) {
                servicioJugador.agregarObjeto(jugador, objetoComprado);
                inventario = servicioJugador.getObjetosJugador(jugador); // refrescar
                mav.addObject("inventario", inventario);
                mav.addObject("emptySlots", 40 - inventario.size());
            }
            mav.addObject("mensaje", "Compra exitosa de: " + producto.getNombre());
        } else {
            mav.addObject("mensaje", "Saldo insuficiente para comprar: " + producto.getNombre());
        }

        mav.addObject("saldo", jugador.getDinero());

        return mav;
    }

    @RequestMapping(value = "/vender", method = RequestMethod.GET)
    public ModelAndView venderObjeto(@RequestParam Long objetoId,
                                     @ModelAttribute("jugador") Jugador jugador) {

        servicioJugador.venderObjeto(jugador, objetoId);

        List<Producto> productos = servicioTienda.obtenerProductosDisponibles();
        List<ObjetoInventario> inventario = servicioJugador.getObjetosJugador(jugador);

        ModelAndView mav = new ModelAndView("tienda");
        mav.addObject("productos", productos);
        mav.addObject("inventario", inventario);
        mav.addObject("emptySlots", 40 - inventario.size());
        mav.addObject("saldo", jugador.getDinero());
        mav.addObject("mensaje", "Objeto vendido");

        // actualizar jugador en sesión
        mav.addObject("jugador", jugador);

        return mav;
    }

}
