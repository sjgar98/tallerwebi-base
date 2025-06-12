package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class EnemigoDTO {
    private Long id;
    private String nombre;
    private Integer nivel = 1;
    // Combat Stats
    private Integer vidaActual;
    private Integer vidaMaxima;
    // Attributes
    private Integer ataque;
    private Integer defensa;

    private String imagenSrc;

    public EnemigoDTO(){

    }

    public EnemigoDTO(Long id, String nombre, Integer vidaActual, Integer vidaMaxima, Integer ataque, Integer defensa, String img) {

        this.id = id;
        this.nombre = nombre;
        this.vidaActual = vidaActual;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.imagenSrc = img;
    }
}
