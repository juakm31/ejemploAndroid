package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.base.INavegador;
import com.infotrack.talleres.transversal.constantes.negocio.DiagnosticoRechazoConstantes;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;

import javax.inject.Inject;

public class ListadoDiagnosticoRechazoActividad extends ActividadBase {

    //region Atributos
    @Inject
    INavegador navegador;

    TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazoEnum;
    //endregion

    //region Instancia
    public static Intent obtenerIntencion(Context contexto, TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazoEnum) {
        Intent intent = new Intent(contexto, ListadoDiagnosticoRechazoActividad.class);
        intent.putExtra(DiagnosticoRechazoConstantes.EXTRA_TIPO_DIAGNOSTICO_RECHAZO, tipoDiagnosticoRechazoEnum);
        return intent;
    }
    //endregion

    //region Sobrecarga
    @Override
    public int asignarLayout() {
        return R.layout.contenedor_activities;
    }

    @Override
    public void extras() {
        super.extras();
        tipoDiagnosticoRechazoEnum = (TipoDiagnosticoRechazoEnum) this.getIntent().getSerializableExtra(DiagnosticoRechazoConstantes.EXTRA_TIPO_DIAGNOSTICO_RECHAZO);
    }

    @Override
    public void iniciarActividad(Bundle bundle) {
        Fragment fragmento = getSupportFragmentManager().findFragmentById(R.id.contenedor);
        if (fragmento == null)
            agregarFragmento(R.id.contenedor, navegador.navegarListadoDiagnosticoRechazoFragmento(tipoDiagnosticoRechazoEnum));
    }

    @Override
    public void iniciarInyector() {
        super.iniciarInyector();
        AplicacionPrincipal app = (AplicacionPrincipal) this.getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void seleccionMenuNavegacion(MenuItem menuItem) {
        super.seleccionMenuNavegacion(menuItem);
        navegador.seleccionar(menuItem.getItemId(), this);
    }
    //endregion
}
