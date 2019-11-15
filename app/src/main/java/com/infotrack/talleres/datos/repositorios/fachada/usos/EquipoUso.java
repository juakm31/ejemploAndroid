package com.infotrack.talleres.datos.repositorios.fachada.usos;

import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.EquipoDto;
import com.infotrack.talleres.datos.entidades.EquipoLocalDto;
import com.infotrack.talleres.datos.entidades.EquipoResDto;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.EquipoRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.equipo.EquipoOrigenDatos;
import com.infotrack.talleres.dominio.modelos.EquipoLocalMdl;
import com.infotrack.talleres.dominio.modelos.EquipoMdl;
import com.infotrack.talleres.dominio.modelos.EquipoResMdl;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;
import com.infotrack.talleres.transversal.enumeradores.ProcesoEquipoEnum;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class EquipoUso implements EquipoRepositorio {

    private final MapeadorGenerico<Respuesta<EquipoDto>, Respuesta<EquipoMdl>> mapEquipo;
    private final MapeadorGenerico<EquipoLocalDto, EquipoLocalMdl> mapEquipoLocal;
    private final MapeadorGenerico<EquipoResDto, EquipoResMdl> mapEquipoRes;

    @Inject
    EquipoUso(MapeadorGenerico<Respuesta<EquipoDto>, Respuesta<EquipoMdl>> mapEquipo,
              MapeadorGenerico<EquipoLocalDto, EquipoLocalMdl> mapEquipoLocal,
              MapeadorGenerico<EquipoResDto, EquipoResMdl> mapEquipoRes) {
        this.mapEquipo = mapEquipo;
        this.mapEquipoLocal = mapEquipoLocal;
        this.mapEquipoRes = mapEquipoRes;
    }

    private EquipoOrigenDatos generarFabricaRetrofit() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Equipo);
        return ((EquipoOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.RETROFIT.toString()));
    }

    private EquipoOrigenDatos generarFabricaFirebase() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Equipo);
        return ((EquipoOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.FIREBASE.toString()));
    }

    @Override
    public Observable<Respuesta<Boolean>> agregarEquipoRecepcion(EquipoResMdl equipoResMdl) {
        return generarFabricaRetrofit()
                .agregarEquipoRecepcion(mapEquipoRes.revertir(equipoResMdl, new EquipoResDto()));
    }

    @Override
    public Observable<Respuesta<Boolean>> agregarEquipoDespacho(EquipoResMdl equipoResMdl) {
        return generarFabricaRetrofit()
                .agregarEquipoDespacho(mapEquipoRes.revertir(equipoResMdl, new EquipoResDto()));
    }

    @Override
    public Observable<List<EquipoLocalMdl>> consultarListadoEquiposRecepcion(String idTaller, String idAlistamiento) {
        return generarFabricaFirebase()
                .consultarListadoEquiposRecepcion(idTaller, idAlistamiento)
                .map(equipoLocalDtos -> mapEquipoLocal.mapear(equipoLocalDtos, new EquipoLocalMdl()));
    }

    @Override
    public Observable<List<EquipoLocalMdl>> consultarListadoEquiposDespacho(String idTaller, String idAlistamiento) {
        return generarFabricaFirebase()
                .consultarListadoEquiposDespacho(idTaller, idAlistamiento)
                .map(equipoLocalDtos -> mapEquipoLocal.mapear(equipoLocalDtos, new EquipoLocalMdl()));
    }

    @Override
    public Observable<Respuesta<EquipoMdl>> consultarEquipoPorPlaca(String placa, String idPersona, ProcesoEquipoEnum procesoEquipo) {
        return generarFabricaRetrofit()
                .consultarEquipoPorPlaca(placa, idPersona, procesoEquipo.getValue())
                .map(equipoDtoRespuesta -> mapEquipo.mapear(equipoDtoRespuesta, new Respuesta<EquipoMdl>()));
    }

    @Override
    public Observable<Void> persistirEquipoRecepcion(EquipoLocalMdl equipoLocalMdl, int idTaller) {
        return generarFabricaFirebase()
                .persistirEquipoRecepcion(mapEquipoLocal.revertir(equipoLocalMdl, new EquipoLocalDto()), idTaller);
    }

    @Override
    public Observable<Void> persistirEquipoDespacho(EquipoLocalMdl equipoLocalMdl, int idTaller) {
        return generarFabricaFirebase()
                .persistirEquipoDespacho(mapEquipoLocal.revertir(equipoLocalMdl, new EquipoLocalDto()), idTaller);
    }

    @Override
    public Observable<Void> eliminarEquipoRececepcion(int idTaller, String idAlistamiento, String placa) {
        return generarFabricaFirebase()
                .eliminarEquipoRecepcion(idTaller, idAlistamiento, placa);
    }

    @Override
    public Observable<Void> eliminarEquipoDespacho(int idTaller, String idAlistamiento, String placa) {
        return generarFabricaFirebase().eliminarEquipoDespacho(idTaller, idAlistamiento, placa);
    }

    @Override
    public Observable<Void> actualizarEtapaEquipo(HashMap<String, Object> mapPropiedad) {
        return generarFabricaFirebase().actualizarEtapaEquipo(mapPropiedad);
    }
}
