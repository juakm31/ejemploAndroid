package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.fabrica;

import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.caso.CasoFabricaCallback;

public class TipoRechazoDiagnosticoInicial implements AccionesDiagnosticoRechazo{
    @Override
    public void configurarTarjetaObservaciones(CasoFabricaCallback listener) {
        listener.permitirObservaciones(true);
    }
}
