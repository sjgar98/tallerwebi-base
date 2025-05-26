package com.tallerwebi.turnos;

public class Objeto {
    private String nombre;
    private Integer id;
    private Integer cantidad;
    private TipoObjeto tipo;

    public Objeto(String nombre, Integer id, Integer cantidad, TipoObjeto tipo) {

        this.nombre = nombre;
        this.id = id;
        this.cantidad = cantidad;
        this.tipo = tipo;

    }

    public void usarObjeto() {
        this.cantidad -= 1;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoObjeto getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // misma instancia
        if (o == null || getClass() != o.getClass()) return false;

        Objeto objeto = (Objeto) o;
        return id == objeto.id; // Podés cambiarlo para que compare más atributos si querés
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id); // Lo mismo: usa los mismos campos que en equals()
    }
}
