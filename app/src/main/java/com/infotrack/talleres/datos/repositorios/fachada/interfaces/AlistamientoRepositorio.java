package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.dominio.modelos.AlistamientoLocalMdl;
import com.infotrack.talleres.dominio.modelos.AlistamientoMdl;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public interface AlistamientoRepositorio {
    Observable<List<AlistamientoLocalMdl>> consultarListadoRecepcion(String idTaller);

    Observable<List<AlistamientoLocalMdl>> consultarListadoDespacho(String idTaller);

    Observable<Respuesta<AlistamientoMdl>> crearRecepcion(AlistamientoMdl alistamientoMdl);

    Observable<Respuesta<AlistamientoMdl>> crearDespacho(AlistamientoMdl alistamientoMdl);

    Observable<Void> persistirRecepcionLocal(AlistamientoLocalMdl alistamientoLocalMdl);

    Observable<Void> persistirDespachoLocal(AlistamientoLocalMdl alistamientoLocalMdl);

    Observable<Respuesta<Boolean>> eliminarRecepcion(String idRecepcion);

    Observable<Respuesta<Boolean>> eliminarDespacho(String idDespacho);

    Observable<Void> eliminarRecepcionlocal(String idRecepcion,String idTaller);

    Observable<Void> eliminarDespachoLocal(String idRecepcion,String idTaller);

    Observable<Void> actualizarPropiedadAlistamiento(HashMap<String, Object> mapPropiedad);
}

