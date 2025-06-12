package com.tallerwebi.dominio.entidad;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

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
}
