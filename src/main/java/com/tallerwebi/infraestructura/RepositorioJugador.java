package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.ObjetoInventario;

import java.util.List;

public interface RepositorioJugador {
    Jugador buscar(Long jugadorId);
    List<ObjetoInventario> buscarObjetosInventario(Long jugadorId);
    void guardar(Jugador jugador);
    void modificar(Jugador jugador);
}
