package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private Integer probabilidadAplicarEfecto;
    private Integer probabilidadUsarHabilidad;
    private Integer cantidadDeVecesParaUsarHabilidad;

    private EfectoDTO efecto;

    private List<EfectoAplicadoEnemigo> efectosRecibidos = new ArrayList<>();
    private List<Habilidad> habilidades = new ArrayList<>();

    public EnemigoDTO(){

    }

    public EnemigoDTO(Long id, String nombre,Integer nivel, Integer vidaActual, Integer vidaMaxima,
                      Integer ataque, Integer defensa, String img,
                      Integer probabilidadAplicarEfecto, Integer probabilidadUsarHabilidad, Integer cantidadDeVecesParaUsarHabilidad) {

        this.id = id;
        this.nivel = nivel;
        this.nombre = nombre;
        this.vidaActual = vidaActual;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.imagenSrc = img;
        this.probabilidadAplicarEfecto = probabilidadAplicarEfecto;
        this.probabilidadUsarHabilidad = probabilidadUsarHabilidad;
        this.cantidadDeVecesParaUsarHabilidad = cantidadDeVecesParaUsarHabilidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnemigoDTO that = (EnemigoDTO) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
