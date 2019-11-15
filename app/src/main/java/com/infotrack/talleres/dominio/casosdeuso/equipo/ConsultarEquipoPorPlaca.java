package com.infotrack.talleres.dominio.casosdeuso.equipo;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.EquipoRepositorio;
import com.infotrack.talleres.dominio.modelos.EquipoMdl;
import com.infotrack.talleres.transversal.enumeradores.ProcesoEquipoEnum;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ConsultarEquipoPorPlaca extends CasoDeUso<Respuesta<EquipoMdl>> {

    //region Atributos
    private EquipoRepositorio repositorio;
    private ProcesoEquipoEnum procesoEquipo;
    private String placa;
    private String idPersona;
    //endregion

    //region Constructor
    @Inject
    ConsultarEquipoPorPlaca(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                            @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                            EquipoRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.repositorio = repositorio;
    }
    //endregion

    //region Propiedades
    public void setProcesoEquipo(ProcesoEquipoEnum procesoEquipo) {
        this.procesoEquipo = procesoEquipo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }
    //endregion

    //region Observadores Desechables
    @Override
    public Observable<Respuesta<EquipoMdl>> crearObservableCasoDeUso() {
        return repositorio.consultarEquipoPorPlaca(placa, idPersona, procesoEquipo);
    }
    //endregion
}
