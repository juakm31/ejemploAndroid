package com.infotrack.talleres.presentacion.componentes.alistamiento.fabrica;

import com.infotrack.talleres.datos.excepciones.GenerarFabricaNullException;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

public class AlmacenamientoFabrica implements FabricaAbstracta<AccionesTipoAlistamiento> {
    @Override
    public AccionesTipoAlistamiento crear(String Tipo) {
        switch (TipoAlistamientoEnum.valueOf(Tipo)) {
            case Recepcion:
                return new TipoRecepcion();
            case Despacho:
                return new TipoDespacho();
        }
        throw new GenerarFabricaNullException();
    }
}
