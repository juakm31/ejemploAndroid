package com.infotrack.talleres.dominio.casosdeuso.menu;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.MenuRepositorio;
import com.infotrack.talleres.dominio.modelos.MenuMdl;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ConsultarMenu extends CasoDeUso<Respuesta<MenuMdl>> {

    private final MenuRepositorio menuRepositorio;
    private String idPersona;
    private int idTaller;

    @Inject
    public ConsultarMenu(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                         @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                         MenuRepositorio menuRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.menuRepositorio = menuRepositorio;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public Observable<Respuesta<MenuMdl>> crearObservableCasoDeUso() {
        return menuRepositorio.ConsultarMenu(idPersona, idTaller);
    }
}
