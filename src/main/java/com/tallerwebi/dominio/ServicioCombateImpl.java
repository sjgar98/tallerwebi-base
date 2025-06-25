package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.infraestructura.RepositorioEfectos;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ServicioCombateImpl implements ServicioCombate {




    private final ServicioNivel servicioNivel;
    private final ServicioJugador servicioJugador;
    private final RepositorioObjetos repositorioObjetos;
    private final RepositorioEfectos repositorioEfectos;

    private String panel = "";
    Combate combate;


    @Autowired
    public ServicioCombateImpl(RepositorioEfectos repositorioEfectos, ServicioNivel servicioNivel, ServicioJugador servicioJugador, RepositorioObjetos repositorioObjetos){
        this.repositorioEfectos = repositorioEfectos;
        this.servicioNivel = servicioNivel;
        this.servicioJugador = servicioJugador;
        this.repositorioObjetos = repositorioObjetos;
        combate = new Combate();


    }

    @Override
    public void setCombate(HttpServletRequest request) {
        var userId =request.getSession().getAttribute("userId");

        if(userId!= null){
            combate.setJugador(this.servicioJugador.getJugadorActual((Long) userId));
            combate.setEnemigos(servicioNivel.obtenerEnemigosDto(servicioNivel.obtenerLosEnemigosDeUnNivel(servicioNivel.devolverNivelSeleccionado().getId())));
            combate.setRecompensaOro(100L);
        }

    }

    @Override
    public Boolean estaVivo() {
       if (!combate.estaVivo()){
           if (combate.gano()){
               repositorioEfectos.vaciarListaEfectos(servicioJugador.getJugadorActual(combate.getJugador().getId()));
           }
       }
        return combate.estaVivo();
    }

    @Override
    public Boolean gano() {
        if (combate.gano()){
            repositorioEfectos.vaciarListaEfectos(servicioJugador.getJugadorActual(combate.getJugador().getId()));
        }

        return combate.gano();
    }

    @Override
    public void ataqueJugador(Integer index) {

        combate.ataqueJugador(index);
        agregarTexto("Jugador: " + combate.getJugador().getNombre() + " realizo ATAQUE sobre: " + combate.getEnemigos().get(index).getNombre());

    }

    @Override
    public void ataqueEnemigo() {

        combate.ataqueEnemigo();
        for (int i = 0; i < combate.getEnemigos().size(); i++){

            agregarTexto("Enemigo: " + combate.getEnemigos().get(i).getNombre() + " realizo ATAQUE al jugador");
        }

        descontarVidaJugadorPorEfecto();

    }

    @Override
    public void defensaJugador() {
        combate.defensaJugador();
        for (int i = 0; i < combate.getEnemigos().size(); i++){
            agregarTexto("Enemigo: " + combate.getEnemigos().get(i).getNombre() + " realizo ATAQUE al jugador");
        }

    }

    @Override
    public Jugador getJugador() {
       return combate.getJugador();
    }

    @Override
    public List<EnemigoDTO> getEnemigos() {
        return combate.getEnemigos();
    }

    @Override
    public Long getRecompensaOro() {
      return combate.getRecompensaOro();
    }

    @Override
    public Integer calcularExperiencia() {

        Integer experiencia = 0;
        for (int i = 0; i < combate.getEnemigos().size(); i++){
            experiencia+= (combate.getEnemigos().get(i).getNivel() * 15);
        }
        return experiencia;
    }

    @Override
    public void usarObjeto(Long id) {

        Jugador jugador  = combate.getJugador();
        Objeto objetoAUsar = repositorioObjetos.getObjetoById(id);


        agregarTexto("Jugador: " + combate.getJugador().getNombre() + " consumio: " + objetoAUsar.getNombre());
        jugador.setVidaActual((int) (jugador.getVidaActual() + (jugador.getVidaActual() * objetoAUsar.getRecuperacionVida())));

        if (jugador.getVidaActual() > jugador.getVidaMaxima()){
            jugador.setVidaActual(jugador.getVidaMaxima());
        }



        servicioJugador.sacarObjetosAlJugador(objetoAUsar,jugador.getId());

    }

    @Override
    public void agregarTexto(String texto) {
        this.panel += texto +"\n";
        System.out.println(panel);
    }

    @Override
    public String devolverTexto() {
        System.out.println(panel);
      return  this.panel;
    }

    @Override
    public void aplicarEfectoAlJugador(Long idEfecto) {
        Efecto efectoAAplicar = repositorioEfectos.obtenerEfectoPorId(idEfecto);
        repositorioEfectos.aplicarEfectoAlJugador(combate.getJugador(), efectoAAplicar);

    }

    @Override
    public void descontarVidaJugadorPorEfecto() {

        Jugador jugador = servicioJugador.getJugadorActual(combate.getJugador().getId());

        for (int i = 0; i < jugador.getEfectosActivos().size(); i++){
            if (jugador.getEfectosActivos().get(i).getNombre().equals("Veneno")){

                combate.getJugador().setVidaActual(combate.getJugador().getVidaActual() - repositorioEfectos.obtenerEfectoPorId(1L).getDanioPorTurno());
                jugador.getEfectosActivos().get(i).setDuracionActual( jugador.getEfectosActivos().get(i).getDuracionActual() - 1);
                agregarTexto("El jugador recibio el efecto de Veneno: Le hizo " + repositorioEfectos.obtenerEfectoPorId(1L).getDanioPorTurno());
            } else if (jugador.getEfectosActivos().get(i).getNombre().equals("Sangrado")){
                combate.getJugador().setVidaActual(combate.getJugador().getVidaActual() - repositorioEfectos.obtenerEfectoPorId(2L).getDanioPorTurno());
                jugador.getEfectosActivos().get(i).setDuracionActual( jugador.getEfectosActivos().get(i).getDuracionActual() - 1);
                agregarTexto("El jugador recibio el efecto de Sangrado: Le hizo " + repositorioEfectos.obtenerEfectoPorId(2L).getDanioPorTurno());
            }

            if (jugador.getEfectosActivos().get(i).getDuracionActual() <= 0){
                repositorioEfectos.removerEfectoDeJugador(jugador, jugador.getEfectosActivos().get(i));
            }
        }
    }

    @Override
    public Boolean probabilidad50PorCiento() {
        Random random = new Random();
        return random.nextDouble() < 0.5;
    }

    @Override
    public Boolean probabilidad40PorCiento() {
        Random random = new Random();
        return random.nextDouble() < 0.4;
    }


}
