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

@Service
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

    @Override
    public void agregarObjetosAlJugador(List<ObjetoInventario> objetos, Long userId) {

        Jugador jugadorActual = this.getJugadorActual(userId);
        List<ObjetoInventario> inventarioJugador = this.repositorioJugador.buscarObjetosInventario(jugadorActual.getId());



        for(int i = 0; i < objetos.size(); i++){

            ObjetoInventario nuevoObjeto = objetos.get(i);
            if (inventarioJugador.contains(nuevoObjeto)){
               Integer index = inventarioJugador.indexOf(nuevoObjeto);

               inventarioJugador.get(index).setCantidad(inventarioJugador.get(index).getCantidad() + 1);
            } else{
                inventarioJugador.add(nuevoObjeto);
            }

        }

        for (int e = 0; e < inventarioJugador.size(); e++){

            System.out.println(inventarioJugador.get(e).getObjeto().getDescripcion() + "cantidad" +  inventarioJugador.get(e).getCantidad());

        }

        jugadorActual.setObjetos(inventarioJugador);

        this.repositorioJugador.modificar(jugadorActual);


    }

    @Override
    public void agregarOroAlJugador(Long userId, Long oro) {
        Jugador jugadorActual = this.getJugadorActual(userId);
        jugadorActual.setDinero(jugadorActual.getDinero() + oro);
    }
}
