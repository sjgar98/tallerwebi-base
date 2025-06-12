package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Ajustes;
import com.tallerwebi.dominio.entidad.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioAjustesImpl implements  RepositorioAjustes{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Ajustes devolverAjustesDelUsuario(Usuario usuario) {
       var session = sessionFactory.getCurrentSession();

       return (Ajustes) session.createCriteria(Ajustes.class).add((Restrictions.eq("usuario", usuario))).uniqueResult();
    }
}
