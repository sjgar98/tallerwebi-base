package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.*;


import java.util.List;


public interface ServicioNivel {




     List<Objeto> obtenerObjetosDeUnNivel(Long id);

     List<ObjetoInventario> obtenerObjetosInventario(Long id);

     List<Nivel> obtenerTodosLosNiveles();

     List<Enemigo> obtenerLosEnemigosDeUnNivel(Long id);

     List<NivelDTO> obtenerNivelesDTO(List<Nivel> niveles, Long opcionId);

     List<EnemigoDTO> obtenerEnemigosDto(List<Enemigo> enemigos);

     Nivel devolverNivelSeleccionado();

     void seleccionarNivel(Long id);

     NivelDTO obtenerNivelPorId(Long id);

}
