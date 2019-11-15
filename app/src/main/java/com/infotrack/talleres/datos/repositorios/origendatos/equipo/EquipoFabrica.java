package com.infotrack.talleres.datos.repositorios.origendatos.equipo;

import com.infotrack.talleres.datos.excepciones.GenerarFabricaNullException;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

public class EquipoFabrica implements FabricaAbstracta<EquipoOrigenDatos> {

    @Override
    public EquipoOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)) {
            case FIREBASE:
                return new EquipoFirebase();
            case RETROFIT:
                return new EquipoRetrofit();
            default:
                throw new GenerarFabricaNullException();
        }
    }
}
