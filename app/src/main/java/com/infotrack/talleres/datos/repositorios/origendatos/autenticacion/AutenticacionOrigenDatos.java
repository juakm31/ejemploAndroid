package com.infotrack.talleres.datos.repositorios.origendatos.autenticacion;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.UsuarioAutenticadoDto;
import com.infotrack.talleres.datos.entidades.UsuarioLoginDto;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AutenticacionOrigenDatos {
    @POST(ConstantesCompartidas.RETROFIT_AUTENTICACION)
    Observable<Respuesta<UsuarioAutenticadoDto>> autenticarUsuario(@Body UsuarioLoginDto usuarioLoginDto);

    AutenticacionOrigenDatos obtenerCliente();
}
