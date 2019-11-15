package com.infotrack.talleres.datos.repositorios.origendatos.menu;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.MenuDto;
import com.infotrack.talleres.transversal.constantes.negocio.MenuConstantes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuOrigenDatos {
    @GET(MenuConstantes.URL_CARGAR_MENU)
    Observable<Respuesta<MenuDto>> consultarMenu(@Path("persona") String persona, @Path("taller") int taller);
}
