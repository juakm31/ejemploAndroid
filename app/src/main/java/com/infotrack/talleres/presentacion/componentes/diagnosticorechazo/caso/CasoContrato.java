package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.caso;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;

interface CasoContrato extends PresentadorBase.VistaBase {
    void finalizarActividad();
    void mostrarMensaje(String mensaje);
}
