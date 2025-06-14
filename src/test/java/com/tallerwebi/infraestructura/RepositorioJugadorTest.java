package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioJugadorTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    RepositorioJugador repositorio;

    private Usuario usuarioMock;
    private Jugador jugadorMock;

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarJugadorPorUserId() {
        givenHayUnJugadorExistente();
        Jugador resultado = whenBuscoJugadorPorUserId();
        thenEncuentroJugador(resultado);
    }
    private void givenHayUnJugadorExistente() {
        Usuario usuarioMock = new Usuario();
        sessionFactory.getCurrentSession().save(usuarioMock);
        jugadorMock = new Jugador().setUsuario(usuarioMock).setNombre("MockPlayer");
        sessionFactory.getCurrentSession().save(jugadorMock);
    }
    private Jugador whenBuscoJugadorPorUserId() {
        return repositorio.buscar(jugadorMock.getUsuario().getId());
    }
    private void thenEncuentroJugador(Jugador resultado) {
        assertThat(resultado.getId(), equalTo(1L));
        assertThat(resultado.getNombre(), equalTo(jugadorMock.getNombre()));
        assertThat(resultado.getUsuario().getId(), equalTo(jugadorMock.getUsuario().getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnJugadorNuevo() {
        givenTengoUnJugadorNuevoSinGuardar();
        whenGuardoJugador(jugadorMock);
        thenJugadorQuedaGuardado();
    }
    private void givenTengoUnJugadorNuevoSinGuardar() {
        usuarioMock = new Usuario();
        sessionFactory.getCurrentSession().save(usuarioMock);
        jugadorMock = new Jugador().setNombre("JugadorMock").setUsuario(usuarioMock);
    }
    private void whenGuardoJugador(Jugador jugador) {
        repositorio.guardar(jugador);
    }
    private void thenJugadorQuedaGuardado() {
        var resultado = repositorio.buscar(usuarioMock.getId());
        assertThat(resultado.getNombre(), equalTo("JugadorMock"));
    }

    @Test
    @Transactional
    @Rollback
    public void puedoModificarUnJugadorExistente() {
        givenTengoUnJugadorExistente();
        whenModificoJugador(jugadorMock);
        thenJugadorQuedaModificado();
    }
    private void givenTengoUnJugadorExistente() {
        var tipoMock = new TipoObjeto().setNombre("Generico");
        sessionFactory.getCurrentSession().save(tipoMock);
        var objetoMock = new Objeto().setNombre("ObjetoMock").setTipo(tipoMock);
        sessionFactory.getCurrentSession().save(objetoMock);

        usuarioMock = new Usuario();
        sessionFactory.getCurrentSession().save(usuarioMock);
        jugadorMock = new Jugador()
                .setNombre("JugadorMock")
                .setUsuario(usuarioMock)
                .setDinero(0L);

        sessionFactory.getCurrentSession().save(jugadorMock);
        sessionFactory.getCurrentSession().save(
                new ObjetoInventario().setObjeto(objetoMock).setJugador(jugadorMock)
        );
    }
    private void whenModificoJugador(Jugador jugador) {
        jugador.setDinero(500L);
        repositorio.modificar(jugador);
    }
    private void thenJugadorQuedaModificado() {
        var resultado = repositorio.buscar(usuarioMock.getId());
        assertThat(resultado.getNombre(), equalTo("JugadorMock"));
        assertThat(resultado.getDinero(), equalTo(500L));
    }

    @Test
    @Transactional
    @Rollback
    public void puedoTraerObjetosDeJugador() {
        givenTengoUnJugadorExistente();
        var resultado = whenBuscoObjetosPorJugador(jugadorMock);
        thenReciboListaDeObjetos(resultado);
    }
    private List<ObjetoInventario> whenBuscoObjetosPorJugador(Jugador jugador) {
        return repositorio.buscarObjetosInventario(jugador.getId());
    }
    private void thenReciboListaDeObjetos(List<ObjetoInventario> resultado) {
        assertThat(resultado.size(), equalTo(1));
    }
}
