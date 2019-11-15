package com.infotrack.talleres.presentacion.componentes.alistamiento.crear;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.talleres.R;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

public class CrearAlistamientoActividad extends ActividadBase {

    TipoAlistamientoEnum tipoAlistamientoEnum;

    public static Intent obtenerIntencion(Context contexto, TipoAlistamientoEnum tipoAlistamientoEnum) {
        Intent intent = new Intent(contexto, CrearAlistamientoActividad.class);
        intent.putExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamientoEnum);
        return intent;
    }

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
            agregarFragmento(R.id.contenedor, CrearAlistamientoFragmento.obtenerInstancia(tipoAlistamientoEnum));
    }

    @Override
    public boolean usarBarraNavegacion() {
        return false;
    }
}
