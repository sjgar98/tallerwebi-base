package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

@Accessors(chain = true)
@Getter
@Setter
public class Combate {
    private Jugador jugador;
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

    public void ataqueEnemigo(){

        for (EnemigoDTO enemigo : enemigos) {
            if (enemigo.getVidaActual() > 0){
                this.jugador.setVidaActual(jugador.getVidaActual() - enemigo.getAtaque());
            }

        }

    }

    private EnemigoDTO obtenerEnemigo(Long id){
        for (EnemigoDTO enemigo : enemigos) {
            if (Objects.equals(enemigo.getId(), id)) {
                return enemigo;
            }
        }

        return null;
    }


}
