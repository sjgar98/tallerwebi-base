package com.tallerwebi;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioNivelImpl implements ServicioNivel {

    Nivel nivelSeleccionado = null;

    @Override
    public Nivel buscarNivelPorId(Integer id){

        if(id > niveles.size()){
            return null;
        }

        for(int i = 0; i < niveles.size();i++){
            if(niveles.get(i).getId() == id){
                return niveles.get(i);
            }
        }

        return  null;
    }

    @Override
    public void seleccionarNivel(Nivel nivel) {

        if(niveles.contains(nivel)){
            nivelSeleccionado = nivel;
        }

    }


    @Override
    public void agregarNivel(Nivel nivel) {
        niveles.add(nivel);
    }

    @Override
    public Nivel devolverNivelSeleccionado() {
        return this.nivelSeleccionado;
    }
}
