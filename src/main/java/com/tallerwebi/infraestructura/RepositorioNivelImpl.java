package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Enemigo;
import com.tallerwebi.dominio.entidad.Nivel;
import com.tallerwebi.dominio.entidad.NivelIntermedio;
import com.tallerwebi.dominio.entidad.Objeto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioNivelImpl implements RepositorioNivel {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public RepositorioNivelImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<NivelIntermedio> devolverTodosLosNivelesIntermedio() {
        Session session = sessionFactory.getCurrentSession();
        List<NivelIntermedio> niveles;
        niveles = session.createCriteria(NivelIntermedio.class).list();


        return niveles;
    }

    @Override
    public List<Nivel> devolverTodosLosNiveles() {

        Session session = sessionFactory.getCurrentSession();
        List<Nivel> niveles;

        niveles = session.createCriteria(NivelIntermedio.class)
                .setProjection(Projections.property("nivel"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();


        return niveles;
    }

    @Override
    public Nivel devolverNivelPorId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Nivel nivel;

        nivel = (Nivel) session.createCriteria(NivelIntermedio.class).add(Restrictions.eq("nivel.id",id))
                .setProjection(Projections.property("nivel")).uniqueResult();

        System.out.println(nivel.toString());

        return nivel;
    }

    @Override
    public List<Objeto> devolverTodosLosObjetosDeUnNivel(Long id) {
        Session session = sessionFactory.getCurrentSession();
        List<Objeto> objetos;

        objetos = session.createCriteria(NivelIntermedio.class)
                .add(Restrictions.eq("nivel.id", id))
                .add(Restrictions.isNotNull("objeto"))
                .setProjection(Projections.property("objeto"))
                .list();


        return objetos;
    }

    @Override
    public List<Enemigo> devolverTodosLosEnemigosDeUnNivel(Long id) {
        Session session = sessionFactory.getCurrentSession();
        List<Enemigo> enemigos;

        enemigos = session.createCriteria(NivelIntermedio.class)
                .add(Restrictions.eq("nivel.id", id))
                .add(Restrictions.isNotNull("enemigo"))
                .setProjection(Projections.property("enemigo"))
                .list();


        return enemigos;
    }


}
