package com.tallerwebi;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
    public void seleccionarNivel(Nivel nivel, Integer nivelJugador) {

        if(niveles.contains(nivel) && nivel.getNivel_minimo_personaje() <= nivelJugador){
            if(this.nivelSeleccionado != null){
                nivel.setSeleccionado(true);
                buscarNivelPorId(nivelSeleccionado.getId()).setSeleccionado(false);
                nivelSeleccionado = nivel;

            } else if(this.nivelSeleccionado == null) {
                nivel.setSeleccionado(true);
                nivelSeleccionado = nivel;

            }


        } else if (niveles.contains(nivel) && nivel.getNivel_minimo_personaje() > nivelJugador){

            this.nivelSeleccionado = null;

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

    @Override
    public Nivel crearNivel(Integer id, Integer niv_min, Integer niv_enem_max, String desc, String recompensas, Boolean estado) {
        Nivel nuevoNivel = new Nivel(id,niv_min,niv_enem_max,desc,recompensas,estado);
        return nuevoNivel;
    }

}
