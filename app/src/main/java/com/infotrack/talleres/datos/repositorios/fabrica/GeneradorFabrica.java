package com.infotrack.talleres.datos.repositorios.fabrica;

import com.infotrack.talleres.datos.excepciones.GenerarFabricaNullException;
import com.infotrack.talleres.datos.repositorios.origendatos.alistamiento.AlistamientoFabrica;
import com.infotrack.talleres.datos.repositorios.origendatos.autenticacion.AutenticacionFabrica;
import com.infotrack.talleres.datos.repositorios.origendatos.caso.CasoFabrica;
import com.infotrack.talleres.datos.repositorios.origendatos.diagnosticorechazo.DiagnosticoRechazoFabrica;
import com.infotrack.talleres.datos.repositorios.origendatos.menu.MenuFabrica;
import com.infotrack.talleres.datos.repositorios.origendatos.configuracion.ConfiguracionFabrica;
import com.infotrack.talleres.datos.repositorios.origendatos.equipo.EquipoFabrica;
import com.infotrack.talleres.datos.repositorios.origendatos.listachequeo.ListaChequeoFabrica;
import com.infotrack.talleres.datos.repositorios.origendatos.transportador.TransportadorFabrica;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;

import javax.inject.Inject;

public class GeneradorFabrica {

    @Inject
    GeneradorFabrica() {
    }

    public static FabricaAbstracta crearFabrica(FabricasEnum listaFabricas) {
        switch (listaFabricas) {
            case Autenticacion:
                return new AutenticacionFabrica();
            case Alistamiento:
                return new AlistamientoFabrica();
            case Equipo:
                return new EquipoFabrica();
            case Transportador:
                return new TransportadorFabrica();
            case ListaChequeo:
                return new ListaChequeoFabrica();
            case Menu:
                return new MenuFabrica();
            case Configuracion:
                return new ConfiguracionFabrica();
            case DiagnosticoRechazo:
                return new DiagnosticoRechazoFabrica();
            case Caso:
                return new CasoFabrica();
            default:
                throw new GenerarFabricaNullException();
        }
    }
}
