package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Ajustes;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioAjustesImpl implements ServicioAjustes{

    Ajustes ajustesUsuario = null;

    public ServicioAjustesImpl(){
        guardarAjustes("#00FF2A",30,50,13,2);
        System.out.println(devolverAjustes().toString());
    }

    @Override
    public void guardarAjustes(String color, Integer vg, Integer vm, Integer ve, Integer dificultad) {
        Ajustes nuevosAjustes = new Ajustes(color,  vg,  vm,  ve,  dificultad);

        this.ajustesUsuario = nuevosAjustes;

    }

    @Override
    public Ajustes devolverAjustes() {
        return this.ajustesUsuario;
    }

    @Override
    public Ajustes devolverPredeterminado() {
        Ajustes auxiliar_prueba = new Ajustes("#00FF2A",100,100,100,2);
        this.ajustesUsuario = auxiliar_prueba;

        return ajustesUsuario;
    }

}
