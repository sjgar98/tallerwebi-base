package com.tallerwebi.dominio.entidad;


import lombok.Getter;

@Getter
public class Producto {
    // Getters
    private Long id;
    private String nombre;
    private Long precio;
    private String rutaImagen;

    public Producto(Long id, String nombre, Long precio, String rutaImagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

}
