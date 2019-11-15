package com.infotrack.talleres.dominio.casosdeuso.listachequeo;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.ListaChequeoRepositorio;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ConsultarListaChequeo extends CasoDeUso<String> {

    private final ListaChequeoRepositorio repositorio;
    private String recepcionId;
    private String placa;

    @Inject
    public ConsultarListaChequeo(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                 @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                 ListaChequeoRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.repositorio = repositorio;
    }

    public void setRecepcionId(String recepcionId) {
        this.recepcionId = recepcionId;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public Observable<String> crearObservableCasoDeUso() {
        return repositorio.consultarListaChequeo(recepcionId, placa);
    }
}
