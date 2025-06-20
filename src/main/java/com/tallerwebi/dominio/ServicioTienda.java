package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Objeto;

import java.util.List;

public interface ServicioTienda {
    List<Objeto> obtenerProductosDisponibles();
    Objeto buscarObjetoPorId(Long objetoId);
}
