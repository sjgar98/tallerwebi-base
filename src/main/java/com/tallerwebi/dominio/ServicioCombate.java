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


    JugadorDTO getJugador();

    List<EnemigoDTO> getEnemigos();

    Long getRecompensaOro();

    Integer calcularExperiencia();

    void usarObjeto(Long id);

    void agregarTexto(String texto);

    String devolverTexto();

    void aplicarEfectoAlJugador(Long idEfecto);

    void descontarVidaJugadorPorEfecto();

    Boolean probabilidadAplicarEfecto(EnemigoDTO enemigoDTO);

    JugadorDTO crearJugadorCombate(Jugador jugador);

    List<HabilidadDTO> crearHabilidadesDTO(List<Habilidad> habilidades);


}
