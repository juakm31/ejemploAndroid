package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.talleres.dominio.modelos.MenuMdl;

import io.reactivex.Observable;

public interface MenuRepositorio {
    Observable<Respuesta<MenuMdl>> ConsultarMenu(String idPersona, int idTaller);
}
