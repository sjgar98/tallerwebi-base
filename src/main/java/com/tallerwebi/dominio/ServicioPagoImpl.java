package com.tallerwebi.dominio;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.tallerwebi.dominio.entidad.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tallerwebi.dominio.ServicioJugador;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ServicioPagoImpl implements InterfaceServicioPago {
    private static final Float PRECIO_UNITARIO = 10.0F;
    private static final String BASE_URL = "https://03a06bf244d4.ngrok-free.app";
    private final ServicioJugador servicioJugador;

    @Autowired
    public ServicioPagoImpl(ServicioJugador servicioJugador) {
        this.servicioJugador = servicioJugador;
    }

    @Override
    public Preference generarPreferencia(Integer cantidadOro, Long userId) throws Exception {
        MercadoPago.SDK.setAccessToken("TEST-7116902112367926-070208-10cdbe939851cca56079b7f00943eec0-472921365");
        Item item = new Item()
                .setTitle("Compra de oro - Jugador " + userId)
                .setQuantity(cantidadOro)
                .setUnitPrice(Float.valueOf(PRECIO_UNITARIO))
                .setCurrencyId("ARS");

        var items = new ArrayList<Item>();
        items.add(item);

        BackUrls backUrls = new BackUrls()
                .setSuccess(BASE_URL + "/tienda?quantity=" + cantidadOro)
                .setFailure(BASE_URL + "/tienda?error=true");

        Preference preference = new Preference()
                .setItems(items)
                .setBackUrls(backUrls)
                .setAutoReturn(Preference.AutoReturn.approved);

        preference.save();

        System.out.println("Preferencia creada: " + preference.getId());
        return preference;
    }

    @Override
    public void procesarCompraExitosa(Integer cantidadOro, Long userId) {
        Jugador jugador = servicioJugador.getJugadorActual(userId);
        servicioJugador.agregarDinero(jugador, Long.valueOf(cantidadOro));
    }
}