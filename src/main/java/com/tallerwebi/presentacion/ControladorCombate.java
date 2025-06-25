package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.ServicioCombate;
import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioNivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


            model.addAttribute("panel", "");
            model.addAttribute("objetos", servicioJugador.getObjetosConsumibles(servicioCombate.getJugador().getId()));
            model.addAttribute("jugador", servicioCombate.getJugador());
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

        model.addAttribute("panel", servicioCombate.devolverTexto());
        model.addAttribute("objetos", servicioJugador.getObjetosConsumibles(servicioCombate.getJugador().getId()));
        model.addAttribute("jugador", servicioCombate.getJugador());
        model.addAttribute("enemigos", servicioCombate.getEnemigos());
        model.addAttribute("url", "personaje.png");

        return new ModelAndView("combate");
    }
    @PostMapping("/accion")
    public ModelAndView procesarAccion(@RequestParam(name = "enemigoIndex", required = false) Integer index,
                                       @RequestParam("accion") String accion,
                                       Model model, HttpServletRequest request) {

        switch (accion) {
            case "atacar":
                if (index != null) {
                    servicioCombate.ataqueJugador(index);
                    servicioCombate.ataqueEnemigo();
                }
                break;

            case "defenderse":
                servicioCombate.defensaJugador();
                break;

            default:
                break;
        }


        if (!servicioCombate.estaVivo()) {
            return derrota(request);
        }
        if (servicioCombate.gano()) {
            return victoria(request);
        }
        model.addAttribute("panel", servicioCombate.devolverTexto());
        model.addAttribute("objetos", servicioJugador.getObjetosConsumibles(servicioCombate.getJugador().getId()));
        model.addAttribute("jugador", servicioCombate.getJugador());
        model.addAttribute("enemigos", servicioCombate.getEnemigos());
        model.addAttribute("url", "personaje.png");

        return new ModelAndView("combate");


    }

   public ModelAndView victoria(HttpServletRequest request){

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

    public ModelAndView derrota(HttpServletRequest request){
        var userId = request.getSession().getAttribute("userId");

        if (userId!=null){
            ModelAndView mav = new ModelAndView("derrota");

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

