package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.dominio.excepcion.DineroInsuficienteException;
import com.tallerwebi.dominio.excepcion.ObjetoNoEncontrado;
import com.tallerwebi.dominio.excepcion.ObjetoNoEquipable;
import com.tallerwebi.infraestructura.RepositorioHabilidades;
import com.tallerwebi.infraestructura.RepositorioJugador;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioJugador repositorioJugador;
    private final RepositorioObjetos repositorioObjetos;
    private final RepositorioHabilidades repositorioHabilidades;
    private final SessionFactory sessionFactory;

    @Autowired
    public ServicioJugadorImpl(RepositorioUsuario repositorioUsuario, RepositorioJugador repositorioJugador, RepositorioObjetos repositorioObjetos, RepositorioHabilidades repositorioHabilidades, SessionFactory sessionFactory) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioJugador = repositorioJugador;
        this.repositorioObjetos = repositorioObjetos;
        this.repositorioHabilidades = repositorioHabilidades;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crearNuevoJugador(Long userId, String nombre) {
        Usuario usuario = this.repositorioUsuario.buscarPorId(userId);
        Jugador nuevoJugador = new Jugador().setUsuario(usuario).setNombre(nombre).setDinero(500L);
        this.repositorioJugador.guardar(nuevoJugador);
        List<ObjetoInventario> objetosIniciales = this.repositorioObjetos.getObjetosIniciales().stream()
                .map(o -> new ObjetoInventario().setObjeto(o).setJugador(nuevoJugador))
                .collect(Collectors.toList());
        nuevoJugador.setObjetos(objetosIniciales);
        //habilidades iniciales
        List<Habilidad> habilidadesIniciales = repositorioHabilidades.obtenerHabilidadesNivel1();

        for (Habilidad h : habilidadesIniciales) {
            h.setJugador(nuevoJugador);
        }
        nuevoJugador.setHabilidades(habilidadesIniciales);
        this.repositorioJugador.modificar(nuevoJugador);
    }

    @Override
    public Jugador getJugadorActual(Long userId) {
        return this.repositorioJugador.buscar(userId);
    }

    @Override
    public List<ObjetoInventario> getObjetosJugador(Jugador jugador) {
        return this.repositorioJugador.buscarObjetosInventario(jugador.getId());
    }

    @Override
    public ObjetoInventario getObjetoInventarioPorId(Long objetoInventarioId) {
        return this.repositorioJugador.buscarObjetoInventarioPorId(objetoInventarioId);
    }

    @Override
    public Integer getAtaqueAdicional(Jugador jugador) {
        return this.getObjetosJugador(jugador)
                .stream()
                .filter(ObjetoInventario::getEquipado)
                .map(o -> o.getObjeto().getAtaque() != null ? o.getObjeto().getAtaque() : 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Integer getAtaqueTotal(Jugador jugador) {
        return jugador.getAtaque() + this.getAtaqueAdicional(jugador);
    }

    @Override
    public Integer getDefensaAdicional(Jugador jugador) {
        return this.getObjetosJugador(jugador)
                .stream()
                .filter(ObjetoInventario::getEquipado)
                .map(o -> o.getObjeto().getDefensa() != null ? o.getObjeto().getDefensa() : 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Integer getDefensaTotal(Jugador jugador) {
        return jugador.getDefensa() + this.getDefensaAdicional(jugador);
    }

    @Override
    public void equiparObjeto(Jugador jugador, Long objetoInventarioId) {
        ObjetoInventario objeto = this.repositorioJugador.buscarObjetoInventarioPorId(objetoInventarioId);
        if (objeto == null || !objeto.getJugador().getId().equals(jugador.getId())) {
            throw new ObjetoNoEncontrado("Objeto no encontrado o no pertenece al jugador");
        }
        if (!objeto.getObjeto().getEquipable()) {
            throw new ObjetoNoEquipable("El objeto no es equipable");
        }
        objeto.setEquipado(!objeto.getEquipado());
        this.repositorioJugador.modificarObjeto(objeto);
    }

    @Override
    public void agregarObjeto(Jugador jugador, Objeto objeto) {
        var objetosInventario = repositorioJugador.buscarObjetosInventarioPorObjeto(jugador, objeto);
        var primerSlotConEspacio = objetosInventario.stream()
                .filter(o -> o.getCantidad() + 1 <= objeto.getMaxPorSlot())
                .findFirst().orElse(null);
        if (primerSlotConEspacio != null) {
            primerSlotConEspacio.setCantidad(primerSlotConEspacio.getCantidad() + 1);
            repositorioJugador.modificarObjeto(primerSlotConEspacio);
        } else {
            ObjetoInventario slotNuevo = new ObjetoInventario().setJugador(jugador).setObjeto(objeto);
            repositorioJugador.agregarObjeto(slotNuevo);
        }
    }

    @Override
    public void agregarObjeto(Jugador jugador, Objeto objeto, Integer cantidad) {
        Integer cantidadRestante = cantidad;
        while (cantidadRestante > 0) {
            this.agregarObjeto(jugador, objeto);
            cantidadRestante--;
        }
    }

    @Override
    public void removerObjeto(Jugador jugador, ObjetoInventario objeto) {
        objeto.setCantidad(objeto.getCantidad() - 1);
        if (objeto.getCantidad() == 0) {
            repositorioJugador.removerObjeto(objeto);
        } else {
            repositorioJugador.modificarObjeto(objeto);
        }
    }

    @Override
    public void agregarDinero(Jugador jugador, Long dinero) {
        jugador.setDinero(jugador.getDinero() + dinero);
        repositorioJugador.modificar(jugador);
    }

    @Override
    public void removerDinero(Jugador jugador, Long dinero) {
        if (jugador.getDinero() - dinero < 0) {
            throw new DineroInsuficienteException("Dinero insuficiente para esta operacion");
        }
        jugador.setDinero(jugador.getDinero() - dinero);
        repositorioJugador.modificar(jugador);
    }

    @Override
    public void agregarObjetosAlJugador(List<Objeto> objetos, Long userId) {

        Jugador jugadorActual = this.repositorioJugador.buscar(userId);

        List<ObjetoInventario> objetoRecompensa = objetos.stream()
                .map(o -> new ObjetoInventario().setObjeto(o).setJugador(jugadorActual))
                .collect(Collectors.toList());


        for(int i = 0; i < objetos.size(); i++){

            ObjetoInventario nuevoObjeto = objetoRecompensa.get(i);
            if (jugadorActual.getObjetos().contains(nuevoObjeto)){
                Integer index = jugadorActual.getObjetos().indexOf(nuevoObjeto);

                jugadorActual.getObjetos().get(index).setCantidad(jugadorActual.getObjetos().get(index).getCantidad() + 1);
            } else{
                jugadorActual.getObjetos().add(nuevoObjeto);
            }

        }
        this.repositorioJugador.modificar(jugadorActual);

    }

    @Override
    public void agregarOroAlJugador(Long userId, Long oro) {
        Jugador jugadorActual = this.getJugadorActual(userId);
        jugadorActual.setDinero(jugadorActual.getDinero() + oro);
    }

    @Override
    public void sacarObjetosAlJugador(Objeto objetoAUsar, Long userId) {
        Jugador jugadorActual = this.repositorioJugador.buscar(userId);


        List<ObjetoInventario> objetosJugador = jugadorActual.getObjetos();

        for(int i = 0; i < objetosJugador.size();i++){
            if(objetosJugador.get(i).getObjeto().equals(objetoAUsar)){


                if (objetosJugador.get(i).getCantidad() - 1 == 0){
                    objetosJugador.remove(i);
                } else{
                    objetosJugador.get(i).setCantidad(objetosJugador.get(i).getCantidad() - 1);
                }
            }
        }

        jugadorActual.setObjetos(objetosJugador);



        this.repositorioJugador.modificar(jugadorActual);

    }

    @Override
    public List<ObjetoInventario> getObjetosConsumibles(Long userId) {
        Jugador jugadorActual = this.repositorioJugador.buscar(userId);
        List<ObjetoInventario> objetos = jugadorActual.getObjetos();
        List<ObjetoInventario> consumibles = new java.util.ArrayList<>();
        for (int i = 0; i < objetos.size(); i++){
            if (objetos.get(i).getObjeto().getTipo().getNombre().equals("Consumible")){
                consumibles.add(objetos.get(i));
            }
        }

        return consumibles;
    }

    @Override
    public void subirDeNivel(Integer experiencia, Long userId) {
        Jugador jugadorActual = this.repositorioJugador.buscar(userId);

        recibirExperiencia(jugadorActual, experiencia);

        this.repositorioJugador.modificar(jugadorActual);
    }

    private void recibirExperiencia(Jugador jugador, Integer experiencia) {
        jugador.setExpActual(jugador.getExpActual() + experiencia);

        while (jugador.getExpActual() >= jugador.getExpSigNiv()) {
            jugador.setExpActual(jugador.getExpActual() - jugador.getExpSigNiv());
            jugador.setNivel(jugador.getNivel() + 1);
            jugador.setAtaque(jugador.getAtaque() + 1);
            jugador.setDefensa(jugador.getDefensa() + 1);
            jugador.setExpSigNiv((int)(100 * Math.pow(jugador.getNivel(), 1.5)));
        }
    }
}
