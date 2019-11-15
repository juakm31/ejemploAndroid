package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.dominio.modelos.EquipoLocalMdl;
import com.infotrack.talleres.dominio.modelos.EquipoMdl;
import com.infotrack.talleres.dominio.modelos.EquipoResMdl;
import com.infotrack.talleres.transversal.enumeradores.ProcesoEquipoEnum;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public interface EquipoRepositorio {
    Observable<List<EquipoLocalMdl>> consultarListadoEquiposRecepcion(String idTaller, String idAlistamiento);

    Observable<List<EquipoLocalMdl>> consultarListadoEquiposDespacho(String idTaller, String idAlistamiento);

    Observable<Respuesta<EquipoMdl>> consultarEquipoPorPlaca(String placa, String idPersona, ProcesoEquipoEnum procesoEquipo);

    Observable<Respuesta<Boolean>> agregarEquipoRecepcion(EquipoResMdl equipoResMdl);

    Observable<Respuesta<Boolean>> agregarEquipoDespacho(EquipoResMdl equipoResMdl);

    Observable<Void> persistirEquipoRecepcion(EquipoLocalMdl equipoLocalMdl, int idTaller);

    Observable<Void> persistirEquipoDespacho(EquipoLocalMdl equipoLocalMdl, int idTaller);

    Observable<Void> eliminarEquipoRececepcion(int idTaller, String idAlistamiento, String placa);

    Observable<Void> eliminarEquipoDespacho(int idTaller, String idAlistamiento, String placa);

    Observable<Void> actualizarEtapaEquipo(HashMap<String, Object> mapPropiedad);
}
