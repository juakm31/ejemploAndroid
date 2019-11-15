package com.infotrack.talleres.dominio.casosdeuso.equipo;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.EquipoRepositorio;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ActualizarEtapaEquipo extends CasoDeUso<Void> {

    private final EquipoRepositorio equipoRepositorio;
    private HashMap<String, Object> mapPropiedad;

    @Inject
    public ActualizarEtapaEquipo(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                 @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                 EquipoRepositorio equipoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.equipoRepositorio = equipoRepositorio;
    }

    public void setMapPropiedad(HashMap<String, Object> mapPropiedad) {
        this.mapPropiedad = mapPropiedad;
    }

    @Override
    public Observable<Void> crearObservableCasoDeUso() {
        return equipoRepositorio.actualizarEtapaEquipo(mapPropiedad);
    }
}
