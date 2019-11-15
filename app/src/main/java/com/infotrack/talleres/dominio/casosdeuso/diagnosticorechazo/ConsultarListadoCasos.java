package com.infotrack.talleres.dominio.casosdeuso.diagnosticorechazo;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.DiagnosticoRechazoRepositorio;
import com.infotrack.talleres.dominio.modelos.CasoMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;
import com.infotrack.talleres.transversal.firebase.rutas.DiagnosticoRechazoFirebase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ConsultarListadoCasos extends CasoDeUso<List<CasoMdl>> {
    private final DiagnosticoRechazoRepositorio repositorio;
    private TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazo;
    private String idTaller;

    @Inject
    public ConsultarListadoCasos(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                                 @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                                 DiagnosticoRechazoRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.repositorio = repositorio;
    }

    public void setTipoDiagnosticoRechazo(TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazo) {
        this.tipoDiagnosticoRechazo = tipoDiagnosticoRechazo;
    }

    public void setIdTaller(String idTaller) {
        this.idTaller = idTaller;
    }

    @Override
    public Observable<List<CasoMdl>> crearObservableCasoDeUso() {
        return repositorio.consultarListadoCasos(construirRutaFirebasePorPorceos());
    }

    private String construirRutaFirebasePorPorceos() {
        switch (tipoDiagnosticoRechazo) {
            case DiagnosticoInicial:
                return String.format(DiagnosticoRechazoFirebase.ConsultarListaDiagnosticoInicial, idTaller);
            case DiagnosticoFinal:
                return String.format(DiagnosticoRechazoFirebase.ConsultarListaDiagnosticoFinal, idTaller);
            case RechazoDiagnosticoInicial:
                return String.format(DiagnosticoRechazoFirebase.ConsultarListaRechazoDiagnosticoInicial, idTaller);
            case RechazoDiagnosticoFinal:
                return String.format(DiagnosticoRechazoFirebase.ConsultarListaRechazoDiagnosticoFinal, idTaller);
            default:
                throw new RuntimeException("Enumerador " + tipoDiagnosticoRechazo.name() + " no implementado");
        }
    }
}
