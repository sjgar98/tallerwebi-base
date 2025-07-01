package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Accessors(chain = true)
@Getter
@Setter
public class EfectoDTO {

    private Long id;

    private String nombre;
    private Integer duracionTotal;
    private Integer duracionActual;
    private Integer danioPorTurno;

    public EfectoDTO() {}

    public EfectoDTO(Long id, String nombre, Integer duracionTotal, Integer danioPorTurno) {
        this.id = id;
        this.nombre = nombre;
        this.duracionTotal = duracionTotal;
        this.duracionActual = duracionTotal;
        this.danioPorTurno = danioPorTurno;

    }

    public EfectoDTO(Efecto efectoBase) {
        this.id = efectoBase.getId();
        this.nombre = efectoBase.getNombre();
        this.duracionTotal = efectoBase.getDuracionTotal();
        this.duracionActual = efectoBase.getDuracionTotal();
        this.danioPorTurno = efectoBase.getDanioPorTurno();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EfectoDTO efectoDTO = (EfectoDTO) o;

        return Objects.equals(id, efectoDTO.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
