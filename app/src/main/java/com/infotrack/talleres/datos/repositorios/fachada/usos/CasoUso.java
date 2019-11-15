package com.infotrack.talleres.datos.repositorios.fachada.usos;

import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.talleres.datos.entidades.CasoDto;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.CasoRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.caso.CasoOrigenDatos;
import com.infotrack.talleres.dominio.modelos.CasoMdl;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CasoUso implements CasoRepositorio {

    private final MapeadorGenerico<CasoMdl, CasoDto> mapCaso;

    @Inject
    CasoUso(MapeadorGenerico<CasoMdl, CasoDto> mapCaso) {
        this.mapCaso = mapCaso;
    }

    private CasoOrigenDatos generarFabricaRetrofit() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Caso);
        return ((CasoOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.RETROFIT.toString()));
    }

    private CasoOrigenDatos generarFabricaFirebase() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Caso);
        return ((CasoOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.FIREBASE.toString()));
    }

    @Override
    public Observable<Void> persistirCaso(String rutaDinamica, CasoMdl caso) {
        return generarFabricaFirebase().persistirCaso(rutaDinamica, mapCaso.mapear(caso, new CasoDto()));
    }
}
