package com.infotrack.talleres.presentacion.componentes.autenticacion;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadoresComunes;
import com.infotrack.artefactos.utilitarios.base.PreferenciasManager;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.dominio.casosdeuso.autenticacion.AutenticarUsuario;
import com.infotrack.talleres.dominio.modelos.UsuarioAutenticadoMdl;
import com.infotrack.talleres.dominio.modelos.UsuarioLoginMdl;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioLoginVM;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;
import com.infotrack.talleres.transversal.firebase.FirebaseAuthentication;
import com.infotrack.talleres.transversal.firebase.FirebaseAuthenticationCallback;
import com.infotrack.talleres.transversal.firebase.FirebaseSync;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class IniciarSesionPresentador extends PresentadorBase<IniciarSesionContrato> implements FirebaseAuthenticationCallback {

    //region Atributos
    private AutenticarUsuario autenticarUsuario;
    private MapeadorGenerico<UsuarioLoginMdl, UsuarioLoginVM> mapUsuarioLogin;
    private Context contexto;
    private UsuarioAutenticadoVM usuarioAutenticado;
    //endregion

    //region Constructor
    @Inject
    public IniciarSesionPresentador(@NonNull AutenticarUsuario autenticarUsuario,
                                    @NonNull MapeadorGenerico<UsuarioLoginMdl, UsuarioLoginVM> mapeadorUsuarioLogin,
                                    @NonNull Context contexto) {
        this.autenticarUsuario = autenticarUsuario;
        this.mapUsuarioLogin = mapeadorUsuarioLogin;
        this.contexto = contexto;
    }
    //endregion

    //region Contrato
    @Override
    public void destruir() {
        autenticarUsuario.desechar();
    }

    @Override
    public void autenticacionUsuario(boolean autenticado) {
        if (autenticado)
            UsuarioSingleton.obtenerInstancia(contexto).almacenarUsuario(usuarioAutenticado);
        else obtenerVista().loginFirebaseInvalido();
    }

    @Override
    public void cambioEstadoAutenticacion(FirebaseUser usuario) {
        if (usuario == null) {
            PreferenciasManager.obtenerInstancia(contexto).eliminar(ConstantesCompartidas.LLAVE_PREFERENCIA_TOKEN_SEGURIDAD);
            PreferenciasManager.obtenerInstancia(contexto).eliminar(ConstantesCompartidas.LLAVE_PREFERENCIA_SINGLETON_USUARIO);
            FirebaseAuthentication.getInstance().unSubscribeStateListener(this);
        } else {
            if (usuarioAutenticado != null) {
                PreferenciasManager.obtenerInstancia(contexto).almacenar(ConstantesCompartidas.LLAVE_PREFERENCIA_TOKEN_SEGURIDAD, usuarioAutenticado.getToken());
                FirebaseSync.obtenerInstancia().mantenerSincronizacionNodos(usuarioAutenticado.getIdentificacion());
            }
            obtenerVista().navegarSiguienteActividad();
        }
    }
    //endregion

    //region Propios
    void autenticarUsuario(String login, String contrasenia) {
        obtenerVista().tareaEnProceso();
        UsuarioLoginVM usuarioLogin = new UsuarioLoginVM();
        usuarioLogin.setNombreUsuario(login);
        usuarioLogin.setContrasena(contrasenia);
        usuarioLogin.setTipoUsuario(0);
        autenticarUsuario.setUsuarioLogin(mapUsuarioLogin.revertir(usuarioLogin, new UsuarioLoginMdl()));
        autenticarUsuario.ejecutar(new ObservadorAutenticarUsuario());
    }

    void verificarEstadoAutenticacion() {
        FirebaseUser usuarioActual = FirebaseAuthentication.getInstance().obtenerUsuarioFirebase();
        if (usuarioActual == null) return;
        String token = PreferenciasManager.obtenerInstancia(contexto).obtener(ConstantesCompartidas.LLAVE_PREFERENCIA_TOKEN_SEGURIDAD, String.class);
        if (token != null) FirebaseAuthentication.getInstance().subscribeStateListener(this);
    }
    //endregion

    //region Observadores
    public class ObservadorAutenticarUsuario extends AccionesObservador<Respuesta<UsuarioAutenticadoMdl>> {
        @Override
        public void onNext(Respuesta<UsuarioAutenticadoMdl> value) {
            super.onNext(value);
            if (!value.isResultado())
                obtenerVista().usuarioInvalido(value.getMensajes().get(0));
            else {
                usuarioAutenticado = MapeadoresComunes.MapearEntidadesIguales(value.getEntidades().get(0), new UsuarioAutenticadoVM());
                FirebaseAuthentication.getInstance().loginTokenPersonalizado(IniciarSesionPresentador.this, usuarioAutenticado.getToken());
            }
            obtenerVista().tareaTerminada();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            obtenerVista().tareaTerminada();
            obtenerVista().errorServicioAutenticacion(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
    //endregion
}
