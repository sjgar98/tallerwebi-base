package com.tallerwebi.dominio.entidad;

import com.tallerwebi.infraestructura.RepositorioEfectos;
import com.tallerwebi.infraestructura.RepositorioHabilidades;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Accessors(chain = true)
@Getter
@Setter
public class Combate {

    private Jugador jugador;
    private List<EnemigoDTO> enemigos;
    private Long recompensaOro;


    @Autowired
    public Combate(){
    }



    public Boolean estaVivo(){
        if (jugador.getVidaActual() > 0 ){
            return true;
        } else {
            return false;
        }
    }

    public Boolean gano(){

        for (EnemigoDTO enemigo : enemigos) {
            if(enemigo.getVidaActual() > 0){
                return false;
            }
        }
        return true;
    }



}
