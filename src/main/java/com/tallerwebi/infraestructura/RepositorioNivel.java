package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Enemigo;
import com.tallerwebi.dominio.entidad.Nivel;
import com.tallerwebi.dominio.entidad.Objeto;

import java.util.List;

public interface RepositorioNivel {

    List<Nivel> devolverTodosLosNiveles();

    Nivel devolverNivelPorId(Long id);

    List<Objeto> devolverTodosLosObjetosDeUnNivel(Long id);

    List<Enemigo> devolverTodosLosEnemigosDeUnNivel(Long id);

}
