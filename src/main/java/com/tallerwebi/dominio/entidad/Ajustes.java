package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
public class Ajustes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


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
