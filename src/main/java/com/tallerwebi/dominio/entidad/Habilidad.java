package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Entity
@Getter
@Setter
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    private TipoHabilidad tipo;
    private Integer consumoMana;
    private Integer danio;
    private Integer nivel;

    @ManyToMany
    @JoinTable(
            name = "habilidad_efecto",
            joinColumns = @JoinColumn(name = "habilidad_id"),
            inverseJoinColumns = @JoinColumn(name = "efecto_id")
    )
    private List<Efecto> efectos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = true)
    private Jugador jugador;

    @ManyToMany(mappedBy = "habilidades")
    private List<Enemigo> enemigos = new ArrayList<>();

    public Habilidad(){

    }

}
