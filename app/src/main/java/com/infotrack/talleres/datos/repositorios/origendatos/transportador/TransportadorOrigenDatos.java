package com.infotrack.talleres.datos.repositorios.origendatos.transportador;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.TransportadorDto;
import com.infotrack.talleres.transversal.constantes.negocio.TransportadorConstantes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TransportadorOrigenDatos {
    @GET(TransportadorConstantes.URL_CONSULTAR_PLACA_RECEPCION)
    Observable<Respuesta<TransportadorDto>> consultarVehiculoPorPlacaRecepcion(@Path("placa") String placa);

    @GET(TransportadorConstantes.URL_CONSULTAR_PLACA_DESPACHO)
    Observable<Respuesta<TransportadorDto>> consultarVehiculoPorPlacaDespacho(@Path("placa") String placa);
}
