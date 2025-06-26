package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.EnemigoDTO;
import com.tallerwebi.dominio.entidad.Jugador;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ServicioCombate {


    void setCombate(HttpServletRequest request);
    Boolean estaVivo();
    Boolean gano();
    void ataqueJugador(Integer index);
    void ataqueEnemigo();
    void defensaJugador();
    Jugador getJugador();
    List<EnemigoDTO> getEnemigos();
    Long getRecompensaOro();
    Integer calcularExperiencia();

    void usarObjeto(Long id);

    void agregarTexto(String texto);
    String devolverTexto();

    void aplicarEfectoAlJugador(Long idEfecto);

    void descontarVidaJugadorPorEfecto();

    Boolean probabilidad50PorCiento();

    Boolean probabilidad40PorCiento();

}
