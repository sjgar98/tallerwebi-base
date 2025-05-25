package com.tallerwebi.presentacion;

import com.tallerwebi.servicios.Ajustes;
import com.tallerwebi.servicios.ServicioAjustes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ajustes")
public class ControladorAjustes {

    private final ServicioAjustes servicioAjustes;

    @Autowired
    public ControladorAjustes(ServicioAjustes servicioAjustes){
     this.servicioAjustes =  servicioAjustes;
    }

    @GetMapping()
    public ModelAndView getAjustes(HttpServletRequest request) {

        return new ModelAndView("ajustes");
    }

    @GetMapping("/verAjustesDelUsuario")
    @ResponseBody
    public Ajustes verAjustesGuardados(){

        if(servicioAjustes.devolverAjustes() == null){
            System.out.println("Se llamo a al auxiliar");

            return servicioAjustes.devolverPredeterminado();
        } else {

            System.out.println(servicioAjustes.devolverAjustes().toString());
            Ajustes ajusteUsuario = servicioAjustes.devolverAjustes();
            return ajusteUsuario;
        }

    }

    @PostMapping("/guardarAjustes")
    public String guardarAjustesUsuario(@RequestParam String colorFondo, @RequestParam Integer vol_general,
                                        @RequestParam Integer vol_musica,
                                        @RequestParam Integer vol_efectos,
                                        @RequestParam Integer idioma,
                                        @RequestParam Integer dificultades){

        servicioAjustes.guardarAjustes(colorFondo, vol_general, vol_musica, vol_efectos, dificultades);
        System.out.println(servicioAjustes.devolverAjustes().toString());

        return "redirect:/ajustes";
    }


}
