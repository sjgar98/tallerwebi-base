package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NivelDTO {

    private Long id;


    private  Integer nivelMinimoPersonaje;
    private  Integer nivelMaximoEnemigo;
    private String descripcion;
    private Boolean seleccionado;

    public NivelDTO(Long id, Integer nivMin, Integer nivMax, String desc, Boolean bool){

        this.id = id;
        this.nivelMaximoEnemigo = nivMax;
        this.nivelMinimoPersonaje = nivMin;
        this.descripcion = desc;
        this.seleccionado = bool;

    }
}
