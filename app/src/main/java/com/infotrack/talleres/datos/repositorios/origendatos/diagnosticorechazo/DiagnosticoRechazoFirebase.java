package com.infotrack.talleres.datos.repositorios.origendatos.diagnosticorechazo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.infotrack.artefactos.utilitarios.firebase.RxFirebaseHelper;
import com.infotrack.talleres.datos.entidades.CasoDto;
import com.infotrack.talleres.transversal.firebase.ConstantesFirebase;

import java.util.List;

import io.reactivex.Observable;

public class DiagnosticoRechazoFirebase implements DiagnosticoRechazoOrigenDatos {
    private RxFirebaseHelper firebaseHelper = new RxFirebaseHelper(ConstantesFirebase.NODO_RAIZ);
    private DatabaseReference referencia = firebaseHelper.obtenerReferencia();

    @Override
    public Observable<List<CasoDto>> consultarListadoCasos(String rutaDinamica) {
        Query query = referencia.child(rutaDinamica);
        return firebaseHelper.consultaListaTiempoReal(query, CasoDto.class);
    }
}
