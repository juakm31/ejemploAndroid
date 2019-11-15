package com.infotrack.talleres.presentacion.componentes.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.base.INavegador;

import javax.inject.Inject;

public class MenuActividad extends ActividadBase {

    @Inject
    INavegador navegador;

    public static Intent obtenerIntencion(Context contexto) {
        return new Intent(contexto, MenuActividad.class);
    }

    @Override
    public int asignarLayout() {
        return R.layout.contenedor_activities;
    }

    @Override
    public void extras() {
        super.extras();
    }

    @Override
    public void iniciarActividad(Bundle bundle) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contenedor);
        if (fragment == null){
            fragment = navegador.navegarMenuFragmento();
            agregarFragmento(R.id.contenedor, fragment);
        }
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal aplicacionPrincipal = (AplicacionPrincipal) this.getApplication();
        aplicacionPrincipal.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void onBackPressed() {

    }
}
