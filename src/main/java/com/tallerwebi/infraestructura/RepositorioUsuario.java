package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Usuario;

public interface RepositorioUsuario {

    Usuario buscarUsuario(String email, String password);
    void guardar(Usuario usuario);
    Usuario buscar(String email);
    Usuario buscarPorId(Long usuarioId);
    void modificar(Usuario usuario);
}
