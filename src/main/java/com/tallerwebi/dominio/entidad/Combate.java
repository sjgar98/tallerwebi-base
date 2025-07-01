package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Accessors(chain = true)
@Getter
@Setter
public class Combate {
    private JugadorDTO jugador;
    private List<EnemigoDTO> enemigos;
    private Long recompensaOro;



    public Combate(){}



    public Boolean estaVivo(){
        if (jugador.getVidaActual() > 0 ){
            return true;
        } else {
            return false;
        }
    }

    public Boolean gano(){
        for (EnemigoDTO enemigo : enemigos) {
            if(enemigo.getVidaActual() > 0){
                return false;
            }
        }
        return true;
    }

    public void ataqueJugador(Integer index){

        EnemigoDTO enemigo = enemigos.get(index);
        enemigo.setVidaActual(enemigo.getVidaActual() - (this.jugador.getAtaque() * 10));
        if (enemigo.getVidaActual() < 0){
            enemigo.setVidaActual(0);
        }



        System.out.println("Se ataco a " + enemigos.get(index).toString());

    }

    public void defensaJugador(){
        for (EnemigoDTO enemigo : enemigos) {
            if (enemigo.getVidaActual() > 0){
                this.jugador.setVidaActual(jugador.getVidaActual() - (enemigo.getAtaque() / 2));
            }

        }
    }

    public void usarHabilidad(Integer index, Long idHabilidad){

        EnemigoDTO enemigo = enemigos.get(index);
        HabilidadDTO habilidad = buscarHabilidadPorId(idHabilidad);

        enemigo.setVidaActual(enemigo.getVidaActual() - habilidad.getDanio());
        if (enemigo.getVidaActual() < 0){
            enemigo.setVidaActual(0);
        }

    }

    public void ataqueEnemigo(){

        for (EnemigoDTO enemigo : enemigos) {
            if (enemigo.getVidaActual() > 0){
                this.jugador.setVidaActual(jugador.getVidaActual() - enemigo.getAtaque());
            }

        }

    }

    public String descontarVidaEnemigosPorEfecto(){
        StringBuilder textoAcumulado = new StringBuilder();
        boolean algunEnemigoRecibioEfecto = false;

        for (EnemigoDTO enemigo : enemigos) {
            if (enemigo.getVidaActual() > 0 && enemigo.getEfectosRecibidos() != null && !enemigo.getEfectosRecibidos().isEmpty()) {
                algunEnemigoRecibioEfecto = true;
                for (EfectoDTO efecto : enemigo.getEfectosRecibidos()) {
                    enemigo.setVidaActual(enemigo.getVidaActual() - efecto.getDanioPorTurno());
                    textoAcumulado.append("El enemigo ").append(enemigo.getNombre())
                            .append(" recibe el efecto ").append(efecto.getNombre())
                            .append(" y le hizo ").append(efecto.getDanioPorTurno())
                            .append(" de daño.\n");
                }
                if (enemigo.getVidaActual() < 0) {
                    enemigo.setVidaActual(0);
                }
            }
        }

        if (algunEnemigoRecibioEfecto) {
            return textoAcumulado.toString();
        } else {
            return "norecibioefecto";
        }
    }

    public HabilidadDTO buscarHabilidadPorId(Long id){

        return jugador.getHabilidades().stream()
                .filter(habilidad -> habilidad.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<EfectoDTO> buscarEfectoPorHabilidad(Long idHabilidad){
        List<EfectoDTO> efectosEncontrados = new ArrayList<>();

        HabilidadDTO habilidadBuscada = buscarHabilidadPorId(idHabilidad);

        if (habilidadBuscada != null) {
            if (habilidadBuscada.getEfectos() != null && !habilidadBuscada.getEfectos().isEmpty()) {
                efectosEncontrados.addAll(habilidadBuscada.getEfectos());
            }
        }
        return efectosEncontrados;
    }
    public void aplicarEfectoAlEnemigo(Integer index,Long idHabilidad){
        EnemigoDTO enemigo = enemigos.get(index);

        if (!buscarEfectoPorHabilidad(idHabilidad).isEmpty()){
            List<EfectoDTO> efectosDeHabilidad = buscarEfectoPorHabilidad(idHabilidad);
            enemigo.setEfectosRecibidos(efectosDeHabilidad);

        }


    }


}
