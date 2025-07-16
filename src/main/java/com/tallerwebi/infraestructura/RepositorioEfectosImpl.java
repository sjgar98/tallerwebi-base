package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Efecto;
import com.tallerwebi.dominio.entidad.EfectoAplicado;
import com.tallerwebi.dominio.entidad.Jugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositorioEfectosImpl implements RepositorioEfectos {

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioEfectosImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Efecto> obtenerTodosLosEfectos() {
        return session().createCriteria(Efecto.class).list();
    }

    @Override
    public Efecto obtenerEfectoPorId(Long id) {
        return (Efecto) session().createCriteria(Efecto.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public List<Efecto> obtenerEfectosDelJugador(Jugador jugador) {
        return session().createCriteria(Efecto.class)
                .add(Restrictions.eq("jugador", jugador))
                .list();
    }

    @Override
    public List<EfectoAplicado> obtenerEfectosAplicadosDelJguador(Jugador jugador) {
        return session().createCriteria(EfectoAplicado.class)
                .add(Restrictions.eq("jugador", jugador))
                .list();
    }

    @Override
    public EfectoAplicado obtenerEfectoAplicadoPorId(Long id) {
        return (EfectoAplicado) session().createCriteria(EfectoAplicado.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public void crearInstanciaEfectoAplicado(EfectoAplicado efectoAplicado) {
        session().save(efectoAplicado);
    }

    @Override
    public List<EfectoAplicado> efectosAplicadosAsociadosAlJugador(Jugador jugador) {
        return session().createCriteria(EfectoAplicado.class)
                .add(Restrictions.eq("jugador", jugador))
                .list();
    }

    @Override
    public void eliminarEfectoAplicado(EfectoAplicado efectoAplicado) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(efectoAplicado);
    }

    @Override
    public EfectoAplicado obtenerEfectoAplicadoPorJugadorYEfectoBase(Jugador jugador, Efecto efectoBase) {
        Session session = sessionFactory.getCurrentSession();
        return (EfectoAplicado) session.createCriteria(EfectoAplicado.class)
                .add(Restrictions.eq("jugador", jugador))
                .add(Restrictions.eq("efectoBase", efectoBase))
                .uniqueResult();
    }

    @Override
    public void eliminarTodosLosEfectosAplicadosDeJugador(Jugador jugador) {
        Session session = sessionFactory.getCurrentSession();
        List<EfectoAplicado> efectosAplicados = session.createCriteria(EfectoAplicado.class)
                .add(Restrictions.eq("jugador", jugador))
                .list();

        for (EfectoAplicado efecto : efectosAplicados) {
            session.delete(efecto);
        }
    }


    @Override
    public void eliminarTodosLosEfectosAplicados() {
        session().createQuery("DELETE FROM EfectoAplicado").executeUpdate();
    }
}
