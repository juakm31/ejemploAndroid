package com.infotrack.talleres.presentacion.componentes.alistamiento.listachequeo;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;

public interface ListaChequeoContrato extends PresentadorBase.VistaBase {

    void respuestaListaChequeo(String json);
    void mostrarMensajeError(String mensaje);
    void finalizarActividad();
}
