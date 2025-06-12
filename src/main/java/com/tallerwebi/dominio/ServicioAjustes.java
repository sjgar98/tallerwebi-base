package com.tallerwebi.dominio;


import com.tallerwebi.dominio.entidad.Ajustes;

public interface ServicioAjustes {

    void guardarAjustes(String color, Integer vg, Integer vm, Integer ve, Integer dificultad);
    Ajustes devolverAjustes();
    Ajustes devolverPredeterminado();


}
