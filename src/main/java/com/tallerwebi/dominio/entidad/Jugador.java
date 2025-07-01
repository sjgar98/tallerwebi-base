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
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Usuario usuario;
    // Base Stats
    private String nombre;
    private Integer nivel = 15;
    private Integer expActual = 0;
    private Integer expSigNiv = 100;
    private Long dinero = 0L;
    // Combat Stats
    private Integer vidaActual = 200;
    private Integer vidaMaxima = 200;
    // Attributes
    private Integer ataque = 5;
    private Integer defensa = 5;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjetoInventario> objetos = List.of();


    @OneToMany(mappedBy = "jugador")
    private List<Efecto> efectosActivos = new ArrayList<>();

    @OneToMany(mappedBy = "jugador")
    private List<Habilidad> habilidades = new ArrayList<>();
}
