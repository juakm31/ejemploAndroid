package com.infotrack.talleres.dominio.casosdeuso.caso;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.CasoRepositorio;
import com.infotrack.talleres.dominio.modelos.CasoMdl;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;
import com.infotrack.talleres.transversal.firebase.rutas.CasoFirebase;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class InsertarCaso extends CasoDeUso<Void> {

    private final CasoRepositorio repositorio;
    private String idTaller;
    private TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazo;
    private CasoMdl caso;

    @Inject
    public InsertarCaso(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                        @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                        CasoRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.repositorio = repositorio;
    }

    public void setIdTaller(String idTaller) {
        this.idTaller = idTaller;
    }

    public void setCaso(CasoMdl caso) {
        this.caso = caso;
    }

    public void setTipoDiagnosticoRechazo(TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazo) {
        this.tipoDiagnosticoRechazo = tipoDiagnosticoRechazo;
    }

    @Override
    public Observable<Void> crearObservableCasoDeUso() {
        return repositorio.persistirCaso(construirRutaFirebaseProceso(), caso);
    }

    private String construirRutaFirebaseProceso() {
        switch (tipoDiagnosticoRechazo) {
            case DiagnosticoInicial:
                return String.format(CasoFirebase.PersistirDiagnosticoInicial, idTaller, caso.getIdCaso());
            case DiagnosticoFinal:
                return String.format(CasoFirebase.PersistirDiagnosticoFinal, idTaller, caso.getIdCaso());
            case RechazoDiagnosticoInicial:
                return String.format(CasoFirebase.PersistirRechazoDiagnosticoInicial, idTaller, caso.getIdCaso());
            case RechazoDiagnosticoFinal:
                return String.format(CasoFirebase.PersistirRechazoDiagnosticoFinal, idTaller, caso.getIdCaso());
            default:
                throw new RuntimeException("Enumerador " + tipoDiagnosticoRechazo.name() + " no implementado");
        }
    }
}
