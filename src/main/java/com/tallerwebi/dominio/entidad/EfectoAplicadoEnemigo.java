package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class EfectoAplicadoEnemigo {

    private Integer duracionActual;

    private Efecto efectoBase;


}
