package com.infotrack.talleres.datos.repositorios.origendatos.configuracion;

import io.reactivex.Observable;

public interface ConfiguracionOrigenDatos {
    Observable<String> consultarPlantillaTipoEquipo(String tipoEquipo);
}
