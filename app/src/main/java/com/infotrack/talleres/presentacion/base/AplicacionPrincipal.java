package com.infotrack.talleres.presentacion.base;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.infotrack.artefactos.workermicroservicios.WorkApplication;
import com.infotrack.talleres.di.componente.ComponentePrincipal;
import com.infotrack.talleres.di.componente.DaggerComponentePrincipal;
import com.infotrack.talleres.di.modulo.ModuloPrincipal;

public class AplicacionPrincipal extends WorkApplication {

    //region Propiedades
    private ComponentePrincipal componente;
    //endregion

    //region Metodos De Sobrecarga
    @Override
    public void onCreate() {
        super.onCreate();

        if (!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        inicializarInyector();
    }
    //endregion

    //region Metodos Propios
    private void inicializarInyector() {
        componente = DaggerComponentePrincipal.builder().moduloPrincipal(new ModuloPrincipal(this)).build();
    }

    public ComponentePrincipal obtenerComponentePrincipal() {
        return componente;
    }
    //endregion
}
