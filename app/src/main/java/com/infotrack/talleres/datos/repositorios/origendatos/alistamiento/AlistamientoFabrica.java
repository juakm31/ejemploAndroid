package com.infotrack.talleres.datos.repositorios.origendatos.alistamiento;

import com.infotrack.talleres.datos.excepciones.GenerarFabricaNullException;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

public class AlistamientoFabrica implements FabricaAbstracta<AlistamientoOrigenDatos> {

    @Override
    public AlistamientoOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)){
            case FIREBASE:
                return new AlistamientoFirebase();
            case RETROFIT:
                return new AlistamientoRetrofit();
            default:
                throw new GenerarFabricaNullException();
        }
    }
}
