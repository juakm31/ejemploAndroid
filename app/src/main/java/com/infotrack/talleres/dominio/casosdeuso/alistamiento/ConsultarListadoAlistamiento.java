package com.infotrack.talleres.dominio.casosdeuso.alistamiento;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.dominio.modelos.AlistamientoLocalMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ConsultarListadoAlistamiento extends CasoDeUso<List<AlistamientoLocalMdl>> {

    private final AlistamientoRepositorio repositorio;
    private TipoAlistamientoEnum tipoAlistamiento;
    private String idTaller;

    @Inject
    public ConsultarListadoAlistamiento(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                        @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                        AlistamientoRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.repositorio = repositorio;
    }

    public void setTipoAlistamiento(TipoAlistamientoEnum tipoAlistamiento) {
        this.tipoAlistamiento = tipoAlistamiento;
    }

    public void setIdTaller(String idTaller) {
        this.idTaller = idTaller;
    }

    @Override
    public Observable<List<AlistamientoLocalMdl>> crearObservableCasoDeUso() {
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            return repositorio.consultarListadoRecepcion(idTaller);
        else
            return repositorio.consultarListadoDespacho(idTaller);
    }
}
