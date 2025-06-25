package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Efecto;
import com.tallerwebi.dominio.entidad.Jugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioEfectosImpl implements RepositorioEfectos{

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public RepositorioEfectosImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Efecto> obtenerTodosLosEfectos() {
        Session session = sessionFactory.getCurrentSession();
        List<Efecto> efectos;
        efectos = session.createCriteria(Efecto.class).list();
        return efectos;
    }

    @Override
    public Efecto obtenerEfectoPorId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Efecto efecto;

        efecto = (Efecto) session.createCriteria(Efecto.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();

        return efecto;
    }

    @Override
    public void aplicarEfectoAlJugador(Jugador jugador, Efecto efecto) {
        Session session = sessionFactory.getCurrentSession();

        jugador = (Jugador) session.merge(jugador);

        if (!jugador.getEfectosActivos().contains(efecto)) {
            jugador.getEfectosActivos().add(efecto);
        }

        efecto.setJugador(jugador);

        System.out.println("Se aplico " + efecto.getNombre() + " a " + jugador.getNombre());

    }

    @Override
    public void removerEfectoDeJugador(Jugador jugador, Efecto efecto) {

        Session session = sessionFactory.getCurrentSession();

        jugador = (Jugador) session.merge(jugador);
        efecto = (Efecto) session.merge(efecto);

        boolean removed = jugador.getEfectosActivos().remove(efecto);

        if (removed) {
            efecto.setJugador(null);
        }

        System.out.println("Se Elimino " + efecto.getNombre() + " a " + jugador.getNombre());

    }

    @Override
    public void vaciarListaEfectos(Jugador jugador) {
        Session session = sessionFactory.getCurrentSession();
        jugador = (Jugador) session.merge(jugador);


        List<Efecto> efectosARemover = new ArrayList<>(jugador.getEfectosActivos());

        for (Efecto efecto : efectosARemover) {
            efecto.setJugador(null);
        }

        jugador.getEfectosActivos().clear();

    }


}
