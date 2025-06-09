package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class Combate {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private List<Objeto> recompensaObjetos;
    private Long recompensaOro;
}
