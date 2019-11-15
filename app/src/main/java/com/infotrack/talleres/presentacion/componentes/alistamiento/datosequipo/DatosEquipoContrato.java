package com.infotrack.talleres.presentacion.componentes.alistamiento.datosequipo;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;

public interface DatosEquipoContrato extends PresentadorBase.VistaBase{

    void finalizarActividad();

    void mostrarMensaje(String mensaje);
}
