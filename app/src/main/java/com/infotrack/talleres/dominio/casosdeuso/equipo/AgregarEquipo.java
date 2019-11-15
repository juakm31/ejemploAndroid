package com.infotrack.talleres.dominio.casosdeuso.equipo;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.EquipoRepositorio;
import com.infotrack.talleres.dominio.modelos.EquipoLocalMdl;
import com.infotrack.talleres.dominio.modelos.EquipoResMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class AgregarEquipo extends CasoDeUso<Respuesta<Boolean>> {

    private final EquipoRepositorio alistamientoRepositorio;
    private TipoAlistamientoEnum tipoAlistamientoEnum;
    private EquipoResMdl equipoResMdl;

    @Inject
    public AgregarEquipo(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                         @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                         EquipoRepositorio alistamientoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.alistamientoRepositorio = alistamientoRepositorio;
    }

    public void setTipoAlistamientoEnum(TipoAlistamientoEnum tipoAlistamientoEnum) {
        this.tipoAlistamientoEnum = tipoAlistamientoEnum;
    }

    public void setEquipoResMdl(EquipoResMdl equipoResMdl) {
        this.equipoResMdl = equipoResMdl;
    }

    @Override
    public Observable<Respuesta<Boolean>> crearObservableCasoDeUso() {
        if (tipoAlistamientoEnum.equals(TipoAlistamientoEnum.Recepcion))
            return alistamientoRepositorio.agregarEquipoRecepcion(equipoResMdl);
        else
            return alistamientoRepositorio.agregarEquipoDespacho(equipoResMdl);
    }
}
