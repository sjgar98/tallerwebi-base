package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.ServicioJugador;
import com.tallerwebi.dominio.ServicioNivel;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.NivelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/seleccion-nivel")
public class ControladorNivel {

    private final ServicioNivel servicioNivel;
    private final ServicioJugador servicioJugador;

    @Autowired
    public ControladorNivel(ServicioNivel servicioNivel, ServicioJugador servicioJugador){
        this.servicioJugador = servicioJugador;

        this.servicioNivel = servicioNivel;

    }


    //modificar
    @GetMapping()
    public ModelAndView getSelecionNivel(HttpServletRequest request, Model model) {

        var userId = request.getSession().getAttribute("userId");
        Jugador jugadorActual = servicioJugador.getJugadorActual((Long) userId);
        if(userId!= null){
            ModelAndView modelAndView = new ModelAndView("seleccion-nivel");

            List<NivelDTO> nivelesDto = servicioNivel.obtenerNivelesDTO(servicioNivel.obtenerTodosLosNiveles(),null);


            modelAndView.addObject("nivel", " ");
            modelAndView.addObject("textoEnemigo"," ");
            modelAndView.addObject("textoRecompensas"," ");
            modelAndView.addObject("jugador", jugadorActual);
            modelAndView.addObject("niveles", nivelesDto);

            return modelAndView;

        } else {
            return new ModelAndView("redirect:/login");
        }


    }


    @GetMapping("/seleccionarNivel/{opcionId}")
    public ModelAndView devolverNivelSeleccionado(@PathVariable(required = false) Long opcionId, HttpServletRequest request){ // `required = false` permite que sea nulo



        var userId = request.getSession().getAttribute("userId");
        Jugador jugadorActual = servicioJugador.getJugadorActual((Long) userId);

        if(userId!= null){

            ModelAndView modelAndView = new ModelAndView("seleccion-nivel");
            List<NivelDTO> niveles = servicioNivel.obtenerNivelesDTO(servicioNivel.obtenerTodosLosNiveles(), opcionId);

            modelAndView.addObject("jugador", jugadorActual);
            modelAndView.addObject("niveles", niveles);
            modelAndView.addObject("textoEnemigo","Enemigos a enfrentar: ");
            modelAndView.addObject("textoRecompensas","Recompensas del Nivel: ");
            modelAndView.addObject("nivel", servicioNivel.obtenerNivelPorId(opcionId).getDescripcion());
            modelAndView.addObject("objetos",servicioNivel.obtenerObjetosInventario(opcionId));
            modelAndView.addObject("enemigos", servicioNivel.obtenerLosEnemigosDeUnNivel(opcionId));

            servicioNivel.seleccionarNivel(opcionId);
            return modelAndView;

        } else {
            return new ModelAndView("redirect:/login");
        }


    }
}


