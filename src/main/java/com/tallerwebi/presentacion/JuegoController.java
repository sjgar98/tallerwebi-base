package com.tallerwebi.presentacion;


import com.tallerwebi.turnos.JuegoService;
import com.tallerwebi.turnos.JuegoServiceImpl;
import com.tallerwebi.turnos.Objeto;
import com.tallerwebi.turnos.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/juego")
public class JuegoController {

    private final JuegoService juegoService;

    @Autowired
    public JuegoController(JuegoService juegoService) {

        this.juegoService = new JuegoServiceImpl();
    }

    @GetMapping("/combate")
    public ModelAndView getCombate(HttpServletRequest request) {

        return new ModelAndView("combate");
    }

    @GetMapping("/todos")
    public Collection<Personaje> getTodos() {
        return juegoService.obtenerTodos();
    }

    @GetMapping("/jugador")
    public Personaje getJugador() {
        return juegoService.obtener("Heroe");
    }

    @GetMapping("/enemigo")
    public Personaje getEnemigo() {
        return juegoService.obtener("Enemigo");
    }

    @PostMapping("/actualizarPersonaje")
    public Personaje getJugadorAct() {
        return juegoService.obtener("Heroe");
    }

    @PostMapping("/actualizarEnemigo")
    public Personaje getEnemigoAct() {
        return juegoService.obtener("Enemigo");
    }

    @GetMapping("/cargarObjetos")
    public List<Objeto> cargarObjetos() {
        return juegoService.obtenerInventarioPersonaje("Heroe");
    }


    @PostMapping("/atacar")
    public void atacar() throws InterruptedException {
        juegoService.atacar();
    }

    @PostMapping("/ataqueEnemigo")
    public void atacarEnemigo() throws InterruptedException {
        juegoService.accionesEnemigo();
    }

    @PostMapping("/usarObjeto")
    public void usarObjeto(@RequestBody Objeto objeto) {
        System.out.println("Objeto recibido: " + objeto.getNombre());
        juegoService.usarObjetoPersonaje(objeto);
    }


}

