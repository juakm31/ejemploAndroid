package com.infotrack.talleres.presentacion.componentes.alistamiento.listachequeo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.base.INavegador;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.constantes.negocio.ListaChequeoConstantes;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;

public class ListaChequeoActividad extends ActividadBase {

    //region Atributos
    TipoAlistamientoEnum tipoAlistamientoEnum;

    @Inject
    INavegador navegador;

    private EquipoLocalVM equipoLocal;
    //endregion

    //region Instancia
    public static Intent obtenerIntencion(Context contexto, EquipoLocalVM equipoLocal, TipoAlistamientoEnum tipoAlistamientoEnum){
        Intent intencion = new Intent(contexto, ListaChequeoActividad.class);
        intencion.putExtra(ListaChequeoConstantes.EXTRA_EQUIPO_LOCAL, equipoLocal);
        intencion.putExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamientoEnum);
        return intencion;
    }
    //endregion

    //region Actividad Base
    @Override
    public int asignarLayout() {
        return R.layout.contenedor_activities;
    }

    @Override
    public void iniciarActividad(Bundle bundle) {
        Fragment fragmento = getSupportFragmentManager().findFragmentById(R.id.contenedor);
        if (fragmento == null){
            fragmento = navegador.navegarListaChequeoFragmento(equipoLocal, tipoAlistamientoEnum);
            agregarFragmento(R.id.contenedor, fragmento);
        }

    }

    @Override
    public void extras() {
        super.extras();
        this.equipoLocal = (EquipoLocalVM) getIntent().getSerializableExtra(ListaChequeoConstantes.EXTRA_EQUIPO_LOCAL);
        tipoAlistamientoEnum = (TipoAlistamientoEnum) this.getIntent().getSerializableExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO);
    }

    @Override
    public boolean usarBarraNavegacion() {
        return false;
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) this.getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    //endregion
}
