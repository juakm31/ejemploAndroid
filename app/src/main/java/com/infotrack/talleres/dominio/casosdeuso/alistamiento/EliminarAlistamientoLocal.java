package com.infotrack.talleres.dominio.casosdeuso.alistamiento;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class EliminarAlistamientoLocal extends CasoDeUso<Void> {

    private AlistamientoRepositorio alistamientoRepositorio;
    private TipoAlistamientoEnum tipoAlistamiento;
    private String idTaller;
    private String idAlistamiento;

    @Inject
    EliminarAlistamientoLocal(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                              @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                              AlistamientoRepositorio alistamientoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.alistamientoRepositorio = alistamientoRepositorio;
    }

    public void setTipoAlistamiento(TipoAlistamientoEnum tipoAlistamiento) {
        this.tipoAlistamiento = tipoAlistamiento;
    }

    public void setIdTaller(String idTaller) {
        this.idTaller = idTaller;
    }

    public void setIdAlistamiento(String idAlistamiento) {
        this.idAlistamiento = idAlistamiento;
    }

    @Override
    public Observable<Void> crearObservableCasoDeUso() {
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            return alistamientoRepositorio.eliminarRecepcionlocal(idAlistamiento, idTaller);
        else
            return alistamientoRepositorio.eliminarDespachoLocal(idAlistamiento, idTaller);
    }
}
