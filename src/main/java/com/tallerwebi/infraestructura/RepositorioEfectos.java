package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Efecto;
import com.tallerwebi.dominio.entidad.Jugador;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RepositorioEfectos {

    List<Efecto> obtenerTodosLosEfectos();
    Efecto obtenerEfectoPorId(Long id);

    void aplicarEfectoAlJugador(Long idJugador, Long idEfecto);

    void removerEfectoDeJugador(Jugador jugador, Efecto efecto);

    void vaciarListaEfectos(Jugador jugador);

}
