package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class NivelDTO {

    private Long id;

    private String nombre;
    private  Integer nivelMinimoPersonaje;
    private  Integer nivelMaximoEnemigo;
    private String descripcion;
    private Boolean seleccionado;
    private  Long oro;

    public NivelDTO(Long id,String nombre, Integer nivMin, Integer nivMax, String desc, Boolean bool, Long oro){

        this.id = id;
        this.nombre = nombre;
        this.nivelMaximoEnemigo = nivMax;
        this.nivelMinimoPersonaje = nivMin;
        this.descripcion = desc;
        this.seleccionado = bool;
        this.oro = oro;

    }
}
