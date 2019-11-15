package com.infotrack.talleres.datos.repositorios.origendatos.diagnosticorechazo;

import com.infotrack.talleres.datos.entidades.CasoDto;

import java.util.List;

import io.reactivex.Observable;

public interface DiagnosticoRechazoOrigenDatos {
    Observable<List<CasoDto>> consultarListadoCasos(String rutaDinamica);
}
