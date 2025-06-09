package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Objeto;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioObjetos")
public class RepositorioObjetosImpl implements RepositorioObjetos {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioObjetosImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Objeto> getAllObjetos() {
        return sessionFactory.getCurrentSession().createCriteria(Objeto.class).list();
    }

    @Override
    public Objeto getObjetoById(Long objetoId) {
        return (Objeto) sessionFactory.getCurrentSession().createCriteria(Objeto.class)
                .add(Restrictions.eq("id", objetoId))
                .uniqueResult();
    }

    @Override
    public List<Objeto> getObjetosIniciales() {
        return sessionFactory.getCurrentSession().createCriteria(Objeto.class)
                .add(Restrictions.eq("rango", 1))
                .createAlias("tipo", "t")
                .add(Restrictions.eq("t.nombre", "Consumible"))
                .list();
    }
}
