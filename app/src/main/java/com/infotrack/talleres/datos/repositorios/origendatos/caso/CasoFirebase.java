package com.infotrack.talleres.datos.repositorios.origendatos.caso;

import com.google.firebase.database.DatabaseReference;
import com.infotrack.artefactos.utilitarios.firebase.RxFirebaseHelper;
import com.infotrack.talleres.datos.entidades.CasoDto;
import com.infotrack.talleres.transversal.firebase.ConstantesFirebase;

import io.reactivex.Observable;

public class CasoFirebase implements CasoOrigenDatos {

    private RxFirebaseHelper firebaseHelper = new RxFirebaseHelper(ConstantesFirebase.NODO_RAIZ);
    private DatabaseReference referencia = firebaseHelper.obtenerReferencia();

    @Override
    public Observable<Void> persistirCaso(String rutaDinamica, CasoDto caso) {
        return Observable.create(emitter -> {
            referencia.child(rutaDinamica)
                    .setValue(caso);
            emitter.onComplete();
        });
    }
}
