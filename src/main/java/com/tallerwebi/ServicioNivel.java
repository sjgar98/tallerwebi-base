package com.tallerwebi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ServicioNivel {

    ArrayList<Nivel> niveles = new ArrayList<>();

    public String buscarDescripcionNivel(Integer id);

    public void agregarNivel(Nivel nivel);
}
