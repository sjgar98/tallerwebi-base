package com.tallerwebi.presentacion;


import com.tallerwebi.Nivel;
import com.tallerwebi.ServicioNivel;
import com.tallerwebi.ServicioNivelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/seleccion-nivel")
public class ControladorNivel {

    private final ServicioNivel servicioNivel;
    private final Integer pruebaNivelJugador = 3;

    @Autowired
    public ControladorNivel( ){

        this.servicioNivel = new ServicioNivelImpl();

        Nivel prueba = new Nivel(1,0,3,"Este es el primer nivel","Monedas",true);
        Nivel prueba2 = new Nivel(2,1,5,"Este es el segundo nivel","Monedas",true);
        Nivel prueba3 = new Nivel(3,5,10,"Este es el tercer nivel","Monedas",true);
        servicioNivel.agregarNivel(prueba);
        servicioNivel.agregarNivel(prueba2);
        servicioNivel.agregarNivel(prueba3);
        System.out.println(servicioNivel.buscarNivelPorId(1));



    }

    @GetMapping("/seleccionarNivel/{opcionId}")
    @ResponseBody
    public String obtenerDescripcion(@PathVariable Integer opcionId) {
        String descripcion = "";
        try {
            descripcion = servicioNivel.buscarNivelPorId(opcionId).getDescripcion();
            servicioNivel.seleccionarNivel(servicioNivel.buscarNivelPorId(opcionId), pruebaNivelJugador);
        } catch (Exception e) {

            System.err.println("Error al obtener/seleccionar nivel: " + e.getMessage());
            return "Error: Nivel no encontrado o problema interno.";
        }
        return descripcion;

    }


    @GetMapping()
    public ModelAndView getSelecionNivel(HttpServletRequest request) {

        return new ModelAndView("seleccion-nivel");
    }

}
