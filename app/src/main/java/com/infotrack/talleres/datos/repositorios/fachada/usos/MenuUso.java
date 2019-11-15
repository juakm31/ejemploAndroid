package com.infotrack.talleres.datos.repositorios.fachada.usos;

import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.MenuDto;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.MenuRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.menu.MenuOrigenDatos;
import com.infotrack.talleres.dominio.modelos.MenuMdl;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MenuUso implements MenuRepositorio {

    private final MapeadorGenerico<Respuesta<MenuDto>, Respuesta<MenuMdl>> mapBandejaEntrada;

    @Inject
    public MenuUso(MapeadorGenerico<Respuesta<MenuDto>, Respuesta<MenuMdl>> mapBandejaEntrada) {
        this.mapBandejaEntrada = mapBandejaEntrada;
    }

    private MenuOrigenDatos generarFabricaRetrofit() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Menu);
        return ((MenuOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.RETROFIT.toString()));
    }

    @Override
    public Observable<Respuesta<MenuMdl>> ConsultarMenu(String idPersona, int idTaller) {
        return generarFabricaRetrofit().consultarMenu(idPersona, idTaller).
                map(menuDtoRespuesta -> mapBandejaEntrada.mapear(menuDtoRespuesta, new Respuesta<MenuMdl>()));
    }
}
