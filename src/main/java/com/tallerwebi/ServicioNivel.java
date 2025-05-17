package com.tallerwebi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ServicioNivel {


     ArrayList<Nivel> niveles = new ArrayList<>();


     Nivel buscarNivelPorId(Integer id);
     void seleccionarNivel(Nivel nivel);
     void agregarNivel(Nivel nivel);
     Nivel devolverNivelSeleccionado();
}
