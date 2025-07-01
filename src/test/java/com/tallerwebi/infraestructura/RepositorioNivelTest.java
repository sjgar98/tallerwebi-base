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
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioNivelTest {

    /*

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RepositorioNivelImpl repositorioNivel;

    @Test
    @Transactional
    @Rollback
    public void queAlPedirTodosLosNivelesIntermedioDevuelvaLaListaCompleta() {
        // Preparar datos
        Nivel nivel1 = new Nivel();
        nivel1.setNombre("Nivel Test");
        sessionFactory.getCurrentSession().save(nivel1);

        TipoObjeto tipoConsumible = new TipoObjeto();
        tipoConsumible.setNombre("Consumible");
        sessionFactory.getCurrentSession().save(tipoConsumible);

        Objeto pocion = new Objeto();
        pocion.setNombre("Poción");
        pocion.setTipo(tipoConsumible);
        sessionFactory.getCurrentSession().save(pocion);

        NivelIntermedio ni1 = new NivelIntermedio();
        ni1.setNivel(nivel1);
        ni1.setObjeto(pocion);
        sessionFactory.getCurrentSession().save(ni1);

        NivelIntermedio ni2 = new NivelIntermedio();
        ni2.setNivel(nivel1);
        ni2.setObjeto(pocion);
        sessionFactory.getCurrentSession().save(ni2);

        // Test
        List<NivelIntermedio> listaObtenida = repositorioNivel.devolverTodosLosNivelesIntermedio();

        // Validar
        assertThat(listaObtenida, is(not(empty())));
        assertThat(listaObtenida, hasItems(ni1, ni2));
    }

    @Test
    @Transactional
    @Rollback
    public void queAlPedirTodosLosNivelesDevuelvaUnaListaDeNivelesUnicos() {
        Nivel nivel1 = new Nivel();
        nivel1.setNombre("Bosque Tenebroso");
        sessionFactory.getCurrentSession().save(nivel1);

        Nivel nivel2 = new Nivel();
        nivel2.setNombre("Cueva Helada");
        sessionFactory.getCurrentSession().save(nivel2);

        NivelIntermedio nivelIntermedio1 = new NivelIntermedio();
        nivelIntermedio1.setNivel(nivel1);
        NivelIntermedio nivelIntermedio2 = new NivelIntermedio();
        nivelIntermedio2.setNivel(nivel2);
        sessionFactory.getCurrentSession().save(nivelIntermedio1);
        sessionFactory.getCurrentSession().save(nivelIntermedio2);

        List<Nivel> listaObtenida = repositorioNivel.devolverTodosLosNiveles();

        assertThat(listaObtenida.size(), greaterThanOrEqualTo(2));
        assertThat(listaObtenida, hasItems(nivel1, nivel2));
    }

    @Test
    @Transactional
    @Rollback
    public void queAlPedirUnNivelPorIdDevuelvaElNivelCorrecto() {

        Nivel nivel = new Nivel();
        nivel.setNombre("Nivel buscado");
        sessionFactory.getCurrentSession().save(nivel);

        NivelIntermedio nivelIntermedio1 = new NivelIntermedio();
        nivelIntermedio1.setNivel(nivel);
        sessionFactory.getCurrentSession().save(nivelIntermedio1);

        Nivel resultado = repositorioNivel.devolverNivelPorId(nivel.getId());

        assertThat(resultado, notNullValue());
        assertThat(resultado.getId(), equalTo(nivel.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void queAlPedirObjetosDeUnNivelDevuelvaLaListaDeObjetosCorrecta() {

        Nivel nivel = new Nivel();
        nivel.setNombre("Nivel Objetos");
        sessionFactory.getCurrentSession().save(nivel);

        TipoObjeto tipoConsumible = new TipoObjeto();
        tipoConsumible.setNombre("Consumible");
        sessionFactory.getCurrentSession().save(tipoConsumible);

        TipoObjeto tipoArma = new TipoObjeto();
        tipoArma.setNombre("Arma");
        sessionFactory.getCurrentSession().save(tipoArma);

        Objeto obj1 = new Objeto();
        obj1.setNombre("Poción de vida");
        obj1.setTipo(tipoConsumible);
        obj1.setConsumible(true);
        obj1.setRecuperacionVida(50.0);
        sessionFactory.getCurrentSession().save(obj1);

        Objeto obj2 = new Objeto();
        obj2.setNombre("Espada de madera");
        obj2.setTipo(tipoArma);
        obj2.setEquipable(true);
        sessionFactory.getCurrentSession().save(obj2);

        NivelIntermedio nivelIntermedio1 = new NivelIntermedio();
        nivelIntermedio1.setNivel(nivel);
        nivelIntermedio1.setObjeto(obj1);
        sessionFactory.getCurrentSession().save(nivelIntermedio1);

        NivelIntermedio nivelIntermedio2 = new NivelIntermedio();
        nivelIntermedio2.setNivel(nivel);
        nivelIntermedio2.setObjeto(obj2);
        sessionFactory.getCurrentSession().save(nivelIntermedio2);

        List<Objeto> objetos = repositorioNivel.devolverTodosLosObjetosDeUnNivel(nivel.getId());

        assertThat(objetos.size(), greaterThanOrEqualTo(2));
        assertThat(objetos, hasItems(obj1, obj2));
    }


    @Test
    @Transactional
    @Rollback
    public void queAlPedirEnemigosDeUnNivelDevuelvaLaListaDeEnemigosCorrecta() {
        Nivel nivel = new Nivel();
        nivel.setNombre("Nivel Enemigos");
        sessionFactory.getCurrentSession().save(nivel);

        Enemigo enemigo1 = new Enemigo();
        enemigo1.setNombre("Goblin");
        sessionFactory.getCurrentSession().save(enemigo1);

        Enemigo enemigo2 = new Enemigo();
        enemigo2.setNombre("Orco");
        sessionFactory.getCurrentSession().save(enemigo2);

        NivelIntermedio nivelIntermedio1 = new NivelIntermedio();
        nivelIntermedio1.setNivel(nivel);
        nivelIntermedio1.setEnemigo(enemigo1);
        sessionFactory.getCurrentSession().save(nivelIntermedio1);

        NivelIntermedio nivelIntermedio2 = new NivelIntermedio();
        nivelIntermedio2.setNivel(nivel);
        nivelIntermedio2.setEnemigo(enemigo2);
        sessionFactory.getCurrentSession().save(nivelIntermedio2);


        List<Enemigo> enemigos = repositorioNivel.devolverTodosLosEnemigosDeUnNivel(nivel.getId());

        assertThat(enemigos.size(), greaterThanOrEqualTo(2));
        assertThat(enemigos, hasItems(enemigo1, enemigo2));
    }

    @Test
    @Transactional
    @Rollback
    public void queAlPedirEnemigosDeUnNivelQueNoTieneEnemigosDevuelvaUnaListaVacia() {
        Nivel nivel = new Nivel();
        nivel.setNombre("Nivel Sin Enemigos");
        sessionFactory.getCurrentSession().save(nivel);

        List<Enemigo> enemigos = repositorioNivel.devolverTodosLosEnemigosDeUnNivel(nivel.getId());

        assertThat(enemigos, is(empty()));
    }  */
}
