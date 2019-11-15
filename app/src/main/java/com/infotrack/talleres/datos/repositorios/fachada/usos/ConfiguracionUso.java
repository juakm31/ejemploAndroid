package com.infotrack.talleres.datos.repositorios.fachada.usos;

import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.ConfiguracionRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.configuracion.ConfiguracionOrigenDatos;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ConfiguracionUso implements ConfiguracionRepositorio {

    @Inject
    public ConfiguracionUso(){}

    private ConfiguracionOrigenDatos generarFabricaFirebase() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.Configuracion);
        return (ConfiguracionOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.FIREBASE.toString());
    }

    @Override
    public Observable<String> consultarPlantillaTipoEquipo(String tipoEquipo) {
        return generarFabricaFirebase().consultarPlantillaTipoEquipo(tipoEquipo);
    }
}
