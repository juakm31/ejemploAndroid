package com.infotrack.talleres.presentacion.componentes.alistamiento.fabrica;

import com.infotrack.talleres.R;

public class TipoRecepcion implements AccionesTipoAlistamiento {

    @Override
    public int tituloToolbarListadoAlistamiento() {
        return R.string.alista_toolbar_listado_recepcion;
    }

    @Override
    public int tituloToolbarInicioAlistamiento() {
        return R.string.alista_toolbar_Recepcion;
    }

    @Override
    public int tituloToolbarListadoEquipos() {
        return R.string.alista_toolbar_listado_equipos_recepcion;
    }
}
