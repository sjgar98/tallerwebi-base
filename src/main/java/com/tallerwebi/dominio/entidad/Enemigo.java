package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Accessors(chain = true)
@Getter
@Setter
public class Enemigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer nivel;

    private Integer vidaActual;
    private Integer vidaMaxima;

    private Integer ataque;
    private Integer defensa;

    private String imagenSrc;

    @OneToMany(mappedBy = "enemigo")
    private List<NivelIntermedio> nivelIntermedios = new ArrayList<>();

    @Override
    public String toString() {
        return "Enemigo{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", Vida Actual=" + vidaActual +
                ", vidaMaxima=" + vidaMaxima +
                ", ataque=" + ataque +
                ", defensa=" + defensa + "Imgen: " + imagenSrc +
                '}';

    }
}
