package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.*;
import com.tallerwebi.infraestructura.RepositorioNivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioNivelImpl implements ServicioNivel {



    @Autowired
    RepositorioNivel repositorioNivel;

    Nivel nivelSeleccionado = null;

    @Autowired
    public ServicioNivelImpl(RepositorioNivel repo){
        this.repositorioNivel = repo;

    }

    @Override
    public List<Objeto> obtenerObjetosDeUnNivel(Long id) {

        return repositorioNivel.devolverTodosLosObjetosDeUnNivel(id);

    }

    @Override
    public List<ObjetoInventario> obtenerObjetosInventario(Long id) {
        List<ObjetoInventario> objetosNivel = repositorioNivel.devolverTodosLosObjetosDeUnNivel(id).stream()
                .map(o -> new ObjetoInventario().setObjeto(o).setJugador(null))
                .collect(Collectors.toList());

        return objetosNivel;
    }

    @Override
    public List<Nivel> obtenerTodosLosNiveles() {
        return repositorioNivel.devolverTodosLosNiveles();
    }

    @Override
    public List<Enemigo> obtenerLosEnemigosDeUnNivel(Long id) {
        return repositorioNivel.devolverTodosLosEnemigosDeUnNivel(id);
    }

    @Override
    public List<NivelDTO> obtenerNivelesDTO(List<Nivel> niveles, Long opcionId) {

        List<NivelDTO> dtoList = niveles.stream()
                .map(n -> new NivelDTO(
                        n.getId(),
                        n.getNombre(),
                        n.getNivelMinimoPersonaje(),
                        n.getNivelMaximoEnemigo(),
                        n.getDescripcion(),
                        opcionId != null && n.getId().equals(opcionId),
                        n.getOro()
                ))
                .collect(Collectors.toList());

        return  dtoList;
    }

    @Override
    public List<EnemigoDTO> obtenerEnemigosDto(List<Enemigo> enemigos) {
        return enemigos.stream().map(enemigo -> {
            EnemigoDTO dto = new EnemigoDTO(
                    enemigo.getId(),
                    enemigo.getNombre(),
                    enemigo.getNivel(),
                    enemigo.getVidaActual(),
                    enemigo.getVidaMaxima(),
                    enemigo.getAtaque(),
                    enemigo.getDefensa(),
                    enemigo.getImagenSrc(),
                    enemigo.getProbabilidadAplicarEfecto(),
                    enemigo.getProbabilidadUsarHabilidad(),
                    enemigo.getCantidadDeVecesParaUsarHabilidad()
            );

            dto.setHabilidades((enemigo.getHabilidades()));

            if (enemigo.getEfecto() != null) {
                dto.setEfecto(new EfectoDTO(enemigo.getEfecto()));
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Nivel devolverNivelSeleccionado() {
        return this.nivelSeleccionado;
    }


    @Override
    public void seleccionarNivel(Long id) {



        if (!repositorioNivel.devolverTodosLosNiveles().contains(repositorioNivel.devolverNivelPorId(id))) {
            System.out.println("Error: El nivel no existe.");
            return;
        }


        if (this.nivelSeleccionado != null && !this.nivelSeleccionado.equals(repositorioNivel.devolverNivelPorId(id))) {
            this.nivelSeleccionado.setSeleccionado(false);
            System.out.println("Deseleccionado nivel previo: " + this.nivelSeleccionado.getId());
        }


        repositorioNivel.devolverNivelPorId(id).setSeleccionado(true);
        this.nivelSeleccionado = repositorioNivel.devolverNivelPorId(id);
        System.out.println("Nivel seleccionado: " + repositorioNivel.devolverNivelPorId(id).getId());

    }

    @Override
    public NivelDTO obtenerNivelPorId(Long id) {

        Nivel nivel = repositorioNivel.devolverNivelPorId(id);

        NivelDTO dto = new NivelDTO(
                nivel.getId(),
                nivel.getNombre(),
                nivel.getNivelMinimoPersonaje(),
                nivel.getNivelMaximoEnemigo(),
                nivel.getDescripcion(),
                id != null && nivel.getId().equals(id),
                nivel.getOro()
        );

        return dto;

    }

}
