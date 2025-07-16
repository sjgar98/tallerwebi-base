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
import java.util.Iterator;
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
        this.combate = new Combate();


    }

    @Override
    public void setCombate(HttpServletRequest request) {
        var userId =request.getSession().getAttribute("userId");

        if(userId!= null){
            combate.setJugador(servicioJugador.getJugadorActual((Long) userId));
            combate.setEnemigos(servicioNivel.obtenerEnemigosDto(servicioNivel.obtenerLosEnemigosDeUnNivel(servicioNivel.devolverNivelSeleccionado().getId())));
            combate.setRecompensaOro(servicioNivel.devolverNivelSeleccionado().getOro());
        }


    }

    @Override
    public Boolean estaVivo() {

        if (!combate.estaVivo()) {
            panel = "";
            repositorioEfectos.eliminarTodosLosEfectosAplicados();
        }
        return combate.estaVivo();
    }

    @Override
    public Boolean gano() {
        if (combate.gano()){
            panel = "";
            repositorioEfectos.eliminarTodosLosEfectosAplicados();
        }

        return combate.gano();
    }

    @Override
    public void ataqueJugador(Integer index, Long idHabilidad) {


        if (idHabilidad == null){

            EnemigoDTO enemigo = combate.getEnemigos().get(index);
            enemigo.setVidaActual(enemigo.getVidaActual() - (servicioJugador.getAtaqueTotal(servicioJugador.getJugadorActual(combate.getJugador().getId()))));

            agregarTexto("Jugador: " + combate.getJugador().getNombre() + " realizo ATAQUE sobre: " + combate.getEnemigos().get(index).getNombre());
            if (enemigo.getVidaActual() <= 0){
                enemigo.setVidaActual(0);
            }
        } else {
            agregarTexto("Jugador: " + combate.getJugador().getNombre() + " realizo " + repositorioHabilidades.obtenerHabilidadPorId(idHabilidad).getNombre() + " sobre: " + combate.getEnemigos().get(index).getNombre());
            usarHabilidad(index, idHabilidad);

        }

        descontarVidaEnemigosPorEfecto();
        gano();
        estaVivo();

    }

    @Override
    public void ataqueEnemigo() {

        for (EnemigoDTO enemigo : combate.getEnemigos()) {
            if (enemigo.getVidaActual() <= 0) continue;

            if (probabilidadUsarHabilidad(enemigo)) {
                usarHabilidadEnemigo(enemigo);
                agregarTexto("Enemigo: " + enemigo.getNombre() + " usó una HABILIDAD contra el jugador");
            } else {
                Integer danioBase = enemigo.getAtaque();
                Integer defensaJugador = servicioJugador.getDefensaTotal(servicioJugador.getJugadorActual(combate.getJugador().getId()));
                Integer danioReducido = (int) Math.floor(danioBase - (danioBase * defensaJugador / 100.0));
                if (danioReducido < 0) danioReducido = 0;

                combate.getJugador().setVidaActual(
                        Math.max(0, combate.getJugador().getVidaActual() - danioReducido)
                );

                agregarTexto("Enemigo: " + enemigo.getNombre() + " realizó ATAQUE básico al jugador y le hizo " + danioReducido + " de daño");
            }

            if (probabilidadAplicarEfecto(enemigo) && enemigo.getEfecto() != null) {
                aplicarEfectoAlJugador(enemigo.getEfecto().getId(), enemigo);
            }
        }

        descontarVidaJugadorPorEfecto();

    }

    @Override
    public void defensaJugador() {
        for (EnemigoDTO enemigo : combate.getEnemigos()) {
            if (enemigo.getVidaActual() > 0){
                combate.getJugador().setVidaActual(combate.getJugador().getVidaActual() - (enemigo.getAtaque() / 2));
            }

        }
        for (int i = 0; i < combate.getEnemigos().size(); i++){
            agregarTexto("Enemigo: " + combate.getEnemigos().get(i).getNombre() + " realizo ATAQUE al jugador");
        }

    }

    @Override
    public List<Habilidad> habilidadesJugador() {
        return  repositorioHabilidades.buscarHabilidadesDelJugador(combate.getJugador().getId());
    }

    @Override
    public Jugador getJugador() {
        return combate.getJugador();
    }

    @Override
    public List<EfectoAplicado> obtenerEfectosDelJugador() {
        return repositorioEfectos.obtenerEfectosAplicadosDelJguador(combate.getJugador());
    }

    @Override
    public List<EnemigoDTO> getEnemigos() {
        for (EnemigoDTO enemigo : combate.getEnemigos()) {
            if (enemigo.getEfectosRecibidos() == null) {
                enemigo.setEfectosRecibidos(new ArrayList<>());
            }
        }
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
        this.panel += "-------------------------------------------------------" + "\n"+ texto +"\n";
        System.out.println(panel);
    }

    @Override
    public String devolverTexto() {
        System.out.println(panel);
        return  this.panel;
    }

    @Override
    public void aplicarEfectoAlJugador(Long idEfecto, EnemigoDTO enemigoActual) {
        List<EfectoAplicado> efectosJugador = obtenerEfectosDelJugador();

        Boolean yaTieneEfecto = false;

        if (efectosJugador != null) {
            for (EfectoAplicado efecto : efectosJugador) {
                if (efecto.getEfectoBase().getId().equals(idEfecto)) {
                    yaTieneEfecto = true;
                    break;
                }
            }
        }

        if (!yaTieneEfecto) {
            Jugador jugador = servicioJugador.getJugadorActual(combate.getJugador().getId());
            EfectoAplicado aplicado = new EfectoAplicado()
                    .setEfectoBase(repositorioEfectos.obtenerEfectoPorId(idEfecto))
                    .setDuracionActual(0)
                    .setJugador(jugador);

            repositorioEfectos.crearInstanciaEfectoAplicado(aplicado);
            agregarTexto("Enemigo: " + enemigoActual.getNombre() + " le aplicó el efecto " + enemigoActual.getEfecto().getNombre() + " al jugador");
        }

    }

    @Override
    public void descontarVidaJugadorPorEfecto() {

        Jugador jugador = combate.getJugador();
        List<EfectoAplicado> efectosAplicados = repositorioEfectos.obtenerEfectosAplicadosDelJguador(jugador);

        if (efectosAplicados.isEmpty()) {
            return;
        }

        for (EfectoAplicado efectoAplicado : new ArrayList<>(efectosAplicados)) {
            String nombre = efectoAplicado.getEfectoBase().getNombre();
            Integer danio = efectoAplicado.getEfectoBase().getDanioPorTurno();

            if (nombre.equals("Veneno") || nombre.equals("Sangrado")) {
                jugador.setVidaActual(jugador.getVidaActual() - danio);

                efectoAplicado.setDuracionActual(efectoAplicado.getDuracionActual() + 1);

                agregarTexto("El jugador recibió el efecto de " + nombre + ": Le hizo " + danio
                        + " de daño, quedan " + (efectoAplicado.getEfectoBase().getDuracionTotal()
                        - efectoAplicado.getDuracionActual()) + " turnos");

                if (efectoAplicado.getDuracionActual() >= efectoAplicado.getEfectoBase().getDuracionTotal()) {

                    repositorioEfectos.eliminarEfectoAplicado(efectoAplicado);
                }
            } else if (nombre.equals("Congelado") || nombre.equals("Inmovilizado") || nombre.equals("Cancelado") ){
                efectoAplicado.setDuracionActual(efectoAplicado.getDuracionActual() + 1);
                if (efectoAplicado.getDuracionActual() >= efectoAplicado.getEfectoBase().getDuracionTotal()) {

                    repositorioEfectos.eliminarEfectoAplicado(efectoAplicado);
                }
            }
        }




    }

    @Override
    public Boolean probabilidadAplicarEfecto(EnemigoDTO enemigoDTO) {
        if(enemigoDTO.getEfecto() != null){
            Random random = new Random();
            return random.nextDouble() < enemigoDTO.getProbabilidadAplicarEfecto();
        }

     return false;
    }

    @Override
    public Boolean probabilidadUsarHabilidad(EnemigoDTO enemigoDTO) {
        List<Habilidad> habilidades = repositorioHabilidades.obtenerHabilidadesPorEnemigoId(enemigoDTO.getId());

        if(habilidades != null && !habilidades.isEmpty()){
            Random random = new Random();
            return random.nextDouble() < enemigoDTO.getProbabilidadUsarHabilidad();
        }

        return false;
    }




    @Override
    public void aplicarEfectoAlEnemigo(Integer index, Long idHabilidad) {
        List<Efecto> efectosEncontrados = buscarEfectoPorHabilidad(idHabilidad);

        EnemigoDTO enemigo = combate.getEnemigos().get(index);

        for (Efecto efecto : efectosEncontrados) {
            boolean yaAplicado = enemigo.getEfectosRecibidos().stream()
                    .anyMatch(e -> e.getEfectoBase().getId().equals(efecto.getId()));

            if (!yaAplicado) {
                EfectoAplicadoEnemigo efectoAplicado = new EfectoAplicadoEnemigo()
                        .setEfectoBase(efecto)
                        .setDuracionActual(0);

                enemigo.getEfectosRecibidos().add(efectoAplicado);
            }
        }

    }

    @Override
    public List<Efecto> buscarEfectoPorHabilidad(Long idHabilidad) {
        List<Efecto> efectosEncontrados = new ArrayList<>();

        Habilidad habilidadBuscada = repositorioHabilidades.obtenerHabilidadPorId(idHabilidad);

        if (habilidadBuscada != null) {
            if (habilidadBuscada.getEfectos() != null && !habilidadBuscada.getEfectos().isEmpty()) {
                efectosEncontrados.addAll(habilidadBuscada.getEfectos());
            }
        }
        return efectosEncontrados;
    }

    @Override
    public void descontarVidaEnemigosPorEfecto() {
        Iterator<EnemigoDTO> iteratorEnemigos = combate.getEnemigos().iterator();

        while (iteratorEnemigos.hasNext()) {
            EnemigoDTO enemigo = iteratorEnemigos.next();
            List<EfectoAplicadoEnemigo> efectosEnemigo = enemigo.getEfectosRecibidos();

            if (efectosEnemigo.isEmpty()) {
                continue;
            }

            Iterator<EfectoAplicadoEnemigo> iteratorEfectos = efectosEnemigo.iterator();

            while (iteratorEfectos.hasNext()) {
                EfectoAplicadoEnemigo efecto = iteratorEfectos.next();
                String nombre = efecto.getEfectoBase().getNombre();
                Integer danio = efecto.getEfectoBase().getDanioPorTurno();

                if (nombre.equals("Veneno") || nombre.equals("Sangrado")) {
                    enemigo.setVidaActual(enemigo.getVidaActual() - danio);
                    if (enemigo.getVidaActual() < 0) {
                        enemigo.setVidaActual(0);
                    }

                    efecto.setDuracionActual(efecto.getDuracionActual() + 1);

                    agregarTexto("El enemigo " + enemigo.getNombre() + " recibió el efecto de " + nombre
                            + ": Le hizo " + danio
                            + " de daño, quedan "
                            + (efecto.getEfectoBase().getDuracionTotal() - efecto.getDuracionActual()) + " turnos");

                    if (efecto.getDuracionActual() >= efecto.getEfectoBase().getDuracionTotal()) {
                        iteratorEfectos.remove();
                    }

                    if (enemigo.getVidaActual() <= 0) {
                        enemigo.setVidaActual(0);
                        break;
                    }
                } else if(nombre.equals("Vacio")){

                }
            }
        }
    }

    @Override
    public void usarHabilidad(Integer index, Long idHabilidad) {
        EnemigoDTO enemigo = combate.getEnemigos().get(index);
        Habilidad habilidad = repositorioHabilidades.obtenerHabilidadPorId(idHabilidad);

        if (combate.getJugador().getMana() >= habilidad.getConsumoMana()){
            enemigo.setVidaActual(enemigo.getVidaActual() - habilidad.getDanio());


            if (buscarEfectoPorHabilidad(idHabilidad) != null){
                aplicarEfectoAlEnemigo(index,idHabilidad);
            }

            if (enemigo.getVidaActual() <= 0){
                enemigo.setVidaActual(0);
            }
            combate.getJugador().setMana(combate.getJugador().getMana() - habilidad.getConsumoMana());

        }


    }

    @Override
    public void usarHabilidadEnemigo(EnemigoDTO enemigoDTO) {
        List<Habilidad> habilidades = repositorioHabilidades.obtenerHabilidadesPorEnemigoId(enemigoDTO.getId());

        if (habilidades != null && !habilidades.isEmpty()) {
            if (enemigoDTO.getCantidadDeVecesParaUsarHabilidad() > 0) {
                Jugador jugador = combate.getJugador();
                Habilidad habilidad = habilidades.get(0);

                Integer defensaJugador = servicioJugador.getDefensaTotal(servicioJugador.getJugadorActual(jugador.getId()));

                Integer danioBase = habilidad.getDanio();
                Integer danioReducido = (int) Math.floor(danioBase - (danioBase * defensaJugador / 100.0));
                if (danioReducido < 0) danioReducido = 0;

                jugador.setVidaActual(Math.max(0, jugador.getVidaActual() - danioReducido));

                agregarTexto("Enemigo: " + enemigoDTO.getNombre() + " realizó " + habilidad.getNombre() +
                        " sobre el jugador y le hizo " + danioReducido + " de daño");

                enemigoDTO.setCantidadDeVecesParaUsarHabilidad(enemigoDTO.getCantidadDeVecesParaUsarHabilidad() - 1);
            }
        }
    }

    @Override
    public void agregarRecompensasAlJugador(Jugador jugador, List<Objeto> objetos) {
        for (int i = 0; i < objetos.size(); i++){
            servicioJugador.agregarObjeto(jugador, objetos.get(i));
        }
    }



}
