package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Enemigo;
import com.tallerwebi.dominio.entidad.Nivel;
import com.tallerwebi.dominio.entidad.NivelIntermedio;
import com.tallerwebi.dominio.entidad.Objeto;
import org.hibernate.*;
import org.hibernate.criterion.CriteriaSpecification;
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
        return session.createCriteria(Nivel.class).list();
    }

    @Override
    public Nivel devolverNivelPorId(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Nivel.class);
        criteria.add(Restrictions.eq("id", id));

        return (Nivel) criteria.uniqueResult();
    }

    @Override
    public List<Objeto> devolverTodosLosObjetosDeUnNivel(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Nivel.class, "nivel");
        criteria.add(Restrictions.eq("nivel.id", id));
        criteria.setFetchMode("nivel.objetos", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        Nivel nivel = (Nivel) criteria.uniqueResult();
        return (nivel != null) ? nivel.getObjetos() : null;
    }

    @Override
    public List<Enemigo> devolverTodosLosEnemigosDeUnNivel(Long id) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Nivel.class, "nivel");
        criteria.add(Restrictions.eq("nivel.id", id));
        criteria.setFetchMode("nivel.enemigos", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);


        Nivel nivel = (Nivel) criteria.uniqueResult();
        return (nivel != null) ? nivel.getEnemigos() : null;


    }


}
