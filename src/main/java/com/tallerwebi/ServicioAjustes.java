package com.tallerwebi;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;

@Service
public interface ServicioAjustes {

    void guardarAjustes(String color, Integer vg, Integer vm, Integer ve, Integer dificultad);
    Ajustes devolverAjustes();


}
