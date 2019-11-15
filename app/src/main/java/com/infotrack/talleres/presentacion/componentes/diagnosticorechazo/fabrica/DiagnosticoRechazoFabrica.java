package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.fabrica;

import com.infotrack.talleres.datos.excepciones.GenerarFabricaNullException;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;

public class DiagnosticoRechazoFabrica implements FabricaAbstracta<AccionesDiagnosticoRechazo> {
    @Override
    public AccionesDiagnosticoRechazo crear(String Tipo) {
        switch (TipoDiagnosticoRechazoEnum.valueOf(Tipo)) {
            case DiagnosticoInicial:
                return new TipoDiagnosticoInicial();
            case DiagnosticoFinal:
                return new TipoDiagnosticoFinal();
            case RechazoDiagnosticoInicial:
                return new TipoRechazoDiagnosticoInicial();
            case RechazoDiagnosticoFinal:
                return new TipoRechazoDiagnosticoFinal();
        }
        throw new GenerarFabricaNullException();
    }
}
