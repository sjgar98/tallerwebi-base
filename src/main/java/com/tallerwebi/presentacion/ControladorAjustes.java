package com.tallerwebi.presentacion;

import com.tallerwebi.Ajustes;
import com.tallerwebi.ServicioAjustes;
import com.tallerwebi.ServicioAjustesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ajustes")
public class ControladorAjustes {

    private final ServicioAjustes servicioAjustes = new ServicioAjustesImpl();
    @Autowired
    public ControladorAjustes(){
        servicioAjustes.guardarAjustes("#00FF2A",30,50,13,2);
        System.out.println(servicioAjustes.devolverAjustes().toString());
    }

    @GetMapping()
    public ModelAndView getSelecionNivel(HttpServletRequest request) {

        return new ModelAndView("ajustes");
    }

    @GetMapping("/verAjustesDelUsuario")
    @ResponseBody
    public Ajustes verAjustesGuardados(){

        if(servicioAjustes.devolverAjustes() == null){
            System.out.println("Se llamo a al auxiliar");
            Ajustes auxiliar_prueba = new Ajustes("#00FF2A",100,100,100,2);
            return auxiliar_prueba;
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

        System.out.println("Se llamo a ESTE metodo");
        servicioAjustes.guardarAjustes(colorFondo, vol_general, vol_musica, vol_efectos, dificultades);
        System.out.println(servicioAjustes.devolverAjustes().toString());

        return "redirect:/ajustes";
    }
}
