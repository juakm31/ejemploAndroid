package com.infotrack.talleres.dominio.casosdeuso.configuracion;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.ConfiguracionRepositorio;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ConsultarPlantillaTipoEquipo extends CasoDeUso<String> {

    private final ConfiguracionRepositorio repositorio;
    private String tipoEquipo;

    @Inject
    public ConsultarPlantillaTipoEquipo(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                        @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                        ConfiguracionRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.repositorio = repositorio;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    @Override
    public Observable<String> crearObservableCasoDeUso() {
        return repositorio.consultarPlantillaTipoEquipo(tipoEquipo);
    }
}
