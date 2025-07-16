package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.entidad.Efecto;
import com.tallerwebi.dominio.entidad.EfectoAplicado;
import com.tallerwebi.dominio.entidad.Jugador;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RepositorioEfectos {

    List<Efecto> obtenerTodosLosEfectos();

    Efecto obtenerEfectoPorId(Long id);

    List<Efecto> obtenerEfectosDelJugador(Jugador jugador);


    void crearInstanciaEfectoAplicado(EfectoAplicado efectoAplicado);

    EfectoAplicado obtenerEfectoAplicadoPorId(Long id);

    List<EfectoAplicado> efectosAplicadosAsociadosAlJugador(Jugador jugador);

    void eliminarEfectoAplicado(EfectoAplicado efectoAplicado);

    List<EfectoAplicado> obtenerEfectosAplicadosDelJguador(Jugador jugador);

    EfectoAplicado obtenerEfectoAplicadoPorJugadorYEfectoBase(Jugador jugador, Efecto efectoBase);

    void eliminarTodosLosEfectosAplicadosDeJugador(Jugador jugador);

    void eliminarTodosLosEfectosAplicados();

}
