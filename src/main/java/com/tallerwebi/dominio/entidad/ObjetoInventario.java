package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Accessors(chain = true)
@Getter
@Setter
public class ObjetoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Objeto objeto;
    @ManyToOne
    private Jugador jugador;
    private Integer cantidad = 1;
    private Boolean equipado = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjetoInventario that = (ObjetoInventario) o;
        return Objects.equals(objeto, that.objeto) &&
                Objects.equals(jugador, that.jugador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objeto, jugador);
    }


}
