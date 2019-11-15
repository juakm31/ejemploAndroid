package com.infotrack.talleres.datos.repositorios.origendatos.configuracion;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.infotrack.artefactos.utilitarios.firebase.RxFirebaseHelper;
import com.infotrack.talleres.transversal.firebase.ConstantesFirebase;

import io.reactivex.Observable;

public class ConfiguracionFirebase implements ConfiguracionOrigenDatos {

    private RxFirebaseHelper firebaseHelper = new RxFirebaseHelper(ConstantesFirebase.NODO_RAIZ);
    private DatabaseReference referencia = firebaseHelper.obtenerReferencia();

    @Override
    public Observable<String> consultarPlantillaTipoEquipo(String tipoEquipo) {
        Query query = referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.ConfiguracionFirebase.ConsultarPlantillaTipoEquipo, tipoEquipo));
        return firebaseHelper.consultaTiempoRealUnicaVez(query, String.class, false);
    }
}
