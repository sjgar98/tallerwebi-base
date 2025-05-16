package com.tallerwebi;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioNivelImpl implements ServicioNivel {

    @Override
    public String buscarDescripcionNivel(Integer id){

        return niveles.get(id).getDescripcion();
    }

    @Override
    public void agregarNivel(Nivel nivel) {
        niveles.add(nivel);
    }
}
