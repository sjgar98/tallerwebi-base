package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.Anuncio;
import com.tallerwebi.dominio.entidad.Jugador;
import com.tallerwebi.dominio.entidad.Objeto;
import com.tallerwebi.dominio.entidad.ObjetoInventario;
import com.tallerwebi.infraestructura.RepositorioHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioHome")
@Transactional
public class ServicioHomeImpl implements ServicioHome {
    private final RepositorioHome repositorioHome;

    @Autowired
    public ServicioHomeImpl(RepositorioHome repositorioHome) {
        this.repositorioHome = repositorioHome;
    }

    @Override
    public List<Anuncio> getAnuncios() {
        return this.repositorioHome.getAnuncios();
    }
}
