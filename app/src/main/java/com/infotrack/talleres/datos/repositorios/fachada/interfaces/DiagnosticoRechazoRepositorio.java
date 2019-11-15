package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import com.infotrack.talleres.dominio.modelos.CasoMdl;

import java.util.List;

import io.reactivex.Observable;

public interface DiagnosticoRechazoRepositorio {
    Observable<List<CasoMdl>> consultarListadoCasos(String rutaDinamica);
}
