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
    private String nombre;
    private Integer nivelMinimoPersonaje;
    private Integer nivelMaximoEnemigo;
    private String descripcion;
    private Boolean seleccionado;
    private Long oro;


    @ManyToMany
    @JoinTable(
            name = "nivel_enemigo",
            joinColumns = @JoinColumn(name = "nivel_id"),
            inverseJoinColumns = @JoinColumn(name = "enemigo_id")
    )
    //@OrderColumn(name = "enemigo_orden")
    private List<Enemigo> enemigos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "nivel_objeto",
            joinColumns = @JoinColumn(name = "nivel_id"),
            inverseJoinColumns = @JoinColumn(name = "objeto_id")
    )
    //@OrderColumn(name = "objeto_orden")
    private List<Objeto> objetos = new ArrayList<>();

    //Constructor
    public Nivel(Long id, Integer nivel_min_personaje, Integer nivelMaximoEnemigo, String desc, Boolean selec) {

        this.id = id;
        this.nivelMinimoPersonaje = nivel_min_personaje;
        this.nivelMaximoEnemigo = nivelMaximoEnemigo;
        this.descripcion = desc;
        this.seleccionado = selec;
    }

    public Nivel(Long id,String nombre,Integer nivel_min_personaje, Integer nivelMaximoEnemigo, String desc, Boolean selec){
        this.id =id;
        this.nombre =nombre;
        this.nivelMinimoPersonaje =nivel_min_personaje;
        this.nivelMaximoEnemigo =nivelMaximoEnemigo;
        this.descripcion =desc;
        this.seleccionado =selec;
    }

    public Nivel(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.tallerwebi.dominio.entidad.Nivel nivel = (com.tallerwebi.dominio.entidad.Nivel) o;
        return Objects.equals(id, nivel.id) &&
                    Objects.equals(nivelMinimoPersonaje, nivel.nivelMinimoPersonaje) &&
                    Objects.equals(nivelMaximoEnemigo, nivel.nivelMaximoEnemigo) &&
                    Objects.equals(descripcion, nivel.descripcion) &&
                    Objects.equals(nombre, nivel.nombre) &&
                    Objects.equals(seleccionado, nivel.seleccionado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nivelMinimoPersonaje, nivelMaximoEnemigo, descripcion, seleccionado, nombre);
    }

    @Override
    public String toString() {
        return "com.tallerwebi.dominio.excepcion.Nivel{" +
                "id=" + id +
                ", nivel_minimo_personaje=" + nivelMinimoPersonaje +
                ", nivel_maximo_enemigo=" + nivelMaximoEnemigo +
                "nombre: " + nombre +
                ", descripcion='" + descripcion + '\'' +
                ", seleccionado=" + seleccionado +
                '}';
    }

}
