package com.tallerwebi;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.awt.*;

@Service
@Transactional
public class ServicioAjustesImpl implements ServicioAjustes{

    Ajustes ajustesUsuario = null;

    @Override
    public void guardarAjustes(String color, Integer vg, Integer vm, Integer ve, Integer dificultad) {
        Ajustes nuevosAjustes = new Ajustes(color,  vg,  vm,  ve,  dificultad);

        this.ajustesUsuario = nuevosAjustes;

    }

    @Override
    public Ajustes devolverAjustes() {
        return this.ajustesUsuario;
    }

}
