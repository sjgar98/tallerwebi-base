package com.tallerwebi.turnos;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersonajeService {

    private Map<String, Personaje> personajes = new HashMap<>();

    // Constructor
    public PersonajeService() {
        personajes.put("Heroe", new Personaje("Heroe", 100, 15));
        personajes.put("Enemigo", new Personaje("Enemigo", 80, 10));
    }

    public Personaje obtener(String nombre) {
        return personajes.get(nombre);
    }

    public Collection<Personaje> obtenerTodos() {
        return personajes.values();
    }
}