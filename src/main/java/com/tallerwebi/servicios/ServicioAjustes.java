package com.tallerwebi.servicios;


public interface ServicioAjustes {

    void guardarAjustes(String color, Integer vg, Integer vm, Integer ve, Integer dificultad);
    Ajustes devolverAjustes();
    Ajustes devolverPredeterminado();


}
