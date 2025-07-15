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
    public void guardar(Jugador jugador) {
        this.sessionFactory.getCurrentSession().save(jugador);
    }

    @Override
    public void modificar(Jugador jugador) {
        this.sessionFactory.getCurrentSession().merge(jugador);
    }

    @Override
    public List<ObjetoInventario> buscarObjetosInventario(Long jugadorId) {
        return (List<ObjetoInventario>) sessionFactory.getCurrentSession().createCriteria(ObjetoInventario.class)
                .createAlias("jugador", "j")
                .add(Restrictions.eq("j.id", jugadorId))
                .list();
    }

    @Override
    public List<ObjetoInventario> buscarObjetosInventarioPorObjeto(Jugador jugador, Objeto objeto) {
        return (List<ObjetoInventario>) sessionFactory.getCurrentSession().createCriteria(ObjetoInventario.class)
                .createAlias("jugador", "j")
                .createAlias("objeto", "o")
                .add(Restrictions.eq("j.id", jugador.getId()))
                .add(Restrictions.eq("o.id", objeto.getId()))
                .list();
    }

    @Override
    public ObjetoInventario buscarObjetoInventarioPorTipo(Jugador jugador, String nombreTipoObjeto) {
        return (ObjetoInventario) sessionFactory.getCurrentSession().createCriteria(ObjetoInventario.class)
                .createAlias("objeto", "o")
                .createAlias("o.tipo", "t")
                .add(Restrictions.eq("t.nombre", nombreTipoObjeto))
                .add(Restrictions.eq("equipado", true))
                .uniqueResult();
    }

    @Override
    public ObjetoInventario buscarObjetoInventarioPorId(Long objetoInventarioId) {
        return (ObjetoInventario) sessionFactory.getCurrentSession().createCriteria(ObjetoInventario.class)
                .add(Restrictions.eq("id", objetoInventarioId))
                .uniqueResult();
    }

    @Override
    public void agregarObjeto(ObjetoInventario objeto) {
        this.sessionFactory.getCurrentSession().save(objeto);
    }

    @Override
    public void modificarObjeto(ObjetoInventario objeto) {
        this.sessionFactory.getCurrentSession().merge(objeto);
    }

    @Override
    public void removerObjeto(ObjetoInventario objeto) {
        this.sessionFactory.getCurrentSession().remove(objeto);
    }
}
