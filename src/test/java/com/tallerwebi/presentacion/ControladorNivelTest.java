package com.tallerwebi.presentacion;

import com.tallerwebi.servicios.Nivel;

import com.tallerwebi.servicios.ServicioNivel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;



import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class ControladorNivelTest {


    private ControladorNivel controladorNivel;
    private ServicioNivel servicioNivel;
    private Model model;

    @BeforeEach
    public void init() {

        servicioNivel = Mockito.mock(ServicioNivel.class);
        model = Mockito.mock(Model.class);

        controladorNivel = new ControladorNivel(servicioNivel);
    }

    @Test
    public void testGetAjustesDevuelveVistaAjustes() {

        ArrayList<Nivel> niveles = servicioNivel.devolverTodosLosNiveles();

        Mockito.when(servicioNivel.devolverTodosLosNiveles()).thenReturn(niveles);

        HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
        ModelAndView result = controladorNivel.getSelecionNivel(requestMock, model);

        assertNotNull(result);
        assertEquals("seleccion-nivel", result.getViewName());

        verify(servicioNivel, Mockito.times(2)).devolverTodosLosNiveles();

        // 3. Verificar que la lista de niveles fue añadida al modelo con la clave correcta
        // Esto verifica que model.addAttribute("niveles", niveles) fue llamado
        verify(model, Mockito.times(1)).addAttribute(eq("niveles"), eq(niveles));

    }

}
