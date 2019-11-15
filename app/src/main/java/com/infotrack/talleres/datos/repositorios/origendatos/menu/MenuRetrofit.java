package com.infotrack.talleres.datos.repositorios.origendatos.menu;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.base.retrofit.RetrofitCliente;
import com.infotrack.talleres.datos.entidades.MenuDto;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MenuRetrofit implements MenuOrigenDatos {

    private MenuOrigenDatos obtenerCliente() {
        return RetrofitCliente.obtener(ConstantesCompartidas.RETROFIT_BASE_URL).create(MenuOrigenDatos.class);
    }

    @Override
    public Observable<Respuesta<MenuDto>> consultarMenu(String idPersona, int idTaller) {
        return Observable.create(emitter -> obtenerCliente().consultarMenu("1001199823", idTaller).subscribe(new Observer<Respuesta<MenuDto>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Respuesta<MenuDto> bandejaEntradaDtoRespuesta) {
                emitter.onNext(bandejaEntradaDtoRespuesta);
            }

            @Override
            public void onError(Throwable e) {
                emitter.onError(e);
            }

            @Override
            public void onComplete() {
                emitter.onComplete();
            }
        }));
    }

    /*@Override
    public Observable<Respuesta<MenuDto>> consultarMenu(int idTaller) {
        return Observable.create(emitter -> obtenerCliente().consultarMenu(idTaller).subscribe(new Observer<Respuesta<MenuDto>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Respuesta<MenuDto> bandejaEntradaDtoRespuesta) {
                emitter.onNext(bandejaEntradaDtoRespuesta);
            }

            @Override
            public void onError(Throwable e) {
                emitter.onError(e);
            }

            @Override
            public void onComplete() {
                emitter.onComplete();
            }
        }));
    }*/
}
