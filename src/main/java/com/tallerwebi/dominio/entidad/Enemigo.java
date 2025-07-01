package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private Integer probabilidadAplicarEfecto;

    @OneToMany(mappedBy = "enemigo")
    private List<NivelIntermedio> nivelIntermedios = new ArrayList<>();

    @ManyToMany(mappedBy = "enemigos")
    private List<Nivel> niveles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "efecto_id", nullable = true)
    private Efecto efecto;

    @ManyToMany
    @JoinTable(
            name = "enemigo_habilidad",
            joinColumns = @JoinColumn(name = "enemigo_id"),
            inverseJoinColumns = @JoinColumn(name = "habilidad_id")
    )
    private List<Habilidad> habilidades = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "enemigo_efecto",
            joinColumns = @JoinColumn(name = "enemigo_id"),
            inverseJoinColumns = @JoinColumn(name = "efecto_id")
    )
    private List<Efecto> efectosRecibidos = new ArrayList<>();


    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, nivel);
    }

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
