package com.infotrack.talleres.presentacion.componentes.comunes.consultaequipo;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoVM;

interface ConsultaEquipoContrato extends PresentadorBase.VistaBase {

    void respuestaConsultaEquipo(EquipoVM equipo);

    void mostrarMensaje(String mensaje);
}
