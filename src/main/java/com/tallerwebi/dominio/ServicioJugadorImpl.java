package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import com.tallerwebi.dominio.entidad.Usuario;
import com.tallerwebi.infraestructura.RepositorioJugador;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("servicioJugador")
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioJugador repositorioJugador;
    private final RepositorioObjetos repositorioObjetos;

    @Autowired
    public ServicioJugadorImpl(RepositorioUsuario repositorioUsuario, RepositorioJugador repositorioJugador, RepositorioObjetos repositorioObjetos) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioJugador = repositorioJugador;
        this.repositorioObjetos = repositorioObjetos;
    }

    @Override
    public void crearNuevoJugador(Long userId, String nombre) {
        Usuario usuario = this.repositorioUsuario.buscarPorId(userId);
        Jugador nuevoJugador = new Jugador().setUsuario(usuario).setNombre(nombre);
        this.repositorioJugador.guardar(nuevoJugador);
        List<ObjetoInventario> objetosIniciales = this.repositorioObjetos.getObjetosIniciales().stream()
                .map(o -> new ObjetoInventario().setObjeto(o).setJugador(nuevoJugador))
                .collect(Collectors.toList());
        nuevoJugador.setObjetos(objetosIniciales);
        this.repositorioJugador.modificar(nuevoJugador);
    }

    @Override
    public Jugador getJugadorActual(Long userId) {
        return this.repositorioJugador.buscar(userId);
    }

    @Override
    public List<ObjetoInventario> getObjetosJugador(Jugador jugador) {
        return this.repositorioJugador.buscarObjetosInventario(jugador.getId());
    }
}
