package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class JugadorDTO {

    private Long id;
    private String nombre;
    private Integer vidaActual;
    private Integer vidaMaxima;
    private Integer ataque;
    private Integer defensa;
    private Integer nivel;

    private List<EfectoDTO> efectos = new ArrayList<>();
    private List<ObjetoInventario> objetos;
    private List<HabilidadDTO> habilidades = new ArrayList<>();

    public JugadorDTO() {}

    public JugadorDTO(Jugador jugador) {
        this.id = jugador.getId();
        this.nombre = jugador.getNombre();
        this.vidaActual = jugador.getVidaActual();
        this.vidaMaxima = jugador.getVidaMaxima();
        this.ataque = jugador.getAtaque();
        this.defensa = jugador.getDefensa();
        this.nivel = jugador.getNivel();
        this.objetos = jugador.getObjetos();
    }
}
