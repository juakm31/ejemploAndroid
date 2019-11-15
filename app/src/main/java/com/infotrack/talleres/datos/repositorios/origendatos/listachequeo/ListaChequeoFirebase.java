package com.infotrack.talleres.datos.repositorios.origendatos.listachequeo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.infotrack.artefactos.utilitarios.firebase.RxFirebaseHelper;
import com.infotrack.talleres.transversal.firebase.ConstantesFirebase;

import io.reactivex.Observable;

public class ListaChequeoFirebase implements ListaChequeoOrigenDatos {

    private RxFirebaseHelper firebaseHelper = new RxFirebaseHelper(ConstantesFirebase.NODO_RAIZ);
    private DatabaseReference referencia = firebaseHelper.obtenerReferencia();

    @Override
    public Observable<String> consultarListaChequeo(String recepcionId, String placa) {
        Query query = referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.ListaChequeoFirebase.ConsultarListaChequeo, recepcionId, placa));
        return firebaseHelper.consultaTiempoRealUnicaVez(query, String.class, false);
    }

    @Override
    public Observable<Void> persistirListaChequeo(String recepcionId, String placa, String listaChequeo) {
        return Observable.create(emitter -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.ListaChequeoFirebase.ConsultarListaChequeo, recepcionId, placa))
                    .setValue(listaChequeo);
            emitter.onComplete();
        });
    }
}
