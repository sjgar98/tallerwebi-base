package com.tallerwebi.servicios;

import java.util.Objects;

public class Nivel {

    //Variables
    private  Integer id;
    private  Integer nivelMinimoPersonaje;
    private  Integer nivelMaximoEnemigo;
    private String descripcion;
    private String recompensas;
    private Boolean seleccionado;


    //Constructor
    public Nivel(Integer id, Integer nivel_min_personaje, Integer nivelMaximoEnemigo, String desc, String recompensas, Boolean selec){

        this.id = id;
        this.nivelMinimoPersonaje = nivel_min_personaje;
        this.nivelMaximoEnemigo = nivelMaximoEnemigo;
        this.descripcion = desc;
        this.recompensas = recompensas;

        this.seleccionado = selec;

    }

    //Getters y Setters


    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNivelMaximoEnemigo() {
        return nivelMaximoEnemigo;
    }

    public Integer getNivelMinimoPersonaje() {
        return nivelMinimoPersonaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRecompensas() {
        return recompensas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNivelMaximoEnemigo(Integer nivelMaximoEnemigo) {
        this.nivelMaximoEnemigo = nivelMaximoEnemigo;
    }

    public void setNivelMinimoPersonaje(Integer nivelMinimoPersonaje) {
        this.nivelMinimoPersonaje = nivelMinimoPersonaje;
    }

    public void setRecompensas(String recompensas) {
        this.recompensas = recompensas;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nivel nivel = (Nivel) o;
        return Objects.equals(id, nivel.id) &&
                Objects.equals(nivelMinimoPersonaje, nivel.nivelMinimoPersonaje) &&
                Objects.equals(nivelMaximoEnemigo, nivel.nivelMaximoEnemigo) &&
                Objects.equals(descripcion, nivel.descripcion) &&
                Objects.equals(recompensas, nivel.recompensas) &&
                Objects.equals(seleccionado, nivel.seleccionado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, recompensas, seleccionado);
    }

    @Override
    public String toString() {
        return "Nivel{" +
                "id=" + id +
                ", nivel_minimo_personaje=" + nivelMinimoPersonaje +
                ", nivel_maximo_enemigo=" + nivelMaximoEnemigo +
                ", descripcion='" + descripcion + '\'' +
                ", recompensas='" + recompensas + '\'' +
                ", seleccionado=" + seleccionado +
                '}';
    }
}
