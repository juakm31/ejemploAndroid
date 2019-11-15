package com.infotrack.talleres.datos.repositorios.origendatos.listachequeo;

import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;


public class ListaChequeoFabrica implements FabricaAbstracta<ListaChequeoOrigenDatos> {

    @Override
    public ListaChequeoOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)) {
            case FIREBASE:
                return new ListaChequeoFirebase();
            case RETROFIT:
                break;
        }
        return null;
    }
}
