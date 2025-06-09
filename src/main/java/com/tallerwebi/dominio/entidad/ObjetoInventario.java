package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Accessors(chain = true)
@Getter
@Setter
public class ObjetoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Objeto objeto;
    @ManyToOne
    private Jugador jugador;
    private Integer cantidad = 1;
    private Boolean equipado;
}
