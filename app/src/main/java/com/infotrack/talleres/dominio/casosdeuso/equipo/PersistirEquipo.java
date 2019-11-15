package com.infotrack.talleres.dominio.casosdeuso.equipo;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.EquipoRepositorio;
import com.infotrack.talleres.dominio.modelos.EquipoLocalMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class PersistirEquipo extends CasoDeUso<Void> {

    private EquipoRepositorio alistamientoRepositorio;
    private TipoAlistamientoEnum tipoAlistamiento;
    private EquipoLocalMdl equipoLocalMdl;
    private int idTaller;


    @Inject
    public PersistirEquipo(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                           @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                           EquipoRepositorio alistamientoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.alistamientoRepositorio = alistamientoRepositorio;
    }

    public void setTipoAlistamiento(TipoAlistamientoEnum tipoAlistamiento) {
        this.tipoAlistamiento = tipoAlistamiento;
    }

    public void setEquipoLocalMdl(EquipoLocalMdl equipoLocalMdl) {
        this.equipoLocalMdl = equipoLocalMdl;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    @Override
    public Observable<Void> crearObservableCasoDeUso() {
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            return alistamientoRepositorio.persistirEquipoRecepcion(equipoLocalMdl, idTaller);
        else
            return alistamientoRepositorio.persistirEquipoDespacho(equipoLocalMdl, idTaller);
    }
}
