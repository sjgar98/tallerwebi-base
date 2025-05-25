package com.tallerwebi.presentacion;

import com.tallerwebi.servicios.ServicioAjustes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ControladorAjustesTest {


    private ControladorAjustes controladorAjustes;
    private ServicioAjustes servicioAjustes;

    @BeforeEach
    public void init() {

        servicioAjustes = Mockito.mock(ServicioAjustes.class);

        controladorAjustes = new ControladorAjustes(servicioAjustes);
    }

    @Test
    public void testGetAjustesDevuelveVistaAjustes() {

        HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
        ModelAndView result = controladorAjustes.getAjustes(requestMock);

        assertNotNull(result);
        assertEquals("ajustes", result.getViewName());

    }
}
