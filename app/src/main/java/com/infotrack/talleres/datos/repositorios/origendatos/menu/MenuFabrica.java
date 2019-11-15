package com.infotrack.talleres.datos.repositorios.origendatos.menu;

import com.infotrack.talleres.datos.excepciones.GenerarFabricaNullException;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

public class MenuFabrica implements FabricaAbstracta<MenuOrigenDatos> {

    @Override
    public MenuOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)) {
            case FIREBASE:
                return null;
            case RETROFIT:
                return new MenuRetrofit();
            default:
                throw new GenerarFabricaNullException();
        }
    }
}
