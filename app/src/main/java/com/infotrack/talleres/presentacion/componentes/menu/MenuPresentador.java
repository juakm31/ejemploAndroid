package com.infotrack.talleres.presentacion.componentes.menu;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadoresComunes;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.dominio.casosdeuso.menu.ConsultarMenu;
import com.infotrack.talleres.dominio.modelos.MenuMdl;
import com.infotrack.talleres.presentacion.vistamodelos.MenuVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MenuPresentador extends PresentadorBase<MenuContrato> {

    //region Atributos
    private ConsultarMenu consultarMenu;
    private List<MenuVM> listaMenu;
    //endregion

    //region Constructor
    @Inject
    public MenuPresentador(ConsultarMenu consultarMenu) {
        this.consultarMenu = consultarMenu;
        listaMenu = new ArrayList<>();
    }
    //endregion

    //region Presentador Base
    @Override
    public void destruir() {
        consultarMenu.desechar();
    }
    //endregion

    //region Propios
    void cargarBandejaEntrada(String idPersona, int idTaller) {
        consultarMenu.setIdPersona(idPersona);
        consultarMenu.setIdTaller(idTaller);
        consultarMenu.ejecutar(new ObservadorCargarBandejaEntrada());
    }

    void armarListado(Respuesta<MenuMdl> value) {
        for (int i = 0; i< value.getEntidades().size(); i++) {
            listaMenu.add(MapeadoresComunes.MapearEntidadesIguales(value.getEntidades().get(i), new MenuVM()));
        }
        obtenerVista().llenarMenu(listaMenu);
    }
    //endregion

    //region Observadores
    class ObservadorCargarBandejaEntrada extends AccionesObservador<Respuesta<MenuMdl>> {
        @Override
        public void onNext(Respuesta<MenuMdl> value) {
            super.onNext(value);
            armarListado(value);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
    //endregion
}
