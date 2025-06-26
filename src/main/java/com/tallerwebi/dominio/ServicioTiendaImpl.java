package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.infraestructura.RepositorioObjetos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioTienda")
@Transactional
public class ServicioTiendaImpl implements ServicioTienda {
    private final RepositorioObjetos repositorioObjetos;

    @Autowired
    public ServicioTiendaImpl(RepositorioObjetos repositorioObjetos) {
        this.repositorioObjetos = repositorioObjetos;
    }

    @Override
    public List<Objeto> obtenerProductosDisponibles() {
        return this.repositorioObjetos.getObjetosComprables();
    }

    @Override
    public Objeto buscarObjetoPorId(Long objetoId) {
        return this.repositorioObjetos.getObjetoById(objetoId);
    }


}
