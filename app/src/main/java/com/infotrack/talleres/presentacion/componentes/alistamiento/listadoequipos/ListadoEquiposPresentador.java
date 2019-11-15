package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoequipos;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.dominio.casosdeuso.alistamiento.ActualizarPropiedadesAlistamiento;
import com.infotrack.talleres.dominio.casosdeuso.alistamiento.EliminarEquipo;
import com.infotrack.talleres.dominio.casosdeuso.equipo.ConsultarListadoEquipos;
import com.infotrack.talleres.dominio.modelos.EquipoLocalMdl;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class ListadoEquiposPresentador extends PresentadorBase<ListadoEquiposContrato> {

    //region Atributos
    private ConsultarListadoEquipos consultarListadoEquipos;
    private EliminarEquipo eliminarEquipo;
    private ActualizarPropiedadesAlistamiento actualizarPropiedadesAlistamiento;
    private MapeadorGenerico<EquipoLocalMdl, EquipoLocalVM> mapEquiposLocal;
    //endregion

    //region Constructor
    @Inject
    public ListadoEquiposPresentador(ConsultarListadoEquipos consultarListadoEquipos,
                                     MapeadorGenerico<EquipoLocalMdl, EquipoLocalVM> mapEquiposLocal,
                                     EliminarEquipo eliminarEquipo,
                                     ActualizarPropiedadesAlistamiento actualizarPropiedadesAlistamiento) {
        this.consultarListadoEquipos = consultarListadoEquipos;
        this.mapEquiposLocal = mapEquiposLocal;
        this.eliminarEquipo = eliminarEquipo;
        this.actualizarPropiedadesAlistamiento = actualizarPropiedadesAlistamiento;
    }
    //endregion

    //region Sobrecarga
    @Override
    public void destruir() {
        consultarListadoEquipos.desechar();
        eliminarEquipo.desechar();
    }
    //endregion

    //region Propios

    void consultarEquipos(String idTaller, TipoAlistamientoEnum tipoAlistamientoEnum, String idAlistamiento) {
        obtenerVista().tareaEnProceso();
        consultarListadoEquipos.setIdTaller(idTaller);
        consultarListadoEquipos.setTipoAlistamiento(tipoAlistamientoEnum);
        consultarListadoEquipos.setIdAlistamiento(idAlistamiento);
        consultarListadoEquipos.ejecutar(new ObservadorConsultarListadoEquipos());
    }

    void eliminarEquipo(String idAlistamiento, String placa, TipoAlistamientoEnum tipoAlistamiento, int idTaller) {
        eliminarEquipo.setIdTaller(idTaller);
        eliminarEquipo.setIdAlistamiento(idAlistamiento);
        eliminarEquipo.setPlaca(placa);
        eliminarEquipo.setTipoAlistamiento(tipoAlistamiento);
        eliminarEquipo.ejecutar(new EliminarEquipoObservador());
    }

    void actualizarPropiedadesAlistamiento(HashMap<String, Object> mapPropiedades){
        actualizarPropiedadesAlistamiento.setMapPropiedad(mapPropiedades);
        actualizarPropiedadesAlistamiento.ejecutar(new ObservadorActualizarAlistamiento());
    }
    //endregion

    //region Observadores
    class ObservadorConsultarListadoEquipos extends AccionesObservador<List<EquipoLocalMdl>> {
        @Override
        public void onNext(List<EquipoLocalMdl> value) {
            super.onNext(value);
            obtenerVista().respuestaConsultaEquipos(mapEquiposLocal.mapear(value, new EquipoLocalVM()));
            obtenerVista().tareaTerminada();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    class EliminarEquipoObservador extends AccionesObservador<Void> {
        @Override
        public void onNext(Void value) {
            super.onNext(value);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    class ObservadorActualizarAlistamiento extends AccionesObservador<Void> {
        @Override
        public void onNext(Void value) {
            super.onNext(value);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
    //endregion
}
