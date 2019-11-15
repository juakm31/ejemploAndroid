package com.infotrack.talleres.dominio.casosdeuso.transportador;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.TransportadorRepositorio;
import com.infotrack.talleres.dominio.modelos.TransportadorMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ConsultarVehiculoPorPlaca extends CasoDeUso<Respuesta<TransportadorMdl>> {

    private final TransportadorRepositorio alistamientoRepositorio;
    private TipoAlistamientoEnum tipoAlistamiento;
    private String placa;

    @Inject
    public ConsultarVehiculoPorPlaca(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                     @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                     TransportadorRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.alistamientoRepositorio = repositorio;
    }

    public void setTipoAlistamiento(TipoAlistamientoEnum tipoAlistamiento) {
        this.tipoAlistamiento = tipoAlistamiento;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public Observable<Respuesta<TransportadorMdl>> crearObservableCasoDeUso() {
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            return alistamientoRepositorio.consultarVehiculoPorPlacaRecepcion(placa);
        else
            return alistamientoRepositorio.consultarVehiculoPorPlacaDespacho(placa);
    }
}
