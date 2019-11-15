package com.infotrack.talleres.presentacion.componentes.autenticacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;

import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.artefactos.utilitarios.base.PreferenciasManager;
import com.infotrack.artefactos.utilitarios.comunes.DialogosHelper;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.base.INavegador;
import com.infotrack.talleres.transversal.constantes.CodigosIntencion;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class IniciarSesionActividad extends ActividadBase implements IniciarSesionContrato {

    //region Atributos
    @Inject
    IniciarSesionPresentador presentador;
    @Inject
    INavegador navegador;

    @BindView(R.id.edt_id_usuario)
    EditText editTextUsuario;
    @BindView(R.id.edt_contrasenia)
    EditText editTextContrasenia;
    @BindView(android.R.id.content)
    View parentLayout;

    private ProgressDialog progressDialog;
    //endregion

    //region Contrato
    @Override
    public int asignarLayout() {
        return R.layout.iniciar_sesion_actividad;
    }

    @Override
    public void iniciarActividad(Bundle bundle) {
        iniciarPresentador();
    }

    @Override
    public void loginFirebaseInvalido() {
        mostrarMensaje(getString(R.string.actividad_inicio_sesion_mensaje_error_token_firebase));
    }

    @Override
    public void navegarSiguienteActividad() {
        navegador.navegarMenuActividad(this);
    }

    @Override
    public void usuarioInvalido(String mensaje) {
        mostrarMensaje(mensaje);
    }

    @Override
    public void errorServicioAutenticacion(Throwable throwable) {
        if (throwable instanceof ConnectException)
            mostrarMensaje(getString(R.string.actividad_inicio_sesion_mensaje_error_conexion));
        else if (throwable instanceof SocketTimeoutException)
            mostrarMensaje(getString(R.string.actividad_inicio_sesion_mensaje_error_timeout));
        else
            mostrarMensaje(throwable.getMessage());
    }

    @Override
    public void tareaEnProceso() {
        progressDialog = new DialogosHelper().mostrarDialogoProgreso(this, getString(R.string.actividad_inicio_sesion_mensaje_tarea));
    }

    @Override
    public void tareaTerminada() {
        progressDialog.dismiss();
    }
    //endregion

    //region Sobrecargas
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }

    @Override
    public boolean usarBarraNavegacion() {
        return false;
    }

    @Override
    public boolean usarBarraHerramientas() {
        return false;
    }

    @Override
    public void iniciarPresentador() {
        presentador.establecerVista(this);
        presentador.verificarEstadoAutenticacion();
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //if (requestCode == CodigosIntencion.CR_CODIGO_PERMISOS_SERVICIO_UBICACION) {
        if (requestCode == CodigosIntencion.CR_CODIGO_PERMISOS_APLICACION) {
            boolean permisosGarantizados = true;

            for (int garantia : grantResults)
                if (garantia != PackageManager.PERMISSION_GRANTED) permisosGarantizados = false;

            if (permisosGarantizados) {
                login();
            } else {
                new DialogosHelper().AlertaSimple(this, getString(R.string.texto_error_permisos_servicio_ubicacion));
            }
        }
    }
    //endregion

    //region Eventos
    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.btn_iniciar_sesion)
    public void iniciarSesionClick() {
        if (tienePermisos()) login();
    }
    //endregion

    //region Propios
    private void login() {
        String usuario = editTextUsuario.getText().toString();
        String contrasenia = editTextContrasenia.getText().toString();
        presentador.autenticarUsuario(usuario, contrasenia);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean tienePermisos() {
        String[] permisosServicioUbicacion = getResources().getStringArray(R.array.permisos_aplicacion);
        boolean tienePermisos = validaPermisos(permisosServicioUbicacion);
        if (!tienePermisos)
            requestPermissions(permisosServicioUbicacion, CodigosIntencion.CR_CODIGO_PERMISOS_APLICACION);
        return tienePermisos;
    }

    private boolean validaPermisos(String[] permisos) {
        boolean permisosGarantizados = true;
        for (String permiso : permisos) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(permiso) != PackageManager.PERMISSION_GRANTED)
                    permisosGarantizados = false;
            }
        }
        return permisosGarantizados;
    }

    private void mostrarMensaje(String mensaje) {
        Snackbar.make(parentLayout, mensaje, Snackbar.LENGTH_LONG).show();
    }
    //endregion
}
