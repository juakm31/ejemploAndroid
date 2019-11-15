package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import io.reactivex.Observable;

public interface ConfiguracionRepositorio {
    Observable<String> consultarPlantillaTipoEquipo(String tipoEquipo);
}
