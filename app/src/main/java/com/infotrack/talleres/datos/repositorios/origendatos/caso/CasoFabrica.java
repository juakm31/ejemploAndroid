package com.infotrack.talleres.datos.repositorios.origendatos.caso;

import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

public class CasoFabrica implements FabricaAbstracta<CasoOrigenDatos> {
    @Override
    public CasoOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)) {
            case FIREBASE:
                return new CasoFirebase();
            case RETROFIT:
                break;
        }
        return null;
    }
}
