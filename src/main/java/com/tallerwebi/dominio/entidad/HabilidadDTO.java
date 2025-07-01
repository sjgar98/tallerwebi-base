package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;


@Accessors(chain = true)
@Getter
@Setter
public class HabilidadDTO {

    private Long id;
    private String nombre;
    private TipoHabilidad tipo;
    private Integer consumoMana;
    private Integer danio;
    private Integer nivel;

    private List<EfectoDTO> efectos = new ArrayList<>();

    public HabilidadDTO(){}

    public HabilidadDTO(Long id, String nombre, TipoHabilidad tipo, Integer consumoMana, Integer danio,Integer nivel){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.consumoMana = consumoMana;
        this.danio = danio;
        this.nivel = nivel;
    }

}
