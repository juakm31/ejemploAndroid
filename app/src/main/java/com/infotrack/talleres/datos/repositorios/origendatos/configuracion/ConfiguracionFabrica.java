package com.infotrack.talleres.datos.repositorios.origendatos.configuracion;

import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

public class ConfiguracionFabrica implements FabricaAbstracta<ConfiguracionOrigenDatos> {
    @Override
    public ConfiguracionOrigenDatos crear(String Tipo) {
        switch (OrigenDatosEnum.valueOf(Tipo)) {
            case FIREBASE:
                return new ConfiguracionFirebase();
            case RETROFIT:
                break;
        }
        return null;
    }
}
