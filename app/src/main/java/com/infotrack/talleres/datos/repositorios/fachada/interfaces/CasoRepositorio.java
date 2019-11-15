package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import com.infotrack.talleres.dominio.modelos.CasoMdl;

import io.reactivex.Observable;

public interface CasoRepositorio {
    Observable<Void> persistirCaso(String rutaDinamica, CasoMdl caso);
}
