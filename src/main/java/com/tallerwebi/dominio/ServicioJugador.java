package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;

import java.util.List;

public interface ServicioJugador {
    void crearNuevoJugador(Long userId, String nombre);
    Jugador getJugadorActual(Long userId);
    List<ObjetoInventario> getObjetosJugador(Jugador jugador);


    void agregarObjeto(Jugador jugador, Objeto objeto);


    void venderObjeto(Jugador jugador, Long objetoInventarioId);
}
