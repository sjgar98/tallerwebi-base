package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioJugador")
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {
    private final RepositorioJugador repositorioJugador;

    @Autowired
    public ServicioJugadorImpl(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    @Override
    public Jugador getJugadorActual() {
        return this.repositorioJugador.buscar(1L);
    }
}
