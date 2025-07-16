package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ServicioCombate {


    void setCombate(HttpServletRequest request);

    Boolean estaVivo();

    Boolean gano();

    void ataqueJugador(Integer index, Long idHabilidad);

    void ataqueEnemigo();

    void defensaJugador();

    List<Habilidad> habilidadesJugador();

    Jugador getJugador();
    Integer getAtaqueTotal();

    List<EfectoAplicado> obtenerEfectosDelJugador();

    List<EnemigoDTO> getEnemigos();

    Long getRecompensaOro();

    Integer calcularExperiencia();

    void usarObjeto(Long id);

    void agregarTexto(String texto);

    String devolverTexto();

    void aplicarEfectoAlJugador(Long idEfecto, EnemigoDTO enemigoDTO);

    void descontarVidaJugadorPorEfecto();

    Boolean probabilidadAplicarEfecto(EnemigoDTO enemigoDTO);

    void aplicarEfectoAlEnemigo(Integer index,Long idHabilidad);

    List<Efecto> buscarEfectoPorHabilidad(Long idHabilidad);

    void descontarVidaEnemigosPorEfecto();

    void usarHabilidad(Integer index, Long idHabilidad);

    void agregarRecompensasAlJugador(Jugador jugador, List<Objeto> objetos);

    Boolean probabilidadUsarHabilidad(EnemigoDTO enemigoDTO);
    void usarHabilidadEnemigo(EnemigoDTO enemigoDTO);

}
