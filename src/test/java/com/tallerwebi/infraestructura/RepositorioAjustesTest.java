package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.entidad.Ajustes;
import com.tallerwebi.dominio.entidad.Usuario;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioAjustesTest {



    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioAjustes repositorioAjustes;

    private Ajustes ajustesEsperados;

    private void givenCreoAjustesYUsuarios(){
        Ajustes a1 = new Ajustes("#FF02", 100,50,50,2);
        Ajustes a2 = new Ajustes("#FH02", 50,32,43,1);

        Usuario usuario1 = new Usuario("emailfalso1@gmail.com","12345","admin");
        Usuario usuario2 = new Usuario("emailreal1@gmail.com","34567","usuario");

        usuario1.setAjustes(a1);
        usuario2.setAjustes(a2);

        ajustesEsperados = a1;

        sessionFactory.getCurrentSession().save(usuario1);
        sessionFactory.getCurrentSession().save(usuario2);

    }

    private Ajustes whenBuscoAjustesPorUsuario(Usuario usuario){
        return repositorioAjustes.devolverAjustesDelUsuario(usuario);
    }

    private void thenEncuentroAjustes(Ajustes ajustesUsuario, Ajustes ajustes){
        assertThat(ajustesUsuario, equalTo(ajustes));
    }

    @Test
    @Transactional
    @Rollback
    public void obtenerAjustesDelUsuario(){

        Ajustes a1 = new Ajustes("#FF02", 100,50,50,2);
        Ajustes a2 = new Ajustes("#FH02", 50,32,43,1);

        Usuario usuario1 = new Usuario("emailfalso1@gmail.com","12345","admin");
        Usuario usuario2 = new Usuario("emailreal1@gmail.com","34567","usuario");

        usuario1.setAjustes(a1);
        usuario2.setAjustes(a2);

        a1.setUsuario(usuario1);
        a2.setUsuario(usuario2);

        ajustesEsperados = a1;

        sessionFactory.getCurrentSession().save(usuario1);
        sessionFactory.getCurrentSession().save(usuario2);

        thenEncuentroAjustes(whenBuscoAjustesPorUsuario(usuario1), a1);
    }

}
