package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Objeto;

import java.util.List;

public interface RepositorioObjetos {
    List<Objeto> getAllObjetos();
    Objeto getObjetoById(Long objetoId);
    List<Objeto> getObjetosIniciales();
}
