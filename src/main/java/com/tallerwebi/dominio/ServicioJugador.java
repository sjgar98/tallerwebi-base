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

    Integer getAtaqueAdicional(Jugador jugador);
    Integer getAtaqueTotal(Jugador jugador);
    Integer getDefensaAdicional(Jugador jugador);
    Integer getDefensaTotal(Jugador jugador);
    void equiparObjeto(Jugador jugador, Long objetoInventarioId);
    ObjetoInventario getObjetoEquipadoPorTipo(Jugador jugador, String nombreTipoObjeto);

    void agregarObjeto(Jugador jugador, Objeto objeto);
    void agregarObjeto(Jugador jugador, Objeto objeto, Integer cantidad);
    void removerObjeto(Jugador jugador, ObjetoInventario objeto);
    void agregarDinero(Jugador jugador, Long dinero);
    void removerDinero(Jugador jugador, Long dinero);

    void agregarObjetosAlJugador(List<Objeto> objetos, Long userId);
    void agregarOroAlJugador(Long userId, Long oro);
    void sacarObjetosAlJugador(Objeto objetoAUsar, Long userId);
    List<ObjetoInventario> getObjetosConsumibles(Long userId);
    void subirDeNivel(Integer experiencia, Long userId);
}
