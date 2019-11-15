package com.infotrack.talleres.datos.repositorios.origendatos.transportador;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.base.retrofit.RetrofitCliente;
import com.infotrack.talleres.datos.entidades.TransportadorDto;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TransportadorRetrofit implements TransportadorOrigenDatos {

    private TransportadorOrigenDatos obtenerCliente() {
        return RetrofitCliente.obtener(ConstantesCompartidas.RETROFIT_BASE_URL).create(TransportadorOrigenDatos.class);
    }

    @Override
    public Observable<Respuesta<TransportadorDto>> consultarVehiculoPorPlacaRecepcion(String placa) {
        return Observable.create(emitter ->
                obtenerCliente().consultarVehiculoPorPlacaRecepcion(placa).subscribe(new Observer<Respuesta<TransportadorDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<TransportadorDto> transportadorDtoRespuesta) {
                        emitter.onNext(transportadorDtoRespuesta);
                    }

                    @Override
                    public void onError(Throwable e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    @Override
    public Observable<Respuesta<TransportadorDto>> consultarVehiculoPorPlacaDespacho(String placa) {
        return Observable.create(e ->
                obtenerCliente().consultarVehiculoPorPlacaDespacho(placa).subscribe(new Observer<Respuesta<TransportadorDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<TransportadorDto> transportadorDtoRespuesta) {
                        e.onNext(transportadorDtoRespuesta);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
