package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import com.tallerwebi.dominio.entidad.Usuario;
import com.tallerwebi.dominio.excepcion.DineroInsuficienteException;
import com.tallerwebi.infraestructura.RepositorioJugador;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioJugador repositorioJugador;
    private final RepositorioObjetos repositorioObjetos;
    private final SessionFactory sessionFactory;

    @Autowired
    public ServicioJugadorImpl(RepositorioUsuario repositorioUsuario, RepositorioJugador repositorioJugador, RepositorioObjetos repositorioObjetos, SessionFactory sessionFactory) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioJugador = repositorioJugador;
        this.repositorioObjetos = repositorioObjetos;
        this.sessionFactory = sessionFactory;
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
    public ObjetoInventario getObjetoInventarioPorId(Long objetoInventarioId) {
        return this.repositorioJugador.buscarObjetoInventarioPorId(objetoInventarioId);
    }

    @Override
    public void agregarObjeto(Jugador jugador, Objeto objeto) {
        var objetosInventario = repositorioJugador.buscarObjetosInventarioPorObjeto(jugador, objeto);
        var primerSlotConEspacio = objetosInventario.stream()
                .filter(o -> o.getCantidad() + 1 <= objeto.getMaxPorSlot())
                .findFirst().orElse(null);
        if (primerSlotConEspacio != null) {
            primerSlotConEspacio.setCantidad(primerSlotConEspacio.getCantidad() + 1);
            repositorioJugador.modificarObjeto(primerSlotConEspacio);
        } else {
            ObjetoInventario slotNuevo = new ObjetoInventario().setJugador(jugador).setObjeto(objeto);
            repositorioJugador.agregarObjeto(slotNuevo);
        }
    }

    @Override
    public void agregarObjeto(Jugador jugador, Objeto objeto, Integer cantidad) {
        Integer cantidadRestante = cantidad;
        while (cantidadRestante > 0) {
            this.agregarObjeto(jugador, objeto);
            cantidadRestante--;
        }
    }

    @Override
    public void removerObjeto(Jugador jugador, ObjetoInventario objeto) {
        objeto.setCantidad(objeto.getCantidad() - 1);
        if (objeto.getCantidad() == 0) {
            repositorioJugador.removerObjeto(objeto);
        } else {
            repositorioJugador.modificarObjeto(objeto);
        }
    }

    @Override
    public void agregarDinero(Jugador jugador, Long dinero) {
        jugador.setDinero(jugador.getDinero() + dinero);
        repositorioJugador.modificar(jugador);
    }

    @Override
    public void removerDinero(Jugador jugador, Long dinero) {
        if (jugador.getDinero() - dinero < 0) {
            throw new DineroInsuficienteException("Dinero insuficiente para esta operacion");
        }
        jugador.setDinero(jugador.getDinero() - dinero);
        repositorioJugador.modificar(jugador);
    }

    @Override
    public void venderObjeto(Jugador jugador, Long objetoInventarioId) {
        if (jugador.getId() == null) {
            throw new IllegalStateException("Jugador no persistido");
        }

        // Obtener ObjetoInventario
        ObjetoInventario oi = (ObjetoInventario) this.sessionFactory.getCurrentSession()
                .get(ObjetoInventario.class, objetoInventarioId);

        if (oi == null || !oi.getJugador().getId().equals(jugador.getId())) {
            throw new IllegalArgumentException("Objeto no pertenece al jugador");
        }

        // Acreditar mitad del valor del objeto
        Long ganancia = oi.getObjeto().getValor() / 2;
        jugador.setDinero(jugador.getDinero() + ganancia);

        // Remover de la colección y borrar
        jugador.getObjetos().remove(oi);
        this.sessionFactory.getCurrentSession().delete(oi);
    }

    @Override
    public void agregarObjetosAlJugador(List<Objeto> objetos, Long userId) {

        Jugador jugadorActual = this.repositorioJugador.buscar(userId);

        List<ObjetoInventario> objetoRecompensa = objetos.stream()
                .map(o -> new ObjetoInventario().setObjeto(o).setJugador(jugadorActual))
                .collect(Collectors.toList());


        for(int i = 0; i < objetos.size(); i++){

            ObjetoInventario nuevoObjeto = objetoRecompensa.get(i);
            if (jugadorActual.getObjetos().contains(nuevoObjeto)){
                Integer index = jugadorActual.getObjetos().indexOf(nuevoObjeto);

                jugadorActual.getObjetos().get(index).setCantidad(jugadorActual.getObjetos().get(index).getCantidad() + 1);
            } else{
                jugadorActual.getObjetos().add(nuevoObjeto);
            }

        }
        this.repositorioJugador.modificar(jugadorActual);

    }

    @Override
    public void agregarOroAlJugador(Long userId, Long oro) {
        Jugador jugadorActual = this.getJugadorActual(userId);
        jugadorActual.setDinero(jugadorActual.getDinero() + oro);
    }
}
