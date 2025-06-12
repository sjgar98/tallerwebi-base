package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.*;


import java.util.List;


public interface ServicioNivel {



     List<NivelIntermedio> obtenerNivelesIntermedios();
     List<ObjetoInventario> obtenerObjetosDeUnNivel(Long id);
     List<Nivel> obtenerTodosLosNiveles();
     List<Enemigo> obtenerLosEnemigosDeUnNivel(Long id);
     List<NivelDTO> obtenerNivelesDTO(List<Nivel> niveles, Long opcionId);
     List<EnemigoDTO> obtenerEnemigosDto(List<Enemigo> enemigos);

     Nivel devolverNivelSeleccionado();
     void seleccionarNivel(Long id);

}
