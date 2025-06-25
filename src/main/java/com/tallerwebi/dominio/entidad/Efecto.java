package com.tallerwebi.dominio.entidad;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Accessors(chain = true)
@Getter
@Setter
@Entity
public class Efecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer duracionTotal;
    private Integer duracionActual;
    private Integer danioPorTurno;


    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = true)
    private Jugador jugador;

    public  Efecto (){}

    public Efecto(Long id, String nombre, Integer duracionTotal,Integer duracionActual, Integer danioPorTurno){
        this.id = id;
        this.nombre = nombre;
        this.duracionTotal = duracionTotal;
        this.duracionActual = duracionActual;
        this.danioPorTurno = danioPorTurno;
    }

    // --- Implementación de equals y hashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Efecto efecto = (Efecto) o;
        return id != null && Objects.equals(id, efecto.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre, duracionTotal, danioPorTurno);
    }
}
