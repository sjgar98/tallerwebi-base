package com.tallerwebi.presentacion;


import com.tallerwebi.servicios.Nivel;
import com.tallerwebi.servicios.ServicioNivel;
import com.tallerwebi.servicios.ServicioNivelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/seleccion-nivel")
public class ControladorNivel {

    private final ServicioNivel servicioNivel;
    private final Integer pruebaNivelJugador = 3;

    @Autowired
    public ControladorNivel(ServicioNivel servicioNivel ){

        this.servicioNivel = servicioNivel;





    }

    @GetMapping("/seleccionarNivel/{opcionId}")
    @ResponseBody
    public Nivel obtenerDescripcion(@PathVariable Integer opcionId) {
        Nivel nivel = null;
        try {
            nivel = servicioNivel.buscarNivelPorId(opcionId);
            servicioNivel.seleccionarNivel(nivel, pruebaNivelJugador);
        } catch (Exception e) {

            System.err.println("Error al obtener/seleccionar nivel: " + e.getMessage());
            return null;
        }
        return nivel;

    }



    @GetMapping()
    public ModelAndView getSelecionNivel(HttpServletRequest request, Model model) {


        ArrayList<Nivel> niveles = servicioNivel.devolverTodosLosNiveles();
        System.out.println(niveles.toString());
        model.addAttribute("niveles", niveles);
        return new ModelAndView("seleccion-nivel");
    }

}
