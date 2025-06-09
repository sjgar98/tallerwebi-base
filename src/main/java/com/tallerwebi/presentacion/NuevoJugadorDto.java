package com.tallerwebi.presentacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoJugadorDto {
    private String nombre;

    public NuevoJugadorDto() {}
    public NuevoJugadorDto(String nombre) {
        this.nombre = nombre;
    }
}
