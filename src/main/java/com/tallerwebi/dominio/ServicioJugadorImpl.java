package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import com.tallerwebi.dominio.entidad.Usuario;
import com.tallerwebi.infraestructura.RepositorioJugador;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("servicioJugador")
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
    public void agregarObjeto(Jugador jugador, Objeto objeto) {

        if (jugador.getId() == null) {
            this.sessionFactory.getCurrentSession().save(jugador);
        }


        Jugador jugadorManaged = (Jugador) this.sessionFactory.getCurrentSession().merge(jugador);


        List<ObjetoInventario> inventario = this.repositorioJugador.buscarObjetosInventario(jugadorManaged.getId());
        ObjetoInventario existente = inventario.stream()
                .filter(oi -> oi.getObjeto().getId().equals(objeto.getId()))
                .findFirst()
                .orElse(null);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + 1);
            this.sessionFactory.getCurrentSession().update(existente);
        } else {
            ObjetoInventario nuevo = new ObjetoInventario()
                    .setObjeto(objeto)
                    .setJugador(jugadorManaged)
                    .setCantidad(1);
            this.sessionFactory.getCurrentSession().save(nuevo);

            jugadorManaged.agregarObjeto(nuevo);
        }
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
}
