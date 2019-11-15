package com.infotrack.talleres.dominio.casosdeuso.alistamiento;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class EliminarAlistamiento extends CasoDeUso<Respuesta<Boolean>> {

    private AlistamientoRepositorio alistamientoRepositorio;
    private TipoAlistamientoEnum tipoAlistamiento;
    private String idAlistamiento;

    @Inject
    public EliminarAlistamiento(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                AlistamientoRepositorio alistamientoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.alistamientoRepositorio = alistamientoRepositorio;
    }

    public void setTipoAlistamiento(TipoAlistamientoEnum tipoAlistamiento) {
        this.tipoAlistamiento = tipoAlistamiento;
    }

    public void setIdAlistamiento(String idAlistamiento) {
        this.idAlistamiento = idAlistamiento;
    }

    @Override
    public Observable<Respuesta<Boolean>> crearObservableCasoDeUso() {
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            return alistamientoRepositorio.eliminarRecepcion(idAlistamiento);
        else
            return alistamientoRepositorio.eliminarDespacho(idAlistamiento);
    }
}
