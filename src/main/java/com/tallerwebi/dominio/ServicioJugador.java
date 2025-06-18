package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;

import java.util.List;

public interface ServicioJugador {
    void crearNuevoJugador(Long userId, String nombre);
    Jugador getJugadorActual(Long userId);
    List<ObjetoInventario> getObjetosJugador(Jugador jugador);
    ObjetoInventario getObjetoInventarioPorId(Long objetoInventarioId);

    void agregarObjeto(Jugador jugador, Objeto objeto);
    void agregarObjeto(Jugador jugador, Objeto objeto, Integer cantidad);
    void removerObjeto(Jugador jugador, ObjetoInventario objeto);
    void agregarDinero(Jugador jugador, Long dinero);
    void removerDinero(Jugador jugador, Long dinero);

    void venderObjeto(Jugador jugador, Long objetoInventarioId);
    void agregarObjetosAlJugador(List<Objeto> objetos, Long userId);
    void agregarOroAlJugador(Long userId, Long oro);
    void sacarObjetosAlJugador(Objeto objetoAUsar, Long userId);
    List<ObjetoInventario> getObjetosConsumibles(Long userId);
    void subirDeNivel(Integer experiencia, Long userId);
}
