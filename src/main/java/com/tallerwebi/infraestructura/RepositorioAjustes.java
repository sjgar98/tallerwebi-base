package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Ajustes;
import com.tallerwebi.dominio.entidad.Usuario;

public interface RepositorioAjustes {

    Ajustes devolverAjustesDelUsuario(Usuario usuario);
}
