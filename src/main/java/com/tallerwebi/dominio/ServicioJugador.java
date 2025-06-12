package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.ObjetoInventario;

import java.util.List;

public interface ServicioJugador {
    void crearNuevoJugador(Long userId, String nombre);
    Jugador getJugadorActual(Long userId);
    List<ObjetoInventario> getObjetosJugador(Jugador jugador);
    void agregarObjetosAlJugador(List<ObjetoInventario> objetos, Long userId);
    void agregarOroAlJugador(Long userId, Long oro);
}
