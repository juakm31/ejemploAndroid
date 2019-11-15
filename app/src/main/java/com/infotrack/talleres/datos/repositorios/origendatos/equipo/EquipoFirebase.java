package com.infotrack.talleres.datos.repositorios.origendatos.equipo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.firebase.RxFirebaseHelper;
import com.infotrack.talleres.datos.entidades.EquipoDto;
import com.infotrack.talleres.datos.entidades.EquipoLocalDto;
import com.infotrack.talleres.datos.entidades.EquipoResDto;
import com.infotrack.talleres.transversal.firebase.ConstantesFirebase;
import com.infotrack.talleres.transversal.firebase.rutas.AlistamientoFirebase;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public class EquipoFirebase implements EquipoOrigenDatos {

    private RxFirebaseHelper firebaseHelper = new RxFirebaseHelper(ConstantesFirebase.NODO_RAIZ);
    private DatabaseReference referencia = firebaseHelper.obtenerReferencia();


    @Override
    public Observable<List<EquipoLocalDto>> consultarListadoEquiposRecepcion(String idTaller, String idAlistamiento) {
        Query query = referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.EquipoFirebase.ConsultarEquipoRecepcion, idTaller, idAlistamiento));
        return firebaseHelper.consultaListaTiempoReal(query, EquipoLocalDto.class);
    }

    @Override
    public Observable<List<EquipoLocalDto>> consultarListadoEquiposDespacho(String idTaller, String idAlistamiento) {
        Query query = referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.EquipoFirebase.ConsultarEquiposDespacho, idTaller, idAlistamiento));
        return firebaseHelper.consultaListaTiempoReal(query, EquipoLocalDto.class);
    }

    @Override
    public Observable<Respuesta<EquipoDto>> consultarEquipoPorPlaca(String placa, String idPersona, int procesoEquipo) {
        return null;
    }

    @Override
    public Observable<Respuesta<Boolean>> agregarEquipoRecepcion(EquipoResDto equipoResDto) {
        return null;
    }

    @Override
    public Observable<Respuesta<Boolean>> agregarEquipoDespacho(EquipoResDto equipoResDto) {
        return null;
    }

    @Override
    public Observable<Void> persistirEquipoRecepcion(EquipoLocalDto equipoLocalDto, int idTaller) {
        return Observable.create(emitter -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.EquipoFirebase.PersistirEquipoRecepcion, idTaller, equipoLocalDto.getIdAlistamiento(), equipoLocalDto.getPlaca()))
                    .setValue(equipoLocalDto);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Void> persistirEquipoDespacho(EquipoLocalDto equipoLocalDto, int idTaller) {
        return Observable.create(emitter -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.EquipoFirebase.PersistirEquiposDespacho, idTaller, equipoLocalDto.getIdAlistamiento(), equipoLocalDto.getPlaca()))
                    .setValue(equipoLocalDto);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Void> eliminarEquipoRecepcion(int idTaller, String idAlistamiento, String placa) {
        return Observable.create(emitter -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.EquipoFirebase.EliminarEquipoRecepcion, idTaller, idAlistamiento, placa))
                    .removeValue();
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Void> eliminarEquipoDespacho(int idTaller, String idAlistamiento, String placa) {
        return Observable.create(emitter -> {
            referencia.child(String.format(com.infotrack.talleres.transversal.firebase.rutas.EquipoFirebase.EliminarEquipoDespacho, idTaller, idAlistamiento, placa))
                    .removeValue();
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Void> actualizarEtapaEquipo(HashMap<String, Object> mapPropiedad) {
        return Observable.create(emitter -> {
            referencia.child(AlistamientoFirebase.alistamiento)
                    .updateChildren(mapPropiedad);
            emitter.onComplete();
        });
    }
}
