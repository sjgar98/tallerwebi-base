package com.tallerwebi.turnos;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface JuegoService {





    void atacar();

    void accionesEnemigo();

     Collection<Personaje> obtenerTodos();
     String getTurnoActual();

     Personaje obtener(String nombre);

     List<Objeto> obtenerInventarioPersonaje(String nombre);

     void usarObjetoPersonaje(Objeto objeto);
}
