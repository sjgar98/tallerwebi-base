package com.tallerwebi.servicios;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface ServicioNivel {


     ArrayList<Nivel> niveles = new ArrayList<>();


     ArrayList<Nivel> devolverTodosLosNiveles();
     Nivel buscarNivelPorId(Integer id);
     void seleccionarNivel(Nivel nivel, Integer nivelJugador);
     void agregarNivel(Nivel nivel);
     Nivel devolverNivelSeleccionado();
     Nivel crearNivel(Integer id, Integer niv_min, Integer niv_enem_max, String desc, String recompensas, Boolean estado);


}
