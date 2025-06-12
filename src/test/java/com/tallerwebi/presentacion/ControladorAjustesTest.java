package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioAjustes;
import com.tallerwebi.dominio.entidad.Ajustes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ControladorAjustesTest {

    private ControladorAjustes controladorAjustes;
    private ServicioAjustes mockServicioAjustes;
    private HttpServletRequest mockRequest;
    private HttpSession mockSession;

    @BeforeEach
    public void init() {
        mockServicioAjustes = mock(ServicioAjustes.class);
        mockRequest = mock(HttpServletRequest.class);
        mockSession = mock(HttpSession.class);

        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getSession(false)).thenReturn(mockSession);

        controladorAjustes = new ControladorAjustes(mockServicioAjustes);
    }


    @Test
    public void getAjustesSiUsuarioEstaLogueadoDeberiaDevolverVistaAjustes() {

        when(mockSession.getAttribute("userId")).thenReturn(1L);


        ModelAndView modelAndView = controladorAjustes.getAjustes(mockRequest);

        assertNotNull(modelAndView);
        assertEquals("ajustes", modelAndView.getViewName());
        verify(mockRequest, times(1)).getSession();
        verify(mockSession, times(1)).getAttribute("userId");
    }

    @Test
    public void getAjustesSiUsuarioNoEstaLogueadoDeberiaRedirigirALogin() {

        when(mockSession.getAttribute("userId")).thenReturn(null);

        ModelAndView modelAndView = controladorAjustes.getAjustes(mockRequest);


        assertNotNull(modelAndView);
        assertEquals("redirect:/login", modelAndView.getViewName());
        verify(mockRequest, times(1)).getSession();
        verify(mockSession, times(1)).getAttribute("userId");
    }



}