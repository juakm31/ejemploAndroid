package com.infotrack.talleres.datos.repositorios.fachada.usos;

import android.content.Context;

import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.datos.entidades.UsuarioAutenticadoDto;
import com.infotrack.talleres.datos.entidades.UsuarioLoginDto;
import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AutenticacionRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.autenticacion.AutenticacionOrigenDatos;
import com.infotrack.talleres.dominio.modelos.UsuarioAutenticadoMdl;
import com.infotrack.talleres.dominio.modelos.UsuarioLoginMdl;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class AutenticacionUso implements AutenticacionRepositorio {

    private AutenticacionOrigenDatos origenDatos;
    private final MapeadorGenerico<UsuarioLoginDto, UsuarioLoginMdl> mapUsuarioLogin;
    private final MapeadorGenerico<Respuesta<UsuarioAutenticadoDto>, Respuesta<UsuarioAutenticadoMdl>> mapRespuesta;
    private final FabricaAbstracta autenticacionFabrica;

    @Inject
    AutenticacionUso(@NonNull GeneradorFabrica generadorFabrica,
                     @NonNull MapeadorGenerico<UsuarioLoginDto, UsuarioLoginMdl> mapUsuarioLogin,
                     @NonNull MapeadorGenerico<Respuesta<UsuarioAutenticadoDto>, Respuesta<UsuarioAutenticadoMdl>> mapRespuesta) {
        autenticacionFabrica = generadorFabrica.crearFabrica(FabricasEnum.Autenticacion);
        this.origenDatos = (AutenticacionOrigenDatos) autenticacionFabrica.crear(OrigenDatosEnum.RETROFIT.toString());
        this.mapUsuarioLogin = mapUsuarioLogin;
        this.mapRespuesta = mapRespuesta;
    }

    @Override
    public Observable<Respuesta<UsuarioAutenticadoMdl>> autenticarUsuario(UsuarioLoginMdl usuarioLoginMdl) {
        this.origenDatos = (AutenticacionOrigenDatos) autenticacionFabrica.crear(OrigenDatosEnum.RETROFIT.toString());
        return origenDatos.autenticarUsuario(mapUsuarioLogin.revertir(usuarioLoginMdl, new UsuarioLoginDto())).map(usuarioAutenticadoDtoRespuesta -> mapRespuesta.mapear(usuarioAutenticadoDtoRespuesta, new Respuesta<UsuarioAutenticadoMdl>()));
    }
}
