package com.infotrack.talleres.dominio.casosdeuso.equipo;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.EquipoRepositorio;
import com.infotrack.talleres.dominio.modelos.EquipoLocalMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ConsultarListadoEquipos extends CasoDeUso<List<EquipoLocalMdl>> {

    private final EquipoRepositorio repositorio;
    private TipoAlistamientoEnum tipoAlistamiento;
    private String idTaller;
    private String idAlistamiento;

    @Inject
    public ConsultarListadoEquipos(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                   @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                   EquipoRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.repositorio = repositorio;
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
    public Observable<List<EquipoLocalMdl>> crearObservableCasoDeUso() {
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            return repositorio.consultarListadoEquiposRecepcion(idTaller, idAlistamiento);
        else
            return repositorio.consultarListadoEquiposDespacho(idTaller, idAlistamiento);
    }
}
