package com.infotrack.talleres.datos.repositorios.origendatos.equipo;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.base.retrofit.RetrofitCliente;
import com.infotrack.talleres.datos.entidades.EquipoDto;
import com.infotrack.talleres.datos.entidades.EquipoLocalDto;
import com.infotrack.talleres.datos.entidades.EquipoResDto;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class EquipoRetrofit implements EquipoOrigenDatos {

    private EquipoOrigenDatos obtenerCliente() {
        return RetrofitCliente.obtener(ConstantesCompartidas.RETROFIT_BASE_URL).create(EquipoOrigenDatos.class);
    }

    @Override
    public Observable<Respuesta<EquipoDto>> consultarEquipoPorPlaca(String placa, String idPersona, int procesoEquipo) {
        return Observable.create(emitter ->
                obtenerCliente().consultarEquipoPorPlaca(placa, idPersona, procesoEquipo).subscribe(new Observer<Respuesta<EquipoDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<EquipoDto> equipoDtoRespuesta) {
                        emitter.onNext(equipoDtoRespuesta);
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
    public Observable<List<EquipoLocalDto>> consultarListadoEquiposRecepcion(String idTaller, String idAlistamiento) {
        return null;
    }

    @Override
    public Observable<List<EquipoLocalDto>> consultarListadoEquiposDespacho(String idTaller, String idAlistamiento) {
        return null;
    }

    @Override
    public Observable<Respuesta<Boolean>> agregarEquipoRecepcion(EquipoResDto equipoResDto) {
        return Observable.create(emitter ->
                obtenerCliente().agregarEquipoRecepcion(equipoResDto).subscribe(new Observer<Respuesta<Boolean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<Boolean> aBoolean) {
                        emitter.onNext(aBoolean);
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
    public Observable<Respuesta<Boolean>> agregarEquipoDespacho(EquipoResDto equipoResDto) {
        return Observable.create(emitter ->
                obtenerCliente().agregarEquipoDespacho(equipoResDto).subscribe(new Observer<Respuesta<Boolean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respuesta<Boolean> aBoolean) {
                        emitter.onNext(aBoolean);
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
    public Observable<Void> persistirEquipoRecepcion(EquipoLocalDto equipoLocalDto, int idTaller) {
        return null;
    }

    @Override
    public Observable<Void> persistirEquipoDespacho(EquipoLocalDto equipoLocalDto, int idTaller) {
        return null;
    }

    @Override
    public Observable<Void> eliminarEquipoRecepcion(int idTaller, String idAlistamiento, String placa) {
        return null;
    }

    @Override
    public Observable<Void> eliminarEquipoDespacho(int idTaller, String idAlistamiento, String placa) {
        return null;
    }

    @Override
    public Observable<Void> actualizarEtapaEquipo(HashMap<String, Object> mapPropiedad) {
        return null;
    }
}
