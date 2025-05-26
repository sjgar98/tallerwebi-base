package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("servicioHome")
@Transactional
public class ServicioHomeImpl implements ServicioHome {

    @Autowired
    public ServicioHomeImpl() {}

    @Override
    public List<Anuncio> getAnuncios() {
        List<Anuncio> anuncios = new ArrayList<Anuncio>();
        anuncios.add(new Anuncio().setTitle("Release 0.0.1-ALPHA").setContent("Anuncio inicial mockeado"));
        anuncios.add(new Anuncio().setTitle("Release 0.0.2-ALPHA").setContent("Segundo update mockeado, mostrar arriba de todo"));
        Collections.reverse(anuncios);
        return anuncios;
    }
}
