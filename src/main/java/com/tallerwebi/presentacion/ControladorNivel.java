package com.tallerwebi.presentacion;



import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.dominio.ServicioNivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seleccion-nivel")
public class ControladorNivel {

    private final ServicioNivel servicioNivel;
    private final Integer pruebaNivelJugador = 3;

    @Autowired
    public ControladorNivel(ServicioNivel servicioNivel){

        this.servicioNivel = servicioNivel;

    }


    //modificar
    @GetMapping()
    public ModelAndView getSelecionNivel(HttpServletRequest request, Model model) {

        var userId = request.getSession().getAttribute("userId");
        if(userId!= null){
            ModelAndView modelAndView = new ModelAndView("seleccion-nivel");

            List<NivelDTO> nivelesDto = servicioNivel.obtenerNivelesDTO(servicioNivel.obtenerTodosLosNiveles(),null);

            modelAndView.addObject("niveles", nivelesDto);

            return modelAndView;

        } else {
            return new ModelAndView("redirect:/login");
        }


    }


    @GetMapping("/seleccionarNivel/{opcionId}")
    public ModelAndView devolverNivelSeleccionado(@PathVariable(required = false) Long opcionId){ // `required = false` permite que sea nulo

        ModelAndView modelAndView = new ModelAndView("seleccion-nivel");
        List<NivelDTO> niveles = servicioNivel.obtenerNivelesDTO(servicioNivel.obtenerTodosLosNiveles(), opcionId);

        modelAndView.addObject("niveles", niveles);
        modelAndView.addObject("objetos",servicioNivel.obtenerObjetosInventario(opcionId));
        modelAndView.addObject("enemigos", servicioNivel.obtenerLosEnemigosDeUnNivel(opcionId));

        servicioNivel.seleccionarNivel(opcionId);
        return modelAndView;
    }
}


