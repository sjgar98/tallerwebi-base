package com.tallerwebi;

import java.util.Objects;

public class Nivel {

    //Variables
    private  Integer id;
    private  Integer nivel_minimo_personaje;
    private  Integer nivel_maximo_enemigo;
    private String descripcion;
    private String recompensas;
    private Boolean seleccionado;


    //Constructor
    public Nivel(Integer id, Integer nivel_min_personaje, Integer nivel_maximo_enemigo, String desc, String recompensas, Boolean selec){

        this.id = id;
        this.nivel_minimo_personaje = nivel_min_personaje;
        this.nivel_maximo_enemigo = nivel_maximo_enemigo;
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

    public Integer getNivel_maximo_enemigo() {
        return nivel_maximo_enemigo;
    }

    public Integer getNivel_minimo_personaje() {
        return nivel_minimo_personaje;
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

    public void setNivel_maximo_enemigo(Integer nivel_maximo_enemigo) {
        this.nivel_maximo_enemigo = nivel_maximo_enemigo;
    }

    public void setNivel_minimo_personaje(Integer nivel_minimo_personaje) {
        this.nivel_minimo_personaje = nivel_minimo_personaje;
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
                Objects.equals(nivel_minimo_personaje, nivel.nivel_minimo_personaje) &&
                Objects.equals(nivel_maximo_enemigo, nivel.nivel_maximo_enemigo) &&
                Objects.equals(descripcion, nivel.descripcion) &&
                Objects.equals(recompensas, nivel.recompensas) &&
                Objects.equals(seleccionado, nivel.seleccionado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nivel_minimo_personaje, nivel_maximo_enemigo, descripcion, recompensas, seleccionado);
    }

    @Override
    public String toString() {
        return "Nivel{" +
                "id=" + id +
                ", nivel_minimo_personaje=" + nivel_minimo_personaje +
                ", nivel_maximo_enemigo=" + nivel_maximo_enemigo +
                ", descripcion='" + descripcion + '\'' +
                ", recompensas='" + recompensas + '\'' +
                ", seleccionado=" + seleccionado +
                '}';
    }
}
