package com.tallerwebi.servicios;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioNivelImpl implements ServicioNivel {

    Nivel nivelSeleccionado = null;

    public ServicioNivelImpl(){
        Nivel prueba = new Nivel(1,0,3,"Mazmorra: Una oscura y tenebrosa mazmorra llena de bichos mutantes","Monedas",true);
        Nivel prueba2 = new Nivel(2,1,5,"Bosque: Bosque selodao habitado por bandidos","Armas",true);
        Nivel prueba3 = new Nivel(3,5,10,"Este es el tercer nivel","Monedas",true);
        agregarNivel(prueba);
        agregarNivel(prueba2);
        agregarNivel(prueba3);
        System.out.println(buscarNivelPorId(1));
    }

    @Override
    public ArrayList<Nivel> devolverTodosLosNiveles() {
        System.out.println(niveles.toString());
        return new ArrayList<>(niveles);
    }

    @Override
    public Nivel buscarNivelPorId(Integer id){

        if(id > niveles.size()){
            return null;
        }

        for(int i = 0; i < niveles.size();i++){
            if(niveles.get(i).getId() == id){
                return niveles.get(i);
            }
        }

        return  null;
    }


    @Override
    public void seleccionarNivel(Nivel nivel, Integer nivelJugador) {



        if (!niveles.contains(nivel)) {
            System.out.println("Error: El nivel " + nivel.getId() + " no existe.");
            return;
        }

        if (nivel.getNivelMinimoPersonaje() > nivelJugador) {
            if (this.nivelSeleccionado != null) {
                this.nivelSeleccionado.setSeleccionado(false);
            }
            this.nivelSeleccionado = null; // No hay nivel seleccionado
            System.out.println("Nivel deseleccionado: Nivel del jugador (" + nivelJugador + ") es insuficiente para " + nivel.getId() + ".");
            return;
        }


        if (this.nivelSeleccionado != null && !this.nivelSeleccionado.equals(nivel)) {
            this.nivelSeleccionado.setSeleccionado(false);
            System.out.println("Deseleccionado nivel previo: " + this.nivelSeleccionado.getId());
        }


        nivel.setSeleccionado(true);
        this.nivelSeleccionado = nivel;
        System.out.println("Nivel seleccionado: " + nivel.getId());

    }


    @Override
    public void agregarNivel(Nivel nivel) {
        niveles.add(nivel);
    }

    @Override
    public Nivel devolverNivelSeleccionado() {
        return this.nivelSeleccionado;
    }

    @Override
    public Nivel crearNivel(Integer id, Integer niv_min, Integer niv_enem_max, String desc, String recompensas, Boolean estado) {
        Nivel nuevoNivel = new Nivel(id,niv_min,niv_enem_max,desc,recompensas,estado);
        return nuevoNivel;
    }

}
