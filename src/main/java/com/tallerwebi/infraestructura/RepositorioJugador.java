package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;

import java.util.List;

public interface RepositorioJugador {
    Jugador buscar(Long jugadorId);
    void guardar(Jugador jugador);
    void modificar(Jugador jugador);
    List<ObjetoInventario> buscarObjetosInventario(Long jugadorId);
    List<ObjetoInventario> buscarObjetosInventarioPorObjeto(Jugador jugador, Objeto objeto);
    ObjetoInventario buscarObjetoInventarioPorTipo(Jugador jugador, String nombreTipoObjeto);
    ObjetoInventario buscarObjetoInventarioPorId(Long objetoInventarioId);
    void agregarObjeto(ObjetoInventario objeto);
    void modificarObjeto(ObjetoInventario objeto);
    void removerObjeto(ObjetoInventario objeto);
}
