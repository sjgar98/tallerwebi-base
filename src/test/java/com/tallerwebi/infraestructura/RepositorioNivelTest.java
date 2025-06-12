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
import static org.mockito.Mockito.mock;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioNivelTest {


    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioNivel repositorioNivel;

    private Nivel nivelEsperado;


    private void givenCreoEntorno(){

        Nivel n1 = new Nivel(1L,1,3,"Mazmorra",false);
        Nivel n2 = new Nivel(2L,3,5,"Bosque",false);
        Nivel n3 = new Nivel(3L,5,7,"Castillo",false);

        TipoObjeto tipo1 = new TipoObjeto();
        tipo1.setId(1L); tipo1.setNombre("Curacion");
        sessionFactory.getCurrentSession().save(tipo1);

        Objeto o1 = new Objeto();
        o1.setId(1L);
        o1.setNombre("Pocion Curacion");
        o1.setDescripcion("Cura un 25%");
        o1.setTipo(tipo1);
        o1.setRango(1);
        o1.setValor(500L);
        o1.setImagenSrc("potion-1");

        Objeto o2 = new Objeto();
        o2.setId(2L);
        o2.setNombre("Pocion Curacion Mayor");
        o2.setDescripcion("Cura un 50%");
        o2.setTipo(tipo1);
        o2.setRango(2);
        o2.setValor(750L);
        o2.setImagenSrc("potion-2");

        Objeto o3 = new Objeto();
        o3.setId(3L);
        o3.setNombre("Pocion Curacion Legendarioa");
        o3.setDescripcion("Cura un 100%");
        o3.setTipo(tipo1);
        o3.setRango(4);
        o3.setValor(1500L);
        o3.setImagenSrc("potion-3");

        Enemigo e1 = new Enemigo();
        e1.setId(1L);
        e1.setNombre("Slime");
        e1.setVidaActual(75);
        e1.setVidaMaxima(75);
        e1.setAtaque(5);
        e1.setDefensa(5);
        e1.setImagenSrc("Slime.png");

        Enemigo e2 = new Enemigo();
        e2.setId(2L);
        e2.setNombre("Esqueleto");
        e2.setVidaActual(100);
        e2.setVidaMaxima(100);
        e2.setAtaque(6);
        e2.setDefensa(7);
        e2.setImagenSrc("esqueleto.png");

        Enemigo e3 = new Enemigo();
        e3.setId(3L);
        e3.setNombre("Bandido");
        e3.setVidaActual(125);
        e3.setVidaMaxima(125);
        e3.setAtaque(7);
        e3.setDefensa(10);
        e3.setImagenSrc("bandido.png");

        NivelIntermedio nivInt1= new NivelIntermedio(1L,n1,o1,e1);
        NivelIntermedio nivInt2= new NivelIntermedio(2L,n1,o1,e1);
        NivelIntermedio nivInt3= new NivelIntermedio(3L,n2,o2,e1);
        NivelIntermedio nivInt4= new NivelIntermedio(4L,n2,o1,e2);
        NivelIntermedio nivInt5= new NivelIntermedio(5L,n3,o3,e3);

        sessionFactory.getCurrentSession().save(n1);
        sessionFactory.getCurrentSession().save(n2);
        sessionFactory.getCurrentSession().save(n3);
        sessionFactory.getCurrentSession().save(o1);
        sessionFactory.getCurrentSession().save(o2);
        sessionFactory.getCurrentSession().save(o3);
        sessionFactory.getCurrentSession().save(e1);
        sessionFactory.getCurrentSession().save(e2);
        sessionFactory.getCurrentSession().save(e3);
        sessionFactory.getCurrentSession().save(nivInt1);
        sessionFactory.getCurrentSession().save(nivInt2);
        sessionFactory.getCurrentSession().save(nivInt3);
        sessionFactory.getCurrentSession().save(nivInt4);
        sessionFactory.getCurrentSession().save(nivInt5);

    }

    private Nivel whenBuscoNivelPorId(Long id){

       return  repositorioNivel.devolverNivelPorId(id);
    }

    private List<NivelIntermedio> whenBuscoTodosLosNivelesIntermedios(){
        return  repositorioNivel.devolverTodosLosNivelesIntermedio();
    }

    private  List<Nivel> whenBuscoTodosLosNiveles(){
        return  repositorioNivel.devolverTodosLosNiveles();
    }

    private List<Objeto> whenBuscoTodosLosObjetosDeUnNivel(Long id){
        return  repositorioNivel.devolverTodosLosObjetosDeUnNivel(id);
    }

    private List<Enemigo> whenBuscoTodosLosEnemigosDeUnNivel(Long id){
        return  repositorioNivel.devolverTodosLosEnemigosDeUnNivel(id);
    }

    private void thenEncuentroNivelPorId(Nivel nivel) {
        assertThat(nivel, is(notNullValue()));
    }

    private void thenEncuentroListaNivelesIntermedio(List<NivelIntermedio> nivelesIntermedios) {
        assertThat(nivelesIntermedios, hasSize(5));
    }
    private void thenEncuentroListaNiveles(List<Nivel> niveles) {
        assertThat(niveles, hasSize(3));
    }
    private void thenEncuentroTodosLosObjetosDeUnNivel(List<Objeto> objetos) {
        Long cantidadDePocionCuracion = objetos.stream()
                .filter(objeto -> "Pocion Curacion".equals(objeto.getNombre()))
                .count();
        assertThat(cantidadDePocionCuracion, equalTo(2L));
    }

    private void thenEncuentroTodosLosEnemigosDeUnNivel(List<Enemigo> enemigos){
        Long cantidadDeSlimes = enemigos.stream()
                .filter(enemigo -> "Slime".equals(enemigo.getNombre()))
                .count();
        assertThat(cantidadDeSlimes, equalTo(2L));
    }


    @Test
    @Transactional
    @Rollback
    void funcionesNivel(){

        givenCreoEntorno();

        thenEncuentroNivelPorId(whenBuscoNivelPorId(1L));
        thenEncuentroListaNivelesIntermedio(whenBuscoTodosLosNivelesIntermedios());
        thenEncuentroListaNiveles(whenBuscoTodosLosNiveles());
    }

/*
    @Test
    @Transactional
    @Rollback
    void funcionesObjeto(){

        givenCreoEntorno();

        thenEncuentroTodosLosObjetosDeUnNivel(whenBuscoTodosLosObjetosDeUnNivel(1L));
    }





    @Test
    @Transactional
    @Rollback
    void funcionesEnemigos(){

        givenCreoEntorno();

        thenEncuentroTodosLosEnemigosDeUnNivel(whenBuscoTodosLosEnemigosDeUnNivel(1L));
    } */


}
