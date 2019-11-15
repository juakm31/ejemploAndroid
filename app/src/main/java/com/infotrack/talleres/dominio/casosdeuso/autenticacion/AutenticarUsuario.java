package com.infotrack.talleres.dominio.casosdeuso.autenticacion;

import com.infotrack.artefactos.utilitarios.base.CasoDeUso;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AutenticacionRepositorio;
import com.infotrack.talleres.dominio.modelos.UsuarioAutenticadoMdl;
import com.infotrack.talleres.dominio.modelos.UsuarioLoginMdl;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

public class AutenticarUsuario extends CasoDeUso<Respuesta<UsuarioAutenticadoMdl>> {

    private final AutenticacionRepositorio repositorio;
    private UsuarioLoginMdl usuarioLogin;

    public void setUsuarioLogin(UsuarioLoginMdl usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    @Inject
    public AutenticarUsuario(@Named("hilo_ejecutor") Scheduler hiloEjecutor,
                             @Named("hilo_interfaz_usuario") Scheduler hiloUI,
                             @NonNull AutenticacionRepositorio repositorio) {
        super(hiloEjecutor, hiloUI);
        this.repositorio = repositorio;
    }

    @Override
    public Observable<Respuesta<UsuarioAutenticadoMdl>> crearObservableCasoDeUso() {
        return repositorio.autenticarUsuario(usuarioLogin);
    }
}
