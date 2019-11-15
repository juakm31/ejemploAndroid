package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoalistamientos;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.comunes.CadenasHelper;
import com.infotrack.talleres.dominio.casosdeuso.alistamiento.ConsultarListadoAlistamiento;
import com.infotrack.talleres.dominio.casosdeuso.alistamiento.EliminarAlistamiento;
import com.infotrack.talleres.dominio.casosdeuso.alistamiento.EliminarAlistamientoLocal;
import com.infotrack.talleres.dominio.modelos.AlistamientoLocalMdl;
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoLocalVM;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import java.util.List;

import javax.inject.Inject;

public class ListadoAlistamientoPresentador extends PresentadorBase<ListadoAlistamientoContrato> {

    //region Atributos
    private ConsultarListadoAlistamiento consultarListadoAlistamiento;
    private EliminarAlistamiento eliminarAlistamiento;
    private EliminarAlistamientoLocal eliminarAlistamientoLocal;
    private MapeadorGenerico<AlistamientoLocalMdl, AlistamientoLocalVM> mapAlistamientoLocal;
    private String idTaller;
    private String idAlistamiento;
    private TipoAlistamientoEnum tipoAlistamientoEnum;
    //endregion

    //region Constructor
    @Inject
    public ListadoAlistamientoPresentador(ConsultarListadoAlistamiento consultarListadoAlistamiento,
                                          EliminarAlistamiento eliminarAlistamiento,
                                          EliminarAlistamientoLocal eliminarAlistamientoLocal,
                                          MapeadorGenerico<AlistamientoLocalMdl, AlistamientoLocalVM> mapAlistamientoLocal) {
        this.consultarListadoAlistamiento = consultarListadoAlistamiento;
        this.eliminarAlistamiento = eliminarAlistamiento;
        this.eliminarAlistamientoLocal = eliminarAlistamientoLocal;
        this.mapAlistamientoLocal = mapAlistamientoLocal;
    }
    //endregion

    //region Sobrecarga
    @Override
    public void destruir() {
        consultarListadoAlistamiento.desechar();
        eliminarAlistamiento.desechar();
        eliminarAlistamientoLocal.desechar();
    }
    //endregion

    //region Propios
    void consultarListadoAlistamiento(String idTaller, TipoAlistamientoEnum tipoAlistamientoEnum) {
        obtenerVista().tareaEnProceso();
        consultarListadoAlistamiento.setIdTaller(idTaller);
        consultarListadoAlistamiento.setTipoAlistamiento(tipoAlistamientoEnum);
        consultarListadoAlistamiento.ejecutar(new ObservadorConsultarListadoAlistamiento());
    }

    void eliminarAlistamiento(String idTaller, String idAlistamiento, TipoAlistamientoEnum tipoAlistamiento) {
        this.idTaller = idTaller;
        this.idAlistamiento = idAlistamiento;
        this.tipoAlistamientoEnum = tipoAlistamiento;
        eliminarAlistamiento.setIdAlistamiento(idAlistamiento);
        eliminarAlistamiento.setTipoAlistamiento(tipoAlistamientoEnum);
        eliminarAlistamiento.ejecutar(new ObservadorEliminarAlistamiento());
    }

    private void eliminarAlistamientoLocal(String idTaller, String idAlistamiento, TipoAlistamientoEnum tipoAlistamiento) {
        eliminarAlistamientoLocal.setIdAlistamiento(idAlistamiento);
        eliminarAlistamientoLocal.setIdTaller(idTaller);
        eliminarAlistamientoLocal.setTipoAlistamiento(tipoAlistamiento);
        eliminarAlistamientoLocal.ejecutar(new ObservadorEliminarAlistamientoLocal());
    }
    //endregion

    //region Observadores
    class ObservadorConsultarListadoAlistamiento extends AccionesObservador<List<AlistamientoLocalMdl>> {
        @Override
        public void onNext(List<AlistamientoLocalMdl> value) {
            super.onNext(value);
            obtenerVista().actualizarListadoAlistamiento(mapAlistamientoLocal.mapear(value, new AlistamientoLocalVM()));
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

    class ObservadorEliminarAlistamiento extends AccionesObservador<Respuesta<Boolean>> {
        @Override
        public void onNext(Respuesta<Boolean> value) {
            super.onNext(value);
            if (value.isResultado()) {
                eliminarAlistamientoLocal(idTaller, idAlistamiento, tipoAlistamientoEnum);
            } else {
                obtenerVista().monstrarMensajesError(new CadenasHelper().encadenarListaMensajesConComodin(value.getMensajes(), CadenasHelper.SALTO_LINEA));
            }
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

    class ObservadorEliminarAlistamientoLocal extends AccionesObservador<Void> {
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
