package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class EnemigoDTO {

    private Long id;
    private String nombre;
    private Integer nivel;

    private Integer vidaActual;
    private Integer vidaMaxima;

    private Integer ataque;
    private Integer defensa;

    private String imagenSrc;

    private List<HabilidadDTO> habilidades = new ArrayList<>();
    private Integer probabilidadAplicarEfecto;
    private EfectoDTO efecto;

    private List<EfectoDTO> efectosRecibidos = new ArrayList<>();


    public EnemigoDTO(){

    }

    public EnemigoDTO(Long id, String nombre,Integer nivel, Integer vidaActual, Integer vidaMaxima,
                      Integer ataque, Integer defensa, String img,
                      Integer probabilidadAplicarEfecto) {

        this.id = id;
        this.nivel = nivel;
        this.nombre = nombre;
        this.vidaActual = vidaActual;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.imagenSrc = img;
        this.probabilidadAplicarEfecto = probabilidadAplicarEfecto;
    }
}
