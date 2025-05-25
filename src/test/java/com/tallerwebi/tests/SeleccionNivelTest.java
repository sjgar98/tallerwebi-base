package com.tallerwebi.tests;

import com.tallerwebi.presentacion.ControladorNivel;
import com.tallerwebi.servicios.Nivel;
import com.tallerwebi.servicios.ServicioNivel;
import com.tallerwebi.servicios.ServicioNivelImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class SeleccionNivelTest {

    private ServicioNivel servicioNivel;
    @BeforeEach
    public void init() {
        servicioNivel = new ServicioNivelImpl();
    }

    @Test
    public void queSePuedaCrearElNivel(){
        Nivel nuevoNivel = servicioNivel.crearNivel(1,2,5,"Mazmorra","Monedas de Oro",false);
        thenSeCreaElNivel(nuevoNivel);
    }

    @Test
    public void queSeGuardeElNivel(){
        Nivel nuevoNivel = servicioNivel.crearNivel(1,2,5,"Mazmorra","Monedas de Oro",false);
        whenGuardarNivel(nuevoNivel);
        thenSeCreaElNivel(servicioNivel.buscarNivelPorId(1));
    }

    @Test
    public void queSePuedaSeleccionarElNivel(){
        Nivel nuevoNivel = servicioNivel.crearNivel(1,2,5,"Mazmorra","Monedas de Oro",false);
        servicioNivel.agregarNivel(nuevoNivel);
        servicioNivel.seleccionarNivel(nuevoNivel, 3);
        assertTrue(servicioNivel.devolverNivelSeleccionado().getSeleccionado());


    }

    @Test
    public void queNoSePuedaSeleccionarUnNivelNoDesbloqueado(){
        Nivel nuevoNivel = servicioNivel.crearNivel(1,5,5,"Mazmorra","Monedas de Oro",false);
        servicioNivel.agregarNivel(nuevoNivel);
        servicioNivel.seleccionarNivel(nuevoNivel, 3);
        assertNull(servicioNivel.devolverNivelSeleccionado());
    }



    private void whenGuardarNivel(Nivel nivel){
        servicioNivel.agregarNivel(nivel);
    }

    private void thenSeCreaElNivel(Nivel nivelCreado){
       assertThat(nivelCreado, is(notNullValue()));
    }


}
