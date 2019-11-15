package com.infotrack.talleres.datos.repositorios.fachada.usos;

import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.AlistamientoDto;
import com.infotrack.talleres.datos.entidades.AlistamientoLocalDto;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.alistamiento.AlistamientoOrigenDatos;
import com.infotrack.talleres.dominio.modelos.AlistamientoLocalMdl;
import com.infotrack.talleres.dominio.modelos.AlistamientoMdl;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AlistamientoUso implements AlistamientoRepositorio {

    private final MapeadorGenerico<AlistamientoLocalDto, AlistamientoLocalMdl> mapAlistamientoLocal;
    private final MapeadorGenerico<AlistamientoMdl, AlistamientoDto> mapAlistamiento;
    private final MapeadorGenerico<Respuesta<AlistamientoDto>, Respuesta<AlistamientoMdl>> mapRespuestaAlistamiento;
    private final MapeadorGenerico<AlistamientoLocalMdl, AlistamientoLocalDto> mapPersistirAlistamiento;

    @Inject
    AlistamientoUso(MapeadorGenerico<AlistamientoLocalDto, AlistamientoLocalMdl> mapAlistamientoLocal,
                    MapeadorGenerico<AlistamientoMdl, AlistamientoDto> mapAlistamiento,
                    MapeadorGenerico<Respuesta<AlistamientoDto>, Respuesta<AlistamientoMdl>> mapRespuestaAlistamiento,
                    MapeadorGenerico<AlistamientoLocalMdl, AlistamientoLocalDto> mapPersistirAlistamiento) {
        this.mapAlistamientoLocal = mapAlistamientoLocal;
        this.mapAlistamiento = mapAlistamiento;
        this.mapRespuestaAlistamiento = mapRespuestaAlistamiento;
        this.mapPersistirAlistamiento = mapPersistirAlistamiento;
    }

    private AlistamientoOrigenDatos generarFabricaRetrofit() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Alistamiento);
        return ((AlistamientoOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.RETROFIT.toString()));
    }

    private AlistamientoOrigenDatos generarFabricaFirebase() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Alistamiento);
        return ((AlistamientoOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.FIREBASE.toString()));
    }

    @Override
    public Observable<Respuesta<AlistamientoMdl>> crearRecepcion(AlistamientoMdl alistamientoMdl) {
        return generarFabricaRetrofit()
                .crearRecepcion(mapAlistamiento.mapear(alistamientoMdl, new AlistamientoDto()))
                .map(alistamientoDtoRespuesta -> mapRespuestaAlistamiento.mapear(alistamientoDtoRespuesta, new Respuesta<AlistamientoMdl>()));
    }

    @Override
    public Observable<Respuesta<AlistamientoMdl>> crearDespacho(AlistamientoMdl alistamientoMdl) {
        return generarFabricaRetrofit()
                .crearDespacho(mapAlistamiento.mapear(alistamientoMdl, new AlistamientoDto()))
                .map(alistamientoDtoRespuesta -> mapRespuestaAlistamiento.mapear(alistamientoDtoRespuesta, new Respuesta<AlistamientoMdl>()));
    }

    @Override
    public Observable<Void> persistirRecepcionLocal(AlistamientoLocalMdl alistamientoLocalMdl) {
        return generarFabricaFirebase()
                .persistirRecepcionLocal(mapPersistirAlistamiento.mapear(alistamientoLocalMdl, new AlistamientoLocalDto()));
    }

    @Override
    public Observable<Void> persistirDespachoLocal(AlistamientoLocalMdl alistamientoLocalMdl) {
        return generarFabricaFirebase()
                .persistirDespachoLocal(mapPersistirAlistamiento.mapear(alistamientoLocalMdl, new AlistamientoLocalDto()));
    }

    @Override
    public Observable<Respuesta<Boolean>> eliminarRecepcion(String idRecepcion) {
        return generarFabricaRetrofit().eliminarRecepcion(idRecepcion);
    }

    @Override
    public Observable<Respuesta<Boolean>> eliminarDespacho(String idDespacho) {
        return generarFabricaRetrofit().eliminarDespacho(idDespacho);
    }

    @Override
    public Observable<Void> eliminarRecepcionlocal(String idRecepcion, String idTaller) {
        return generarFabricaFirebase().eliminarRecepcionlocal(idRecepcion, idTaller);
    }

    @Override
    public Observable<Void> eliminarDespachoLocal(String idRecepcion, String idTaller) {
        return generarFabricaFirebase().eliminarDespachoLocal(idRecepcion, idTaller);
    }

    @Override
    public Observable<Void> actualizarPropiedadAlistamiento(HashMap<String, Object> mapPropiedad) {
        return generarFabricaFirebase().actualizarPropiedadAlistamiento(mapPropiedad);
    }

    @Override
    public Observable<List<AlistamientoLocalMdl>> consultarListadoRecepcion(String idTaller) {
        return generarFabricaFirebase()
                .consultarListadoRecepcion(idTaller)
                .map(alistamientoLocalDtos -> mapAlistamientoLocal.mapear(alistamientoLocalDtos, new AlistamientoLocalMdl()));
    }

    @Override
    public Observable<List<AlistamientoLocalMdl>> consultarListadoDespacho(String idTaller) {
        return generarFabricaFirebase()
                .consultarListadoDespacho(idTaller)
                .map(alistamientoLocalDtos -> mapAlistamientoLocal.mapear(alistamientoLocalDtos, new AlistamientoLocalMdl()));
    }
}
