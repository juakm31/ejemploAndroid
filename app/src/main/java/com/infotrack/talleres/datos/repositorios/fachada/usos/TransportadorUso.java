package com.infotrack.talleres.datos.repositorios.fachada.usos;

import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.TransportadorDto;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.TransportadorRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.transportador.TransportadorOrigenDatos;
import com.infotrack.talleres.dominio.modelos.TransportadorMdl;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TransportadorUso implements TransportadorRepositorio {

    private final MapeadorGenerico<Respuesta<TransportadorDto>, Respuesta<TransportadorMdl>> mapTransportador;

    @Inject
    TransportadorUso(MapeadorGenerico<Respuesta<TransportadorDto>, Respuesta<TransportadorMdl>> mapTransportador) {
        this.mapTransportador = mapTransportador;
    }

    private TransportadorOrigenDatos generarFabricaRetrofit() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Transportador);
        return ((TransportadorOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.RETROFIT.toString()));
    }

    @Override
    public Observable<Respuesta<TransportadorMdl>> consultarVehiculoPorPlacaRecepcion(String placa) {
        return generarFabricaRetrofit()
                .consultarVehiculoPorPlacaRecepcion(placa)
                .map(transportadorDtoRespuesta -> mapTransportador.mapear(transportadorDtoRespuesta, new Respuesta<TransportadorMdl>()));
    }

    @Override
    public Observable<Respuesta<TransportadorMdl>> consultarVehiculoPorPlacaDespacho(String placa) {
        return generarFabricaRetrofit()
                .consultarVehiculoPorPlacaDespacho(placa)
                .map(transportadorDtoRespuesta -> mapTransportador.mapear(transportadorDtoRespuesta, new Respuesta<TransportadorMdl>()));
    }
}
