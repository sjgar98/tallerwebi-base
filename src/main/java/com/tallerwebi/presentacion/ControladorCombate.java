package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.ServicioCombate;
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
    private final ServicioCombate servicioCombate;


    @Autowired
    public ControladorCombate(ServicioNivel servicioNivel, ServicioJugador servicioJugador, ServicioCombate servicioCombate) {

        this.servicioNivel = servicioNivel;
        this.servicioJugador = servicioJugador;
        this.servicioCombate = servicioCombate;

    }



    @GetMapping()
    public ModelAndView getCombate(HttpServletRequest request, Model model) {


        var userId = request.getSession().getAttribute("userId");

        if(userId!= null){
            servicioCombate.setCombate(request);


            model.addAttribute("objetos", servicioJugador.getObjetosConsumibles(servicioCombate.getJugador().getId()));
            model.addAttribute("jugador", servicioCombate.getJugador());
            model.addAttribute("url", "personaje1.png");
            model.addAttribute("enemigos", servicioCombate.getEnemigos());

            return new ModelAndView("combate");
        } else {
            return new ModelAndView("redirect:/login");
        }

//
    }

    @PostMapping("/usarObjeto")
    public ModelAndView usarObjeto(@RequestParam("idObjeto") Long idObjeto, Model model){
        System.out.println("Se uso el objeto: " + idObjeto);


        servicioCombate.usarObjeto(idObjeto);


        model.addAttribute("objetos", servicioJugador.getObjetosConsumibles(servicioCombate.getJugador().getId()));
        model.addAttribute("jugador", servicioCombate.getJugador());
        model.addAttribute("enemigos", servicioCombate.getEnemigos());
        model.addAttribute("url", "personaje1.png");

        return new ModelAndView("combate");
    }
    @PostMapping("/accion")
    public ModelAndView procesarAccion(@RequestParam("enemigoIndex") int index,
                                       @RequestParam("accion") String accion,
                                       Model model, HttpServletRequest request) {



        if ("atacar".equals(accion)) {
            servicioCombate.ataqueJugador(index);
            servicioCombate.ataqueEnemigo();
        } else if ("defenderse".equals(accion)) {
            servicioCombate.defensaJugador();
        }




        if(servicioCombate.estaVivo().equals(false)){
            return derrota(request);
        }

        if (servicioCombate.gano().equals(true)){
            return victoria(request);
        }


        model.addAttribute("objetos", servicioJugador.getObjetosConsumibles(servicioCombate.getJugador().getId()));
        model.addAttribute("jugador", servicioCombate.getJugador());
        model.addAttribute("enemigos", servicioCombate.getEnemigos());
        model.addAttribute("url", "personaje1.png");

        return new ModelAndView("combate");
    }

   private ModelAndView victoria(HttpServletRequest request){

       var userId = request.getSession().getAttribute("userId");

       if (userId!=null){
           ModelAndView mav = new ModelAndView("victoria");

           mav.addObject("objetos",servicioNivel.obtenerObjetosInventario(servicioNivel.devolverNivelSeleccionado().getId()));
           mav.addObject("recompensaOro", servicioCombate.getRecompensaOro());


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
       servicioJugador.agregarOroAlJugador((Long) userId,servicioCombate.getRecompensaOro());
        servicioJugador.subirDeNivel(servicioCombate.calcularExperiencia(), (Long) userId);

       ModelAndView mav = new ModelAndView("redirect:/home");



       return mav;

   }


}

