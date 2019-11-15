package com.infotrack.talleres.dominio.casosdeuso.alistamiento;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ActualizarPropiedadesAlistamiento extends CasoDeUso<Void> {

    private final AlistamientoRepositorio alistamientoRepositorio;
    private HashMap<String, Object> mapPropiedad;

    @Inject
    public ActualizarPropiedadesAlistamiento(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                             @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                             AlistamientoRepositorio alistamientoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.alistamientoRepositorio = alistamientoRepositorio;
    }

    public void setMapPropiedad(HashMap<String, Object> mapPropiedad) {
        this.mapPropiedad = mapPropiedad;
    }

    @Override
    public Observable<Void> crearObservableCasoDeUso() {
            return alistamientoRepositorio.actualizarPropiedadAlistamiento(mapPropiedad);
    }
}
