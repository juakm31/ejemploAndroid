package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoalistamientos;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoLocalVM;

import java.util.List;

public interface ListadoAlistamientoContrato extends PresentadorBase.VistaBase {
    void actualizarListadoAlistamiento(List<AlistamientoLocalVM> listaAlistamiento);

    void monstrarMensajesError(String mensaje);
}
