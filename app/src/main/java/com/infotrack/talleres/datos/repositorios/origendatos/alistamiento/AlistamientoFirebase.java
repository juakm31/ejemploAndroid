package com.infotrack.talleres.datos.repositorios.origendatos.alistamiento;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.firebase.RxFirebaseHelper;
import com.infotrack.talleres.datos.entidades.AlistamientoDto;
import com.infotrack.talleres.datos.entidades.AlistamientoLocalDto;
import com.infotrack.talleres.transversal.firebase.ConstantesFirebase;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public class AlistamientoFirebase implements AlistamientoOrigenDatos {

    private RxFirebaseHelper firebaseHelper = new RxFirebaseHelper(ConstantesFirebase.NODO_RAIZ);
    private DatabaseReference referencia = firebaseHelper.obtenerReferencia();

    @Override
    public Observable<List<AlistamientoLocalDto>> consultarListadoRecepcion(String idTaller) {
        Query query = referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.AlistamientoFirebase.ConsultarAlistamientoRecepcion, idTaller));
        return firebaseHelper.consultaListaTiempoReal(query, AlistamientoLocalDto.class);
    }

    @Override
    public Observable<List<AlistamientoLocalDto>> consultarListadoDespacho(String idTaller) {
        Query query = referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.AlistamientoFirebase.ConsultarAlistamientoDespacho, idTaller));
        return firebaseHelper.consultaListaTiempoReal(query, AlistamientoLocalDto.class);
    }

    @Override
    public Observable<Respuesta<AlistamientoDto>> crearRecepcion(AlistamientoDto alistamientoDto) {
        return null;
    }

    @Override
    public Observable<Respuesta<AlistamientoDto>> crearDespacho(AlistamientoDto alistamientoDto) {
        return null;
    }

    @Override
    public Observable<Void> persistirRecepcionLocal(AlistamientoLocalDto alistamientoLocalDto) {
        return Observable.create(emitter -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.AlistamientoFirebase.PersistirAlistamientoRecepcion, alistamientoLocalDto.getIdTaller(), alistamientoLocalDto.getIdAlistamiento()))
                    .setValue(alistamientoLocalDto);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Void> persistirDespachoLocal(AlistamientoLocalDto alistamientoLocalDto) {
        return Observable.create(emitter -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.AlistamientoFirebase.PersistirAlistamientoDespacho, alistamientoLocalDto.getIdTaller(), alistamientoLocalDto.getIdAlistamiento()))
                    .setValue(alistamientoLocalDto);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Respuesta<Boolean>> eliminarRecepcion(String idRecepcion) {
        return null;
    }

    @Override
    public Observable<Respuesta<Boolean>> eliminarDespacho(String idRecepcion) {
        return null;
    }

    @Override
    public Observable<Void> eliminarRecepcionlocal(String idRecepcion, String idTaller) {
        return Observable.create(e -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.AlistamientoFirebase.EliminarAlistamientoRecepcion, idTaller, idRecepcion))
                    .removeValue();
            e.onComplete();
        });
    }

    @Override
    public Observable<Void> eliminarDespachoLocal(String idDespacho, String idTaller) {
        return Observable.create(e -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.AlistamientoFirebase.EliminarAlistamientoDespacho, idTaller, idDespacho))
                    .removeValue();
            e.onComplete();
        });
    }

    @Override
    public Observable<Void> actualizarPropiedadAlistamiento(HashMap<String, Object> mapPropiedad) {
        return Observable.create(emitter -> {
            referencia.updateChildren(mapPropiedad);
            emitter.onComplete();
        });
    }
}
