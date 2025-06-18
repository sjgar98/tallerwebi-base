package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Combate;
import com.tallerwebi.dominio.entidad.EnemigoDTO;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioCombateImpl implements ServicioCombate {



    private final ServicioNivel servicioNivel;
    private final ServicioJugador servicioJugador;
    private final RepositorioObjetos repositorioObjetos;

    Combate combate;


    @Autowired
    public ServicioCombateImpl(ServicioNivel servicioNivel, ServicioJugador servicioJugador, RepositorioObjetos repositorioObjetos){
        this.servicioNivel = servicioNivel;
        this.servicioJugador = servicioJugador;
        this.repositorioObjetos = repositorioObjetos;
        combate = new Combate();


    }

    @Override
    public void setCombate(HttpServletRequest request) {
        var userId =request.getSession().getAttribute("userId");

        if(userId!= null){
            combate.setJugador(this.servicioJugador.getJugadorActual((Long) userId));
            combate.setEnemigos(servicioNivel.obtenerEnemigosDto(servicioNivel.obtenerLosEnemigosDeUnNivel(servicioNivel.devolverNivelSeleccionado().getId())));
            combate.setRecompensaOro(100L);
        }

    }

    @Override
    public Boolean estaVivo() {
       return combate.estaVivo();
    }

    @Override
    public Boolean gano() {
        return combate.gano();
    }

    @Override
    public void ataqueJugador(Integer index) {
        combate.ataqueJugador(index);
    }

    @Override
    public void ataqueEnemigo() {
        combate.ataqueEnemigo();
    }

    @Override
    public void defensaJugador() {
        combate.defensaJugador();
    }

    @Override
    public Jugador getJugador() {
       return combate.getJugador();
    }

    @Override
    public List<EnemigoDTO> getEnemigos() {
        return combate.getEnemigos();
    }

    @Override
    public Long getRecompensaOro() {
      return combate.getRecompensaOro();
    }

    @Override
    public Integer calcularExperiencia() {

        Integer experiencia = 0;
        for (int i = 0; i < combate.getEnemigos().size(); i++){
            experiencia+= (combate.getEnemigos().get(i).getNivel() * 15);
        }
        return experiencia;
    }

    @Override
    public void usarObjeto(Long id) {

        Jugador jugador  = combate.getJugador();
        Objeto objetoAUsar = repositorioObjetos.getObjetoById(id);


        jugador.setVidaActual((int) (jugador.getVidaActual() + (jugador.getVidaActual() * objetoAUsar.getRecuperacionVida())));

        if (jugador.getVidaActual() > jugador.getVidaMaxima()){
            jugador.setVidaActual(jugador.getVidaMaxima());
        }

        servicioJugador.sacarObjetosAlJugador(objetoAUsar,jugador.getId());

    }

}
