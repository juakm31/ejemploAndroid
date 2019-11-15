package com.infotrack.talleres.datos.repositorios.origendatos.transportador;

import com.infotrack.talleres.datos.excepciones.GenerarFabricaNullException;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

public class TransportadorFabrica implements FabricaAbstracta<TransportadorOrigenDatos> {

    @Override
    public TransportadorOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)) {
            case RETROFIT:
                return new TransportadorRetrofit();
            case FIREBASE:
            default:
                throw new GenerarFabricaNullException();
        }
    }
}
