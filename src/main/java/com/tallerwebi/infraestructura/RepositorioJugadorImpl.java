package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("repositorioJugador")
public class RepositorioJugadorImpl implements RepositorioJugador {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioJugadorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Jugador buscar(Long userId) {
        return (Jugador) sessionFactory.getCurrentSession().createCriteria(Jugador.class)
                .createAlias("usuario", "u")
                .add(Restrictions.eq("u.id", userId)).uniqueResult();
    }

    @Override
    public List<ObjetoInventario> buscarObjetosInventario(Long jugadorId) {
        return (List<ObjetoInventario>) sessionFactory.getCurrentSession().createCriteria(ObjetoInventario.class)
                .createAlias("jugador", "j")
                .add(Restrictions.eq("j.id", jugadorId))
                .list();
    }

    @Override
    public void guardar(Jugador jugador) {
        this.sessionFactory.getCurrentSession().save(jugador);
    }

    @Override
    public void modificar(Jugador jugador) {
        this.sessionFactory.getCurrentSession().update(jugador);
    }
}
