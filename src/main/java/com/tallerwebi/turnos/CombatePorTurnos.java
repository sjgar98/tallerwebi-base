package com.tallerwebi.turnos;

public class CombatePorTurnos {

    private Personaje jugador;
    private Personaje enemigo;
    private boolean turnoJugador;

    public CombatePorTurnos(Personaje jugador, Personaje enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.turnoJugador = true;
    }

    public void iniciarCombate() {
        System.out.println("¡Combate iniciado!");

        while (jugador.estaVivo() && enemigo.estaVivo()) {
            if (turnoJugador) {
                jugador.atacar(enemigo);
            } else {
                enemigo.atacar(jugador);
            }


            turnoJugador = !turnoJugador;


            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El combate ha terminado.");
        if (jugador.estaVivo()) {
            System.out.println("¡Ganó el jugador!");
        } else {
            System.out.println("¡Ganó el enemigo!");
        }
    }
}
