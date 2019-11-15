package com.infotrack.talleres.dominio.casosdeuso.alistamiento;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.dominio.modelos.AlistamientoMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class CrearAlistamiento extends CasoDeUso<Respuesta<AlistamientoMdl>> {

    private final AlistamientoRepositorio alistamientoRepositorio;
    private AlistamientoMdl alistamientoMdl;
    private TipoAlistamientoEnum tipoAlistamiento;

    @Inject
    public CrearAlistamiento(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                             @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                             AlistamientoRepositorio alistamientoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.alistamientoRepositorio = alistamientoRepositorio;
    }

    public void setAlistamientoMdl(AlistamientoMdl alistamientoMdl) {
        this.alistamientoMdl = alistamientoMdl;
    }

    public void setTipoAlistamiento(TipoAlistamientoEnum tipoAlistamiento) {
        this.tipoAlistamiento = tipoAlistamiento;
    }

    @Override
    public Observable<Respuesta<AlistamientoMdl>> crearObservableCasoDeUso() {
        if (tipoAlistamiento == TipoAlistamientoEnum.Recepcion)
            return alistamientoRepositorio.crearRecepcion(alistamientoMdl);
        else
            return alistamientoRepositorio.crearDespacho(alistamientoMdl);
    }
}
