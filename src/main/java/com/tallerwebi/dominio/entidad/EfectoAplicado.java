package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Getter
@Setter
@Entity
public class EfectoAplicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer duracionActual;


    @ManyToOne
    private Efecto efectoBase;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = true)
    private Jugador jugador;



}
