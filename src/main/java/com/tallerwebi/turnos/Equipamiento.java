package com.tallerwebi.turnos;

public class Equipamiento extends Objeto{
    private Integer aumento;
    private Boolean esta_Equipado;

    public Equipamiento(String nombre, Integer id, Integer cantidad, TipoObjeto tipo, Integer aumento, Boolean estado) {
        super(nombre, id, cantidad, tipo);
        this.aumento = aumento;
        this.esta_Equipado = estado;

    }

    public Integer getAumento() {
        return aumento;
    }

    public Boolean getEsta_Equipado() {
        return esta_Equipado;
    }

    public void setAumento(Integer aumento) {
        this.aumento = aumento;
    }

    public void setEsta_Equipado(Boolean esta_Equipado) {
        this.esta_Equipado = esta_Equipado;
    }
}
