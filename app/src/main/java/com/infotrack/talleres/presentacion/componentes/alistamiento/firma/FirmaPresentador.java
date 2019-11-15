package com.infotrack.talleres.presentacion.componentes.alistamiento.firma;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.dominio.casosdeuso.alistamiento.EliminarAlistamientoLocal;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;

public class FirmaPresentador extends PresentadorBase<FirmaContrato> {

    //region Atributos
    private EliminarAlistamientoLocal eliminarAlistamientoLocal;
    //endregion

    //region Constructor
    @Inject
    public FirmaPresentador(EliminarAlistamientoLocal eliminarAlistamientoLocal) {
        this.eliminarAlistamientoLocal = eliminarAlistamientoLocal;
    }
    //endregion

    //region Sobrecarga
    @Override
    public void destruir() {
        eliminarAlistamientoLocal.desechar();
    }
    //endregion

    //region Propios
    void eliminarAlistamientoLocal(String idTaller, String idAlistamiento, TipoAlistamientoEnum tipoAlistamiento) {
        eliminarAlistamientoLocal.setIdAlistamiento(idAlistamiento);
        eliminarAlistamientoLocal.setIdTaller(idTaller);
        eliminarAlistamientoLocal.setTipoAlistamiento(tipoAlistamiento);
        eliminarAlistamientoLocal.ejecutar(new ObservadorEliminarAlistamientoLocal());
    }
    //endregion

    //region Observadores
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
