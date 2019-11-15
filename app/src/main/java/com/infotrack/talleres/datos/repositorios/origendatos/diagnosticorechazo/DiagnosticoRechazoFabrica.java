package com.infotrack.talleres.datos.repositorios.origendatos.diagnosticorechazo;

import com.infotrack.talleres.datos.excepciones.GenerarFabricaNullException;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

public class DiagnosticoRechazoFabrica implements FabricaAbstracta<DiagnosticoRechazoOrigenDatos> {

    @Override
    public DiagnosticoRechazoOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)) {
            case FIREBASE:
                return new DiagnosticoRechazoFirebase();
            case RETROFIT:
            default:
                throw new GenerarFabricaNullException();
        }
    }
}
