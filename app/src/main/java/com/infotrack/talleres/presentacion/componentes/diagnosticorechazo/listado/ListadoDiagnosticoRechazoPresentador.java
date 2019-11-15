package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.dominio.casosdeuso.diagnosticorechazo.ConsultarListadoCasos;
import com.infotrack.talleres.dominio.modelos.CasoMdl;
import com.infotrack.talleres.presentacion.vistamodelos.CasoVM;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;

import java.util.List;

import javax.inject.Inject;

public class ListadoDiagnosticoRechazoPresentador extends PresentadorBase<ListadoDiagnosticoRechazoContrato> {

    //region Atributos
    private ConsultarListadoCasos consultarListadoCasos;
    private MapeadorGenerico<CasoMdl, CasoVM> mapCaso;
    //endregion

    //region Constructor
    @Inject
    public ListadoDiagnosticoRechazoPresentador(ConsultarListadoCasos consultarListadoCasos,
                                                MapeadorGenerico<CasoMdl, CasoVM> mapCaso) {
        this.consultarListadoCasos = consultarListadoCasos;
        this.mapCaso = mapCaso;
    }
    //endregion

    //region Sobrecarga
    @Override
    public void destruir() {
        consultarListadoCasos.desechar();
    }
    //endregion

    //region Propios
    void consultarListadoCasos(String idTaller, TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazoEnum) {
        obtenerVista().tareaEnProceso();
        consultarListadoCasos.setIdTaller(idTaller);
        consultarListadoCasos.setTipoDiagnosticoRechazo(tipoDiagnosticoRechazoEnum);
        consultarListadoCasos.ejecutar(new ObservadorConsultarListadoCasos());
    }
    //endregion

    //region Observadores
    class ObservadorConsultarListadoCasos extends AccionesObservador<List<CasoMdl>> {
        @Override
        public void onNext(List<CasoMdl> value) {
            super.onNext(value);
            obtenerVista().actualizarListadoDiagnosticoRechazo(mapCaso.mapear(value, new CasoVM()));
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
    //endregion
}
