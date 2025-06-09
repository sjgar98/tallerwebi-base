package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Anuncio;

import java.util.List;

public interface RepositorioHome {
    List<Anuncio> getAnuncios();
}
