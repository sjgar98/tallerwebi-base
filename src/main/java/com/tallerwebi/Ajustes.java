package com.tallerwebi;

import java.util.Objects;

public class Ajustes {

    //Variables

    private String color_fondo_hexadecimal;
    private  Integer volumen_general;
    private  Integer volumen_musica;
    private  Integer volumen_efectos;
    private  Integer dificultad;

    //Constructor
    public Ajustes(String color, Integer vg, Integer vm, Integer ve, Integer dificultad){
        this.color_fondo_hexadecimal = color;
        this.volumen_general = vg;
        this.volumen_musica = vm;
        this.volumen_efectos = ve;
        this.dificultad = dificultad;
    }
    public Ajustes() {
    }


    //Getters y Setters
    public String getColor_fondo_hexadecimal() {
        return color_fondo_hexadecimal;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public Integer getVolumen_efectos() {
        return volumen_efectos;
    }

    public Integer getVolumen_general() {
        return volumen_general;
    }

    public Integer getVolumen_musica() {
        return volumen_musica;
    }

    public void setColor_fondo_hexadecimal(String color_fondo_hexadecimal) {
        this.color_fondo_hexadecimal = color_fondo_hexadecimal;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }

    public void setVolumen_efectos(Integer volumen_efectos) {
        this.volumen_efectos = volumen_efectos;
    }

    public void setVolumen_general(Integer volumen_general) {
        this.volumen_general = volumen_general;
    }

    public void setVolumen_musica(Integer volumen_musica) {
        this.volumen_musica = volumen_musica;
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(color_fondo_hexadecimal, volumen_general, volumen_musica, volumen_efectos, dificultad);
    }

    // toString

    @Override
    public String toString() {
        return "Ajustes{" +
                "color_fondo=" + color_fondo_hexadecimal +
                ", volumen_general=" + volumen_general +
                ", volumen_musica=" + volumen_musica +
                ", volumen_efectos=" + volumen_efectos +
                ", dificultad=" + dificultad +
                '}';
    }
}
