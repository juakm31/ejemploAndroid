package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.caso;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.dominio.casosdeuso.caso.InsertarCaso;
import com.infotrack.talleres.dominio.modelos.CasoMdl;
import com.infotrack.talleres.presentacion.vistamodelos.CasoVM;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;

import javax.inject.Inject;

public class CasoPresentador extends PresentadorBase<CasoContrato> {

    private InsertarCaso insertarCaso;
    private MapeadorGenerico<CasoVM, CasoMdl> mapCaso;

    @Inject
    public CasoPresentador(InsertarCaso insertarCaso, MapeadorGenerico<CasoVM, CasoMdl> mapCaso) {
        this.insertarCaso = insertarCaso;
        this.mapCaso = mapCaso;
    }

    @Override
    public void destruir() {
        insertarCaso.desechar();
    }

    void persistirCaso(String idTaller, CasoVM caso, TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazo) {
        obtenerVista().tareaEnProceso();
        insertarCaso.setIdTaller(idTaller);
        insertarCaso.setCaso(mapCaso.mapear(caso, new CasoMdl()));
        insertarCaso.setTipoDiagnosticoRechazo(tipoDiagnosticoRechazo);
        insertarCaso.ejecutar(new PersistirCasoObservador());
    }

    private class PersistirCasoObservador extends AccionesObservador<Void> {
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
            obtenerVista().tareaTerminada();
        }
    }
}
