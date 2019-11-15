package com.infotrack.talleres.datos.repositorios.origendatos.caso;

import com.infotrack.talleres.datos.entidades.CasoDto;

import io.reactivex.Observable;

public interface CasoOrigenDatos {
    Observable<Void> persistirCaso(String rutaDinamica, CasoDto caso);
}
