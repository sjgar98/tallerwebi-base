package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Accessors(chain = true)
@Getter
@Setter
public class Objeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion = "";
    @ManyToOne
    private TipoObjeto tipo;
    private Integer rango;
    private Long valor;
    private String imagenSrc;

    // Equipables
    private Boolean equipable;
    private Integer vidaMaxima;
    private Integer ataque;
    private Integer defensa;

    // Consumibles
    private Boolean consumible;
    private Double recuperacionVida;

    public String getTooltip(Boolean withSellPrice) {
        return
            "<div class=\"slot-tooltip\">" +
                "<h5 class=\"slot-tooltip__nombre\">" + this.getNombre() + "</h5>" +
                "<div class=\"slot-tooltip__tipo\">" + this.getTipo().getNombre() + "</div>" +
                (
                    !this.getDescripcion().isEmpty()
                    ? "<div class=\"slot-tooltip__descripcion\">" + this.getDescripcion() + "</div>"
                    : ""
                ) +
                "<div class=\"slot-tooltip__valor\">" + (withSellPrice ? (Long) (this.getValor() / 2) : this.getValor()) + "g</div>" +
            "</div>";
    }

    @OneToMany(mappedBy = "objeto")
    private List<NivelIntermedio> nivelIntermedios = new ArrayList<>();

    @Override
    public String toString() {
        return "Objeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + (tipo != null ? tipo.getNombre() : "null") +
                ", rango=" + rango +
                ", valor=" + valor +
                ", imagenSrc='" + imagenSrc + '\'' +
                ", equipable=" + equipable +
                ", vidaMaxima=" + vidaMaxima +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objeto that = (Objeto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
