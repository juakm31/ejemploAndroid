package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoequipos;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;

import java.util.List;

public interface ListadoEquiposContrato extends PresentadorBase.VistaBase {

    void respuestaConsultaEquipos(List<EquipoLocalVM> listadoEquipos);
}
