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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String rol;
    private Boolean activo = false;
    private String nombreUsuario;
    private String nombre;
    private String apellido;

    public Usuario(){

    }

    public Usuario(String email, String contra, String rol){

        this.email = email;
        this.password = contra;
        this.rol = rol;

    }


    public boolean activo() {
        return activo;
    }

    public void activar() {
        activo = true;
    }

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ajustes ajustes;


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
