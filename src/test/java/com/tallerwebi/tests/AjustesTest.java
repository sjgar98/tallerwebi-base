package com.tallerwebi.tests;


import com.tallerwebi.Ajustes;
import com.tallerwebi.Nivel;
import com.tallerwebi.ServicioAjustes;
import com.tallerwebi.ServicioAjustesImpl;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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


    private void thenSeCranAjustes(Ajustes ajustes){
        assertThat(ajustes, is(notNullValue()));
    }
}
