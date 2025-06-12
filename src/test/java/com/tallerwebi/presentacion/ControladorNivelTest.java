package com.tallerwebi.presentacion;

public class ControladorNivelTest {



    /*

    private ControladorNivel controladorNivel;
    private ServicioNivel servicioNivel;
    private JuegoService juegoService;
    private Model model;

    @BeforeEach
    public void init() {

        servicioNivel = Mockito.mock(ServicioNivel.class);
        juegoService = Mockito.mock(JuegoService.class);
        model = Mockito.mock(Model.class);

        controladorNivel = new ControladorNivel(servicioNivel, juegoService);
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
        verify(model, Mockito.times(1)).addAttribute(eq("niveles"), eq(niveles));

    } */

}
