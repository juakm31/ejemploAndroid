package com.infotrack.talleres.datos.repositorios.origendatos.alistamiento;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.AlistamientoDto;
import com.infotrack.talleres.datos.entidades.AlistamientoLocalDto;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AlistamientoOrigenDatos {

    Observable<List<AlistamientoLocalDto>> consultarListadoRecepcion(String idTaller);

    Observable<List<AlistamientoLocalDto>> consultarListadoDespacho(String idTaller);

    @POST(AlistamientoConstantes.URL_CREAR_RECEPCION)
    Observable<Respuesta<AlistamientoDto>> crearRecepcion(@Body AlistamientoDto alistamientoDto);

    @POST(AlistamientoConstantes.URL_CREAR_DESPACHO)
    Observable<Respuesta<AlistamientoDto>> crearDespacho(@Body AlistamientoDto alistamientoDto);

    Observable<Void> persistirRecepcionLocal(AlistamientoLocalDto alistamientoLocalDto);

    Observable<Void> persistirDespachoLocal(AlistamientoLocalDto alistamientoLocalDto);

    @DELETE(AlistamientoConstantes.URL_ELIMINAR_RECEPCION)
    Observable<Respuesta<Boolean>> eliminarRecepcion(@Path("idRecepcion") String idRecepcion);

    @DELETE(AlistamientoConstantes.URL_ELIMINAR_DESPACHO)
    Observable<Respuesta<Boolean>> eliminarDespacho(@Path("idDespacho") String idRecepcion);

    Observable<Void> eliminarRecepcionlocal(String idRecepcion, String idTaller);

    Observable<Void> eliminarDespachoLocal(String idDespacho, String idTaller);

    Observable<Void> actualizarPropiedadAlistamiento(HashMap<String, Object> mapPropiedad);
}
