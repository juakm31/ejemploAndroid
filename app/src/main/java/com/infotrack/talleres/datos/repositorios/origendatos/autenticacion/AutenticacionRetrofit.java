package com.infotrack.talleres.datos.repositorios.origendatos.autenticacion;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.base.retrofit.RetrofitCliente;
import com.infotrack.talleres.datos.entidades.UsuarioAutenticadoDto;
import com.infotrack.talleres.datos.entidades.UsuarioLoginDto;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AutenticacionRetrofit implements AutenticacionOrigenDatos {

    @Override
    public Observable<Respuesta<UsuarioAutenticadoDto>> autenticarUsuario(final UsuarioLoginDto usuarioLoginDto) {
        return Observable.create(e -> obtenerCliente().autenticarUsuario(usuarioLoginDto).
                subscribe(new Observer<Respuesta<UsuarioAutenticadoDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<UsuarioAutenticadoDto> usuarioAutenticadoDtoRespuesta) {
                        e.onNext(usuarioAutenticadoDtoRespuesta);
                    }

                    @Override
                    public void onError(Throwable ex) {
                        e.onError(ex);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public AutenticacionOrigenDatos obtenerCliente() {
        return RetrofitCliente.obtener(ConstantesCompartidas.RETROFIT_BASE_URL).create(AutenticacionOrigenDatos.class);
    }
}
