package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.dominio.modelos.TransportadorMdl;

import io.reactivex.Observable;

public interface TransportadorRepositorio {
    Observable<Respuesta<TransportadorMdl>> consultarVehiculoPorPlacaRecepcion(String placa);

    Observable<Respuesta<TransportadorMdl>> consultarVehiculoPorPlacaDespacho(String placa);
}
