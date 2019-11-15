package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.dominio.modelos.UsuarioAutenticadoMdl;
import com.infotrack.talleres.dominio.modelos.UsuarioLoginMdl;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public interface AutenticacionRepositorio {
    Observable<Respuesta<UsuarioAutenticadoMdl>> autenticarUsuario(@NonNull UsuarioLoginMdl usuarioLoginMdl);
}
