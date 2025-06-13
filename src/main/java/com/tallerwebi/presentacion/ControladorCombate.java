package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioNivel;
import com.tallerwebi.dominio.entidad.Combate;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/combate")
public class ControladorCombate {




    private final ServicioNivel servicioNivel;
    private final ServicioJugador servicioJugador;

    private Combate combate = new Combate();


    @Autowired
    public ControladorCombate(ServicioNivel servicioNivel, ServicioJugador servicioJugador) {

        this.servicioNivel = servicioNivel;
        this.servicioJugador = servicioJugador;


    }



    @GetMapping()
    public ModelAndView getCombate(HttpServletRequest request, Model model) {


        var userId = request.getSession().getAttribute("userId");

        if(userId!= null){
            combate.setJugador(this.servicioJugador.getJugadorActual((Long) userId));
            combate.setEnemigos(servicioNivel.obtenerEnemigosDto(servicioNivel.obtenerLosEnemigosDeUnNivel(servicioNivel.devolverNivelSeleccionado().getId())));
            combate.setRecompensaObjetos(servicioNivel.obtenerObjetosInventario(servicioNivel.devolverNivelSeleccionado().getId()));
            combate.setRecompensaOro(100L);


            model.addAttribute("jugador", combate.getJugador());
            model.addAttribute("url", "personaje1.png");
            model.addAttribute("enemigos", combate.getEnemigos());

            return new ModelAndView("combate");
        } else {
            return new ModelAndView("redirect:/login");
        }


    }

    @PostMapping("/accion")
    public ModelAndView procesarAccion(@RequestParam("enemigoIndex") int index,
                                       @RequestParam("accion") String accion,
                                       Model model, HttpServletRequest request) {



        if ("atacar".equals(accion)) {
            combate.ataqueJugador(combate.getJugador(), index);
            combate.ataqueEnemigo(combate.getJugador());
        } else if ("defenderse".equals(accion)) {

        }




        if(combate.estaVivo().equals(false)){
            return derrota(request);
        }

        if (combate.gano().equals(true)){
            return victoria(request);
        }



        model.addAttribute("jugador", combate.getJugador());
        model.addAttribute("enemigos", combate.getEnemigos());
        model.addAttribute("url", "personaje1.png");

        return new ModelAndView("combate");
    }

   private ModelAndView victoria(HttpServletRequest request){

       var userId = request.getSession().getAttribute("userId");

       if (userId!=null){
           ModelAndView mav = new ModelAndView("victoria");

           mav.addObject("objetos",servicioNivel.obtenerObjetosInventario(servicioNivel.devolverNivelSeleccionado().getId()));
           mav.addObject("recompensaOro", combate.getRecompensaOro());


           return mav;

       } else {
           return new ModelAndView("redirect:/login");
       }


   }

    private ModelAndView derrota(HttpServletRequest request){
        var userId = request.getSession().getAttribute("userId");

        if (userId!=null){
            ModelAndView mav = new ModelAndView("gameOver");

            return mav;

        } else {
            return new ModelAndView("redirect:/login");
        }



    }

   @GetMapping("/agararRecompensa")
   public ModelAndView agarrarRecompensa(HttpServletRequest request){

       var userId = request.getSession().getAttribute("userId");

       servicioJugador.agregarObjetosAlJugador(servicioNivel.obtenerObjetosDeUnNivel(servicioNivel.devolverNivelSeleccionado().getId()),(Long) userId);
       servicioJugador.agregarOroAlJugador((Long) userId,combate.getRecompensaOro());

       ModelAndView mav = new ModelAndView("redirect:/home");



       return mav;

   }


}

