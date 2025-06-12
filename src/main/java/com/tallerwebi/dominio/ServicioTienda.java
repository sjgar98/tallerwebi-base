package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTienda {

    public List<Producto> obtenerProductosDisponibles() {
        return List.of(
                new Producto(1L, "Pocion de curacion menor", 120L, "img/items/potion-1.jpg"),
                new Producto(2L, "Pocion de curacion", 150L, "img/items/potion-2.jpg"),
                new Producto(3L, "Pocion de curacion mayor", 300L, "img/items/potion-3.jpg"),
                new Producto(4L, "Daga", 1000L, "img/items/potion-1.jpg")
                /*new Producto(5L, "Arco", 200L, "img/items/arco.jpg")*/
        );
    }



    public Producto buscarProductoPorNombre(String nombre) {
        return obtenerProductosDisponibles().stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }



}

