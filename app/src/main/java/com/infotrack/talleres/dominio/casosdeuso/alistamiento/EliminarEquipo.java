package com.infotrack.talleres.dominio.casosdeuso.alistamiento;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.EquipoRepositorio;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class EliminarEquipo extends CasoDeUso<Void> {

    private EquipoRepositorio equipoRepositorio;
    private int idTaller;
    private String idAlistamiento;
    private String placa;
    private TipoAlistamientoEnum tipoAlistamiento;

    @Inject
    public EliminarEquipo(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                          @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                          EquipoRepositorio equipoRepositorio) {
        super(hiloEjecutor, hiloUI);
        this.equipoRepositorio = equipoRepositorio;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public void setIdAlistamiento(String idAlistamiento) {
        this.idAlistamiento = idAlistamiento;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setTipoAlistamiento(TipoAlistamientoEnum tipoAlistamiento) {
        this.tipoAlistamiento = tipoAlistamiento;
    }

    @Override
    public Observable<Void> crearObservableCasoDeUso() {
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            return equipoRepositorio.eliminarEquipoRececepcion(idTaller,idAlistamiento,placa);
        else
            return equipoRepositorio.eliminarEquipoDespacho(idTaller,idAlistamiento,placa);
    }
}
