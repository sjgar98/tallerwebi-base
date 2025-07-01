package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.infraestructura.RepositorioEfectos;
import com.tallerwebi.infraestructura.RepositorioHabilidades;
import com.tallerwebi.infraestructura.RepositorioJugador;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioCombateImpl implements ServicioCombate {




    private final ServicioNivel servicioNivel;
    private final ServicioJugador servicioJugador;
    private final RepositorioObjetos repositorioObjetos;
    private final RepositorioEfectos repositorioEfectos;
    private final RepositorioHabilidades repositorioHabilidades;

    private String panel = "";
    Combate combate;


    @Autowired
    public ServicioCombateImpl(RepositorioEfectos repositorioEfectos, ServicioNivel servicioNivel, ServicioJugador servicioJugador, RepositorioObjetos repositorioObjetos, RepositorioHabilidades repositorioHabilidades){
        this.repositorioEfectos = repositorioEfectos;
        this.servicioNivel = servicioNivel;
        this.servicioJugador = servicioJugador;
        this.repositorioObjetos = repositorioObjetos;
        this.repositorioHabilidades = repositorioHabilidades;
        combate = new Combate();


    }

    @Override
    public void setCombate(HttpServletRequest request) {
        var userId =request.getSession().getAttribute("userId");

        if(userId!= null){
            combate.setJugador(crearJugadorCombate(servicioJugador.getJugadorActual((Long) userId)));
            combate.setEnemigos(servicioNivel.obtenerEnemigosDto(servicioNivel.obtenerLosEnemigosDeUnNivel(servicioNivel.devolverNivelSeleccionado().getId())));
            combate.setRecompensaOro(100L);
        }

    }

    @Override
    public Boolean estaVivo() {

       if (!combate.estaVivo()) {
           panel = "";
       }
        return combate.estaVivo();
    }

    @Override
    public Boolean gano() {
        if (combate.gano()){
            panel = "";
        }

        return combate.gano();
    }

    @Override
    public void ataqueJugador(Integer index, Long idHabilidad) {

        if (idHabilidad == null){
            combate.ataqueJugador(index);
            agregarTexto("Jugador: " + combate.getJugador().getNombre() + " realizo ATAQUE sobre: " + combate.getEnemigos().get(index).getNombre());
        } else {
            combate.usarHabilidad(index, idHabilidad);
            combate.aplicarEfectoAlEnemigo(index,idHabilidad);

            System.out.println("Enemigo recicibo" + combate.getEnemigos().get(index).getEfectosRecibidos().get(0).getNombre());
            agregarTexto("Jugador: " + combate.getJugador().getNombre() + " realizo " + combate.buscarHabilidadPorId(idHabilidad).getNombre() + " sobre: " + combate.getEnemigos().get(index).getNombre());
        }

        if (combate.descontarVidaEnemigosPorEfecto().equals("norecibioefecto")){

        } else {
            agregarTexto(combate.descontarVidaEnemigosPorEfecto());
        }

        gano();
        estaVivo();


    }

    @Override
    public void ataqueEnemigo() {

        combate.ataqueEnemigo();
        for (int i = 0; i < combate.getEnemigos().size(); i++){

            agregarTexto("Enemigo: " + combate.getEnemigos().get(i).getNombre() + " realizo ATAQUE al jugador");
            if (probabilidadAplicarEfecto(combate.getEnemigos().get(i))){

                if (combate.getEnemigos().get(i).getEfecto() != null && combate.getEnemigos().get(i).getVidaActual() > 0){

                    if (combate.getJugador().getEfectos().contains(combate.getEnemigos().get(i).getEfecto())){

                    } else {
                        aplicarEfectoAlJugador(combate.getEnemigos().get(i).getEfecto().getId());
                        agregarTexto("Enemigo: " + combate.getEnemigos().get(i).getNombre() + " le aplico el efecto " + combate.getEnemigos().get(i).getEfecto().getNombre() + " al jugador");
                    }
                }



            }
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
    public JugadorDTO getJugador() {
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

        JugadorDTO jugador  = combate.getJugador();
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
        this.panel += "-------------------------------------------------------" + "\n"+ texto +"\n";
        System.out.println(panel);
    }

    @Override
    public String devolverTexto() {
        System.out.println(panel);
      return  this.panel;
    }

    @Override
    public void aplicarEfectoAlJugador(Long idEfecto) {
       JugadorDTO jugador = combate.getJugador();
       EfectoDTO efecto = new EfectoDTO(repositorioEfectos.obtenerEfectoPorId(idEfecto));

       jugador.getEfectos().add(efecto);


    }

    @Override
    public void descontarVidaJugadorPorEfecto() {

        JugadorDTO jugador = combate.getJugador();

        for (int i = 0; i < jugador.getEfectos().size(); i++){
            if (jugador.getEfectos().get(i).getNombre().equals("Veneno")){

                combate.getJugador().setVidaActual(combate.getJugador().getVidaActual() - jugador.getEfectos().get(i).getDanioPorTurno());
                jugador.getEfectos().get(i).setDuracionActual( jugador.getEfectos().get(i).getDuracionActual() - 1);
                agregarTexto("El jugador recibio el efecto de Veneno: Le hizo " + repositorioEfectos.obtenerEfectoPorId(jugador.getEfectos().get(i).getId()).getDanioPorTurno());
            } else if (jugador.getEfectos().get(i).getNombre().equals("Sangrado")){
                combate.getJugador().setVidaActual(combate.getJugador().getVidaActual() - repositorioEfectos.obtenerEfectoPorId(jugador.getEfectos().get(i).getId()).getDanioPorTurno());
                jugador.getEfectos().get(i).setDuracionActual( jugador.getEfectos().get(i).getDuracionActual() - 1);
                agregarTexto("El jugador recibio el efecto de Sangrado: Le hizo " + repositorioEfectos.obtenerEfectoPorId(jugador.getEfectos().get(i).getId()).getDanioPorTurno());
            }

            if (jugador.getEfectos().get(i).getDuracionActual() <= 0){
                jugador.getEfectos().remove(i);
            }
        }
    }

    @Override
    public Boolean probabilidadAplicarEfecto(EnemigoDTO enemigoDTO) {
        Random random = new Random();
        return random.nextDouble() < enemigoDTO.getProbabilidadAplicarEfecto();
    }


    @Override
    public JugadorDTO crearJugadorCombate(Jugador jugador) {

        JugadorDTO jugadorDTO = new JugadorDTO(jugador);

        List<Habilidad> habilidades = repositorioHabilidades.buscarHabilidadesDelJugador(jugador.getId());

        List<HabilidadDTO> habilidadDTOS = crearHabilidadesDTO(habilidades);
        System.out.println("Cantidad de habilidades" + habilidadDTOS.size());
        jugadorDTO.setHabilidades(habilidadDTOS);

        return jugadorDTO;
    }

    @Override
    public List<HabilidadDTO> crearHabilidadesDTO(List<Habilidad> habilidades) {

        List<HabilidadDTO> habilidadDTOList = habilidades.stream()
                .map(habilidad -> {
                    HabilidadDTO habilidadDTO = new HabilidadDTO(
                            habilidad.getId(),
                            habilidad.getNombre(),
                            habilidad.getTipo(),
                            habilidad.getNivel(),
                            habilidad.getConsumoMana(),
                            habilidad.getDanio()
                    );


                    if (habilidad.getEfectos() != null && !habilidad.getEfectos().isEmpty()) {
                        habilidad.getEfectos().forEach(efecto -> {
                            habilidadDTO.getEfectos().add(new EfectoDTO(efecto));
                        });
                    }
                    return habilidadDTO;
                })
                .collect(Collectors.toList());

        return habilidadDTOList;
    }



}
