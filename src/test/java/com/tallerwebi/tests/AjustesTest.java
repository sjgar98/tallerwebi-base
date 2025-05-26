package com.tallerwebi.tests;


import com.tallerwebi.servicios.Ajustes;
import com.tallerwebi.servicios.ServicioAjustes;
import com.tallerwebi.servicios.ServicioAjustesImpl;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AjustesTest {

    ServicioAjustes servicioAjustes = new ServicioAjustesImpl();

    @Test
    public void queSePuedanCrearAjustes(){
        Ajustes nuevosAjustes = new Ajustes("#00FF2A",30,50,13,2);
        thenSeCranAjustes(nuevosAjustes);
    }
    @Test
    public void queSePuedanGuardarAjustes(){
        servicioAjustes.guardarAjustes("#00FF2A",30,50,13,2);
    }

    @Test
    public void queDevuelvaLosAjustesGuardados(){

        servicioAjustes.guardarAjustes("#00FF2A",30,50,13,2);
        thenSeCranAjustes(servicioAjustes.devolverAjustes());
    }



    //mvn jetty:run

    private void thenSeCranAjustes(Ajustes ajustes){
        assertThat(ajustes, is(notNullValue()));
    }
}
