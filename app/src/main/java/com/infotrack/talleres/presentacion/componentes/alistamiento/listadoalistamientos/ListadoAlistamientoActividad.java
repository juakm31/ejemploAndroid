package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoalistamientos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.base.INavegador;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;

public class ListadoAlistamientoActividad extends ActividadBase {

    //region Atributos
    @Inject
    INavegador navegador;

    TipoAlistamientoEnum tipoAlistamientoEnum;
    //endregion

    //region Instancia
    public static Intent obtenerIntencion(Context contexto, TipoAlistamientoEnum tipoAlistamientoEnum) {
        Intent intent = new Intent(contexto, ListadoAlistamientoActividad.class);
        intent.putExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamientoEnum);
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
        tipoAlistamientoEnum = (TipoAlistamientoEnum) this.getIntent().getSerializableExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO);
    }

    @Override
    public void iniciarActividad(Bundle bundle) {
        Fragment fragmento = getSupportFragmentManager().findFragmentById(R.id.contenedor);
        if (fragmento == null)
            agregarFragmento(R.id.contenedor, navegador.navegarListadoAlistamientoFragmento(tipoAlistamientoEnum));
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
