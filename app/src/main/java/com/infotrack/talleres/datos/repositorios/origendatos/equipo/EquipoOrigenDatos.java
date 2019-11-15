package com.infotrack.talleres.datos.repositorios.origendatos.equipo;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.EquipoDto;
import com.infotrack.talleres.datos.entidades.EquipoLocalDto;
import com.infotrack.talleres.datos.entidades.EquipoResDto;
import com.infotrack.talleres.transversal.constantes.negocio.EquipoConstantes;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EquipoOrigenDatos {

    Observable<List<EquipoLocalDto>> consultarListadoEquiposRecepcion(String idTaller, String idAlistamiento);

    Observable<List<EquipoLocalDto>> consultarListadoEquiposDespacho(String idTaller, String idAlistamiento);

    @GET(EquipoConstantes.URL_CONSULTAR_EQUIPO_PLACA)
    Observable<Respuesta<EquipoDto>> consultarEquipoPorPlaca(@Path("placa") String placa, @Path("idPersona") String idPersona, @Path("procesoEquipo") int procesoEquipo);

    @POST(EquipoConstantes.URL_AGREGAR_EQUIPO_RECEPCION)
    Observable<Respuesta<Boolean>> agregarEquipoRecepcion(@Body EquipoResDto equipoResDto);

    @POST(EquipoConstantes.URL_AGREGAR_EQUIPO_DESPACHO)
    Observable<Respuesta<Boolean>> agregarEquipoDespacho(@Body EquipoResDto equipoResDto);

    Observable<Void> persistirEquipoRecepcion(EquipoLocalDto equipoLocalDto, int idTaller);

    Observable<Void> persistirEquipoDespacho(EquipoLocalDto equipoLocalDto, int idTaller);

    Observable<Void> eliminarEquipoRecepcion(int idTaller, String idAlistamiento, String placa);

    Observable<Void> eliminarEquipoDespacho(int idTaller, String idAlistamiento, String placa);

    Observable<Void> actualizarEtapaEquipo(HashMap<String, Object> mapPropiedad);
}
