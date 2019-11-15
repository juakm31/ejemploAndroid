package com.infotrack.talleres.dominio.casosdeuso.listachequeo;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.ListaChequeoRepositorio;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class PersistirListaChequeo extends CasoDeUso<Void> {

    private final ListaChequeoRepositorio repositorio;
    private String recepcionId;
    private String placa;
    private String listaChequeo;

    @Inject
    public PersistirListaChequeo(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
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

    public void setListaChequeo(String listaChequeo) {
        this.listaChequeo = listaChequeo;
    }

    @Override
    public Observable<Void> crearObservableCasoDeUso() {
        return repositorio.persistirListaChequeo(recepcionId, placa, listaChequeo);
    }
}
