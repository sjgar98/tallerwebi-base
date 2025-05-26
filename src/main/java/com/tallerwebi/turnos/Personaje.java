package com.tallerwebi.turnos;

import java.util.ArrayList;
import java.util.List;

public class Personaje {

    //variables
    private String nombre;
    private Integer vida;
    private Integer vidaMaxima;
    private Integer ataque;
    private List<Objeto> inventario = new ArrayList<>();
    private List<Equipamiento> Equipamiento = new ArrayList<>();

    //constructor
    public Personaje(String nombre, Integer vida, Integer ataque) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
    }

    //Getters y Setters
    public Integer getVida() {
        return vida;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getAtaque() {
        return ataque;
    }

    //funciones
    public void atacar(Personaje objetivo) {
        System.out.println(nombre + " ataca a " + objetivo.getNombre());
        objetivo.recibirDaño(ataque);
    }

    public void recibirDaño(Integer daño) {
        vida -= daño;
        System.out.println(nombre + " recibe " + daño + " de daño. Vida restante: " + vida);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    //Seccion Objetos/inventario

    public void agregarObjeto(Objeto objeto) {
        inventario.add(objeto);
    }
    public void agregarEquipamiento(Equipamiento objeto) {
        Equipamiento.add(objeto);
    }


    public void usarObjeto(Objeto objeto) {

        for (int e = 0; e < inventario.size(); e++) {
            if (inventario.get(e).equals(objeto)) {

                if (objeto.getTipo().equals(TipoObjeto.CURATIVO) && objeto.getId() == 0) {
                    if (this.vida + 20 >= this.vidaMaxima) {

                        this.vida = vidaMaxima;
                        inventario.get(e).usarObjeto();
                        System.out.println("Se uso el objeto y la vida es de " + this.vida);

                    } else if (this.vida + 20 < this.vidaMaxima) {
                        this.vida += 20;
                        inventario.get(e).usarObjeto();
                        System.out.println("Se uso el objeto y la vida es de " + this.vida);
                    }
                }


            }
        }
    }

    public void aplicarAumentos(){
        for(int i = 0; i < Equipamiento.size(); i++){
            if(Equipamiento.get(i).getEsta_Equipado() == true && Equipamiento.get(i) != null){
                if (Equipamiento.get(i).getTipo() == TipoObjeto.DANIO_FISICO){
                    this.ataque += Equipamiento.get(i).getAumento();
                }
            }
        }
    }

    public Integer saberCantidadObjeto(Objeto objeto) {
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i) == objeto) {
                return inventario.get(i).getCantidad();
            }
        }
        return null;
    }


    public List<Objeto> getInventario() {
        return inventario;
    }
}
