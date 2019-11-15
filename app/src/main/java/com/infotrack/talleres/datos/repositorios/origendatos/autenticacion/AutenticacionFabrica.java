package com.infotrack.talleres.datos.repositorios.origendatos.autenticacion;

import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

public class AutenticacionFabrica implements FabricaAbstracta<AutenticacionOrigenDatos> {

    @Override
    public AutenticacionOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)) {
            case FIREBASE:
                break;
            case RETROFIT:
                return new AutenticacionRetrofit();
        }
        return null;
    }
}
