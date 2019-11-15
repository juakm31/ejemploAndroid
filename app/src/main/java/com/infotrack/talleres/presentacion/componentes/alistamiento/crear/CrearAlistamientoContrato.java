package com.infotrack.talleres.presentacion.componentes.alistamiento.crear;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.presentacion.vistamodelos.TransportadorVM;

public interface CrearAlistamientoContrato extends PresentadorBase.VistaBase {

    void llenarFormularioTransportador(TransportadorVM transportadorVMRespuesta);

    void monstrarMensajesError(String mensaje);

    void finalizarActividad();
}
