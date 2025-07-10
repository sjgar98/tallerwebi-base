package com.tallerwebi.dominio;



import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class ServicioPago {
    private static final float PRECIO_UNITARIO = 10.0f;

    public Preference generarPreferencia(int cantidadOro, Long userId) throws Exception {
        MercadoPago.SDK.setAccessToken("TEST-7116902112367926-070208-10cdbe939851cca56079b7f00943eec0-472921365");//

        Item item = new Item();
                item.setTitle("Compra de oro - Jugador " + userId);
                item.setQuantity(cantidadOro);
                item.setUnitPrice(PRECIO_UNITARIO);
                item.setCurrencyId("ARS");

        ArrayList<Item> items = new ArrayList<>();
        items.add(item);

        Preference preference = new Preference()
                .setItems(items);

        preference.save();
        System.out.println("Preferencia creada: " + preference.getInitPoint());
        System.out.println("HTTP Status: " + preference.getLastApiResponse().getStatusCode());
        System.out.println("Response Body: " + preference.getLastApiResponse().getStringResponse());

        return preference;

    }
}
