package com.infotrack.talleres.dominio.casosdeuso.alistamiento;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.dominio.modelos.AlistamientoLocalMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class PersistirAlistamiento extends CasoDeUso<Void> {

    private AlistamientoRepositorio alistamientoRepositorio;
    private AlistamientoLocalMdl alistamientoLocalMdl;
    private TipoAlistamientoEnum tipoAlistamientoEnum;

    @Inject
    public PersistirAlistamiento(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                 @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                 AlistamientoRepositorio alistamientoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.alistamientoRepositorio = alistamientoRepositorio;
    }

    public void setAlistamientoLocalVM(AlistamientoLocalMdl alistamientoLocalMdl) {
        this.alistamientoLocalMdl = alistamientoLocalMdl;
    }

    public void setTipoAlistamientoEnum(TipoAlistamientoEnum tipoAlistamientoEnum) {
        this.tipoAlistamientoEnum = tipoAlistamientoEnum;
    }

    @Override
    public Observable<Void> crearObservableCasoDeUso() {
        if (tipoAlistamientoEnum == TipoAlistamientoEnum.Recepcion)
            return alistamientoRepositorio.persistirRecepcionLocal(alistamientoLocalMdl);
        else
            return alistamientoRepositorio.persistirDespachoLocal(alistamientoLocalMdl);
    }
}
