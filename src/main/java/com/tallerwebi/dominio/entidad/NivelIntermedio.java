package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nivel_intermedio")
@Getter
@Setter
public class NivelIntermedio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "nivel_id", nullable = false)
    private Nivel nivel;

    @ManyToOne
    @JoinColumn(name = "objeto_id")
    private Objeto objeto;

    @ManyToOne
    @JoinColumn(name = "enemigo_id")
    private Enemigo enemigo;

    public NivelIntermedio() {}

    public NivelIntermedio(Nivel nivel, Objeto objeto, Enemigo enemigo) {
        this.nivel = nivel;
        this.objeto = objeto;
        this.enemigo = enemigo;
    }
    public NivelIntermedio(Long ID,Nivel nivel, Objeto objeto, Enemigo enemigo) {
        this.id = ID;
        this.nivel = nivel;
        this.objeto = objeto;
        this.enemigo = enemigo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NivelIntermedio that = (NivelIntermedio) o;
        return Objects.equals(nivel, that.nivel) &&
                Objects.equals(objeto, that.objeto) &&
                Objects.equals(enemigo, that.enemigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nivel, objeto, enemigo);
    }
}
