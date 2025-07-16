package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Habilidad;

import java.util.List;

public interface RepositorioHabilidades {

    List<Habilidad> obtenerTodasLasHabilidades();

    Habilidad obtenerHabilidadPorId(Long id);

    List<Habilidad> obtenerHabilidadesNivel1();

    List<Habilidad> buscarHabilidadesDelJugador(Long jugadorId);

    public List<Habilidad> obtenerHabilidadesPorEnemigoId(Long idEnemigo);
}
