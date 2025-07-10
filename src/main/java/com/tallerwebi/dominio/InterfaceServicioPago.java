package com.tallerwebi.dominio;
import com.mercadopago.resources.Preference;

public interface InterfaceServicioPago {
    Preference generarPreferencia(Integer cantidadOro, Long userId) throws Exception;

    void procesarCompraExitosa(Integer cantidadOro, Long userId);
}
