package com.tallerwebi.turnos;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class JuegoServiceImpl implements JuegoService {

    Map<String, Personaje> personajes = new HashMap<>();
    String turnoActual = "Heroe";


    public JuegoServiceImpl(){
        personajes.put("Heroe", new Personaje("Heroe", 100, 15));
        personajes.put("Enemigo", new Personaje("Enemigo", 80, 10));
        //
        Objeto poscion = new Objeto("Pocion Curativa", 0, 3, TipoObjeto.CURATIVO);
        Equipamiento amuletoAtaque = new Equipamiento("Amuleto Ataque",1,1,TipoObjeto.DANIO_FISICO,15,true);
        personajes.get("Heroe").agregarEquipamiento(amuletoAtaque);
        personajes.get("Heroe").agregarObjeto(poscion);
        personajes.get("Heroe").aplicarAumentos();
    }
    @Override
    public void atacar() {
        personajes.get("Enemigo").recibirDaño(personajes.get("Heroe").getAtaque());
        System.out.println("Hereo ataco a Enemigo y le hizo: " + personajes.get("Heroe").getAtaque() + " de daño");

    }


    @Override
    public void accionesEnemigo() {
        personajes.get("Heroe").recibirDaño(personajes.get("Enemigo").getAtaque());
        System.out.println("Enemigo ataco a Heroe y le hizo: " + personajes.get("Enemigo").getAtaque() + " de daño");
    }

    @Override
    public Collection<Personaje> obtenerTodos() {
        return personajes.values();
    }

    @Override
    public String getTurnoActual() {
        return personajes.get(turnoActual).getNombre();
    }

    @Override
    public Personaje obtener(String nombre) {
        return personajes.get(nombre);
    }

    @Override
    public List<Objeto> obtenerInventarioPersonaje(String nombre) {
        System.out.println(personajes.get(nombre).getInventario());
        return personajes.get(nombre).getInventario();
    }

    @Override
    public void usarObjetoPersonaje(Objeto objeto) {
        personajes.get("Heroe").usarObjeto(objeto);
    }
}
