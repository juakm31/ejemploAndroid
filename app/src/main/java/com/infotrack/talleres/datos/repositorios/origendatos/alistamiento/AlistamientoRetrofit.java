package com.infotrack.talleres.datos.repositorios.origendatos.alistamiento;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.base.retrofit.RetrofitCliente;
import com.infotrack.talleres.datos.entidades.AlistamientoDto;
import com.infotrack.talleres.datos.entidades.AlistamientoLocalDto;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AlistamientoRetrofit implements AlistamientoOrigenDatos {

    private AlistamientoOrigenDatos obtenerCliente() {
        return RetrofitCliente.obtener(ConstantesCompartidas.RETROFIT_BASE_URL).create(AlistamientoOrigenDatos.class);
    }

    @Override
    public Observable<List<AlistamientoLocalDto>> consultarListadoRecepcion(String idTaller) {
        return null;
    }

    @Override
    public Observable<List<AlistamientoLocalDto>> consultarListadoDespacho(String idTaller) {
        return null;
    }

    @Override
    public Observable<Respuesta<AlistamientoDto>> crearRecepcion(AlistamientoDto alistamientoDto) {
        return Observable.create(emitter ->
                obtenerCliente().crearRecepcion(alistamientoDto).subscribe(new Observer<Respuesta<AlistamientoDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Respuesta<AlistamientoDto> alistamientoDtoRespuesta) {
                        emitter.onNext(alistamientoDtoRespuesta);
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

    @Override
    public Observable<Respuesta<AlistamientoDto>> crearDespacho(AlistamientoDto alistamientoDto) {
        return Observable.create(emitter ->
                obtenerCliente().crearDespacho(alistamientoDto).subscribe(new Observer<Respuesta<AlistamientoDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<AlistamientoDto> alistamientoDtoRespuesta) {
                        emitter.onNext(alistamientoDtoRespuesta);
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

    @Override
    public Observable<Void> persistirRecepcionLocal(AlistamientoLocalDto alistamientoLocalDto) {
        return null;
    }

    @Override
    public Observable<Void> persistirDespachoLocal(AlistamientoLocalDto alistamientoLocalDto) {
        return null;
    }

    @Override
    public Observable<Respuesta<Boolean>> eliminarRecepcion(String idRecepcion) {
        return Observable.create(emitter ->
                obtenerCliente().eliminarRecepcion(idRecepcion).subscribe(new Observer<Respuesta<Boolean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<Boolean> booleanRespuesta) {
                        emitter.onNext(booleanRespuesta);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public Observable<Respuesta<Boolean>> eliminarDespacho(String idRecepcion) {
        return Observable.create(emitter ->
                obtenerCliente().eliminarDespacho(idRecepcion).subscribe(new Observer<Respuesta<Boolean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<Boolean> booleanRespuesta) {
                        emitter.onNext(booleanRespuesta);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public Observable<Void> eliminarRecepcionlocal(String idRecepcion, String idTaller) {
        return null;
    }

    @Override
    public Observable<Void> eliminarDespachoLocal(String idDespacho, String idTaller) {
        return null;
    }

    @Override
    public Observable<Void> actualizarPropiedadAlistamiento(HashMap<String, Object> mapPropiedad) {
        return null;
    }
}
