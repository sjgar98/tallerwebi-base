package com.tallerwebi;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Service
public interface ServicioNivel {


     ArrayList<Nivel> niveles = new ArrayList<>();


     Nivel buscarNivelPorId(Integer id);
     void seleccionarNivel(Nivel nivel, Integer nivelJugador);
     void agregarNivel(Nivel nivel);
     Nivel devolverNivelSeleccionado();
     Nivel crearNivel(Integer id, Integer niv_min, Integer niv_enem_max, String desc, String recompensas, Boolean estado);


}
