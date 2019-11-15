package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.presentacion.vistamodelos.CasoVM;

import java.util.List;

public interface ListadoDiagnosticoRechazoContrato extends PresentadorBase.VistaBase {
    void actualizarListadoDiagnosticoRechazo(List<CasoVM> listaCasos);
}
