package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Habilidad;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioHabilidadImpl implements RepositorioHabilidades{

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public RepositorioHabilidadImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Habilidad> obtenerTodasLasHabilidades() {
        Session session  = sessionFactory.getCurrentSession();
        return session.createCriteria(Habilidad.class).list();
    }

    @Override
    public Habilidad obtenerHabilidadPorId(Long id) {
        Session session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Habilidad.class);
        criteria.add(Restrictions.eq("id",id));

        return (Habilidad) criteria.uniqueResult();
    }

    @Override
    public List<Habilidad> obtenerHabilidadesNivel1() {
        Session session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Habilidad.class);
        criteria.add(Restrictions.eq("nivel",1));


        return (List<Habilidad>) criteria.list();
    }

    @Override
    public List<Habilidad> buscarHabilidadesDelJugador(Long jugadorId) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Habilidad.class);
        criteria.createAlias("jugador", "j");
        criteria.add(Restrictions.eq("j.id", jugadorId));

        return criteria.list();
    }
}
