package com.tallerwebi.dominio.excepcion;


public class Producto {
    private String nombre;
    private double precio;
    private String rutaImagen;

    public Producto(String nombre, double precio, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }
}
