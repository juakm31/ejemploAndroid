package com.infotrack.talleres.presentacion.componentes.alistamiento.firma;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.infotrack.artefactofirma.ContenedorFirma.ContenedorFirma;
import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.artefactos.utilitarios.comunes.DialogosHelper;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoLocalVM;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class FirmaActividad extends ActividadBase implements FirmaContrato {

    //region Atributos
    @Inject
    FirmaPresentador presentador;

    @BindView(R.id.contenedor_firma_tecnico)
    ContenedorFirma contenedorFirma;
    @BindString(R.string.mensaje_guardar_firma)
    String mensajeGuardar;
    @BindString(R.string.mensaje_solicitar_firma)
    String mensajeSolicitudFirmaTecnico;

    private AlistamientoLocalVM alistamientoSeleccionado;
    private TipoAlistamientoEnum tipoAlistamiento;
    //endregion

    //region Instancia
    public static Intent obtenerIntencion(Context contexto, AlistamientoLocalVM alistamientoSeleccionado, TipoAlistamientoEnum tipoAlistamientoEnum) {
        Intent intencion = new Intent(contexto, FirmaActividad.class);
        intencion.putExtra(AlistamientoConstantes.EXTRA_ALISTAMIENTO_LOCAL, alistamientoSeleccionado);
        intencion.putExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamientoEnum);
        return intencion;
    }
    //endregion


    //region Sobrecarga
    @Override
    public int asignarLayout() {
        return R.layout.fragmento_firma;
    }

    @Override
    public void iniciarActividad(Bundle bundle) {
        iniciarPresentador();
    }

    @Override
    public void iniciarPresentador() {
        presentador.establecerVista(this);
    }

    @Override
    public void extras() {
        alistamientoSeleccionado = (AlistamientoLocalVM) getIntent().getSerializableExtra(AlistamientoConstantes.EXTRA_ALISTAMIENTO_LOCAL);
        tipoAlistamiento = (TipoAlistamientoEnum) getIntent().getSerializableExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO);
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_navegacion_guardar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_guardar) {
            new DialogosHelper().mostrarDialogoOpciones(this,
                    mensajeGuardar,
                    dialogoGuardar);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean usarBarraNavegacion() {
        return false;
    }
    //endregion

    //region Propios
    void finalizarActividad() {
        if (contenedorFirma.isEmpty()) {
            new DialogosHelper().AlertaSimple(this, mensajeSolicitudFirmaTecnico);
            return;
        }

        contenedorFirma.setDrawingCacheEnabled(true);
        presentador.eliminarAlistamientoLocal(Integer.toString(alistamientoSeleccionado.getIdTaller()), alistamientoSeleccionado.getIdAlistamiento(), tipoAlistamiento);
        this.setResult(RESULT_OK, new Intent());
        this.finish();
    }
    //endregion

    //region Contrato
    @Override
    public void tareaEnProceso() {

    }

    @Override
    public void tareaTerminada() {

    }
    //endregion

    //region Eventos
    @OnClick(R.id.btn_limpiar_firmas)
    public void clickLimpiarFirma() {
        contenedorFirma.limpiar();
    }

    DialogInterface.OnClickListener dialogoGuardar = (dialog, which) -> {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                finalizarActividad();
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.dismiss();
        }
    };
    //endregion

}
