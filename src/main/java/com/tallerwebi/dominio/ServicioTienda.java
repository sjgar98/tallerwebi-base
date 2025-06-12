package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTienda {

    public List<Producto> obtenerProductosDisponibles() {
        return List.of(
                new Producto("Espada Mágica", 150, "css/imagenes/producto.png"),
                new Producto("Escudo de Hierro", 120, "css/imagenes/escudo.jpg"),
                new Producto("Armadura de Diamante", 300, "css/imagenes/armadura.png"),
                new Producto("Poción de Vida", 50, "css/imagenes/curacion.png"),
                new Producto("Arco", 200, "css/imagenes/arco.png")
        );
    }
}

