package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Anuncio;
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
import static org.hamcrest.Matchers.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioHomeTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    RepositorioHome repositorio;

    @Test
    @Transactional
    @Rollback
    public void puedoObtenerAnuncios() {
        givenHayAnunciosCreados();
        List<Anuncio> resultado = whenBuscoListaDeAnuncios();
        thenReciboListaCompleta(resultado);
    }
    private void givenHayAnunciosCreados() {
        Anuncio anuncioMock = new Anuncio().setTitle("Anuncio de Prueba").setContent("Contenido de Prueba");
        sessionFactory.getCurrentSession().save(anuncioMock);
    }
    private List<Anuncio> whenBuscoListaDeAnuncios() {
        return repositorio.getAnuncios();
    }
    private void thenReciboListaCompleta(List<Anuncio> resultado) {
        assertThat(resultado.size(), equalTo(1));
        assertThat(resultado.get(0).getTitle(), equalTo("Anuncio de Prueba"));
    }
}
