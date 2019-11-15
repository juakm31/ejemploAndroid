package com.infotrack.talleres.presentacion.componentes.alistamiento.datosequipo;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.talleres.dominio.casosdeuso.equipo.PersistirEquipo;
import com.infotrack.talleres.dominio.modelos.EquipoLocalMdl;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;

public class DatosEquipoPresentador extends PresentadorBase<DatosEquipoContrato> {

    //region Atributos
    private PersistirEquipo persistirEquipo;
    private MapeadorGenerico<EquipoLocalVM, EquipoLocalMdl> mapPersistirEquipo;
    //endregion

    //region Constructor
    @Inject
    public DatosEquipoPresentador(PersistirEquipo persistirEquipo,
                                  MapeadorGenerico<EquipoLocalVM, EquipoLocalMdl> mapPersistirEquipo) {
        this.persistirEquipo = persistirEquipo;
        this.mapPersistirEquipo = mapPersistirEquipo;
    }
    //endregion

    //region Presentador Base
    @Override
    public void destruir() {
        persistirEquipo.desechar();
    }
    //endregion

    //region Propios
    void persistirEquipo(EquipoLocalVM equipoLocalVM, TipoAlistamientoEnum tipoAlistamientoEnum, int idTaller) {
        persistirEquipo.setEquipoLocalMdl(mapPersistirEquipo.mapear(equipoLocalVM, new EquipoLocalMdl()));
        persistirEquipo.setTipoAlistamiento(tipoAlistamientoEnum);
        persistirEquipo.setIdTaller(idTaller);
        persistirEquipo.ejecutar(new PersistirEquipoObservador());
    }
    //endregion

    //region Observadores Desechables
    class PersistirEquipoObservador extends AccionesObservador<Void> {
        @Override
        public void onNext(Void value) {
            super.onNext(value);
            obtenerVista().finalizarActividad();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
            obtenerVista().finalizarActividad();
        }
    }
    //endregion
}
