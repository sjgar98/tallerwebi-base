package com.tallerwebi.servicios;

import java.util.Objects;

public class Ajustes {

    //Variables

    private String colorFondoHexadecimal;
    private  Integer volumenGeneral;
    private  Integer volumenMusica;
    private  Integer volumenEfectos;
    private  Integer dificultad;

    //Constructor
    public Ajustes(String color, Integer vg, Integer vm, Integer ve, Integer dificultad){
        this.colorFondoHexadecimal = color;
        this.volumenGeneral = vg;
        this.volumenMusica = vm;
        this.volumenEfectos = ve;
        this.dificultad = dificultad;
    }
    public Ajustes() {
    }


    //Getters y Setters
    public String getcolorFondoHexadecimal() {
        return colorFondoHexadecimal;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public Integer getVolumenEfectos() {
        return volumenEfectos;
    }

    public Integer getVolumenGeneral() {
        return volumenGeneral;
    }

    public Integer getVolumenMusica() {
        return volumenMusica;
    }

    public void setcolorFondoHexadecimal(String colorFondoHexadecimal) {
        this.colorFondoHexadecimal = colorFondoHexadecimal;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }

    public void setVolumenEfectos(Integer volumenEfectos) {
        this.volumenEfectos = volumenEfectos;
    }

    public void setVolumenGeneral(Integer volumenGeneral) {
        this.volumenGeneral = volumenGeneral;
    }

    public void setVolumenMusica(Integer volumenMusica) {
        this.volumenMusica = volumenMusica;
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(colorFondoHexadecimal, volumenGeneral, volumenMusica, volumenEfectos, dificultad);
    }

    // toString

    @Override
    public String toString() {
        return "Ajustes{" +
                "color_fondo=" + colorFondoHexadecimal +
                ", volumen_general=" + volumenGeneral +
                ", volumen_musica=" + volumenMusica +
                ", volumen_efectos=" + volumenEfectos +
                ", dificultad=" + dificultad +
                '}';
    }
}
