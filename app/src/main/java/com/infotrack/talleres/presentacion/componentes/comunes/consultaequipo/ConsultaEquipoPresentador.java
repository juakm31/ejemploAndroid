package com.infotrack.talleres.presentacion.componentes.comunes.consultaequipo;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadoresComunes;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.dominio.casosdeuso.equipo.ConsultarEquipoPorPlaca;
import com.infotrack.talleres.dominio.modelos.EquipoMdl;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoVM;
import com.infotrack.talleres.transversal.enumeradores.ProcesoEquipoEnum;

import javax.inject.Inject;

public class ConsultaEquipoPresentador extends PresentadorBase<ConsultaEquipoContrato> {

    //region Atributos
    private ConsultarEquipoPorPlaca consultarEquipo;
    //endregion

    //region Constructor
    @Inject
    public ConsultaEquipoPresentador(ConsultarEquipoPorPlaca consultarEquipo) {
        this.consultarEquipo = consultarEquipo;
    }
    //endregion

    //region Contrato Presentador Base
    @Override
    public void destruir() {
        consultarEquipo.desechar();
    }
    //endregion

    //region Propios
    void consultarEquipo(String placa, String idUsuario, ProcesoEquipoEnum procesoEquipo) {
        obtenerVista().tareaEnProceso();
        consultarEquipo.setPlaca(placa);
        consultarEquipo.setIdPersona(idUsuario);
        consultarEquipo.setProcesoEquipo(procesoEquipo);
        consultarEquipo.ejecutar(new ConsultarEquipoObservador());
    }
    //endregion

    //region Observadores Desechables
    private class ConsultarEquipoObservador extends AccionesObservador<Respuesta<EquipoMdl>> {
        @Override
        public void onNext(Respuesta<EquipoMdl> value) {
            super.onNext(value);
            if (value.getTipoNotificacion().getValue() == 3)
                obtenerVista().respuestaConsultaEquipo(MapeadoresComunes.MapearEntidadesIguales(value.getEntidades().get(0), new EquipoVM()));
            else
                obtenerVista().mostrarMensaje(value.getMensajes().get(0));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
            obtenerVista().tareaTerminada();
        }
    }
    //endregion
}
