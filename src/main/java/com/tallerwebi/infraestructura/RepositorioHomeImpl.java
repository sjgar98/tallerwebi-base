package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Anuncio;
import com.tallerwebi.dominio.entidad.Objeto;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioHome")
public class RepositorioHomeImpl implements RepositorioHome {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioHomeImpl(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    public List<Anuncio> getAnuncios() {
        List<Anuncio> anuncios = (List<Anuncio>) sessionFactory.getCurrentSession().createCriteria(Anuncio.class)
                .addOrder(Order.desc("id"))
                .list();
        return anuncios;
    }
}
