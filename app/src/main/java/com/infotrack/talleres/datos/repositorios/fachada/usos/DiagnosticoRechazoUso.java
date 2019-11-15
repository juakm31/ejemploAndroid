package com.infotrack.talleres.datos.repositorios.fachada.usos;

import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.talleres.datos.entidades.CasoDto;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.DiagnosticoRechazoRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.diagnosticorechazo.DiagnosticoRechazoOrigenDatos;
import com.infotrack.talleres.dominio.modelos.CasoMdl;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DiagnosticoRechazoUso implements DiagnosticoRechazoRepositorio {

    private final MapeadorGenerico<CasoDto, CasoMdl> mapCaso;

    @Inject
    public DiagnosticoRechazoUso(MapeadorGenerico<CasoDto, CasoMdl> mapCaso) {
        this.mapCaso = mapCaso;
    }

    private DiagnosticoRechazoOrigenDatos generarFabricaFirebase() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.DiagnosticoRechazo);
        return ((DiagnosticoRechazoOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.FIREBASE.toString()));
    }

    @Override
    public Observable<List<CasoMdl>> consultarListadoCasos(String rutaDinamica) {
        return generarFabricaFirebase()
                .consultarListadoCasos(rutaDinamica)
                .map(casoDtos -> mapCaso.mapear(casoDtos, new CasoMdl()));
    }
}
