package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Nivel {


    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private  Integer nivelMinimoPersonaje;
    private  Integer nivelMaximoEnemigo;
    private String descripcion;
    private Boolean seleccionado;

    @OneToMany(mappedBy = "nivel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NivelIntermedio> nivelIntermedios = new ArrayList<>();





    //Constructor
    public Nivel(Long id, Integer nivel_min_personaje, Integer nivelMaximoEnemigo, String desc, Boolean selec){

        this.id = id;
        this.nivelMinimoPersonaje = nivel_min_personaje;
        this.nivelMaximoEnemigo = nivelMaximoEnemigo;
        this.descripcion = desc;
        this.seleccionado = selec;
    }

    public Nivel(Integer nivel_min_personaje, Integer nivelMaximoEnemigo, String desc, Boolean selec, Long id_Enemgo){
        this.nivelMinimoPersonaje = nivel_min_personaje;
        this.nivelMaximoEnemigo = nivelMaximoEnemigo;
        this.descripcion = desc;
        this.seleccionado = selec;
    }

    public Nivel(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nivel nivel = (Nivel) o;
        return Objects.equals(id, nivel.id) &&
                Objects.equals(nivelMinimoPersonaje, nivel.nivelMinimoPersonaje) &&
                Objects.equals(nivelMaximoEnemigo, nivel.nivelMaximoEnemigo) &&
                Objects.equals(descripcion, nivel.descripcion) &&
                Objects.equals(seleccionado, nivel.seleccionado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado);
    }

    @Override
    public String toString() {
        return "Nivel{" +
                "id=" + id +
                ", nivel_minimo_personaje=" + nivelMinimoPersonaje +
                ", nivel_maximo_enemigo=" + nivelMaximoEnemigo +
                ", descripcion='" + descripcion + '\'' +
                ", seleccionado=" + seleccionado +
                '}';
    }
}
