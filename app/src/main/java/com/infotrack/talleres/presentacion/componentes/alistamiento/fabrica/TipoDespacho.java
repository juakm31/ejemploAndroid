package com.infotrack.talleres.presentacion.componentes.alistamiento.fabrica;

import com.infotrack.talleres.R;

public class TipoDespacho implements AccionesTipoAlistamiento {

    @Override
    public int tituloToolbarListadoAlistamiento() {
        return R.string.alista_toolbar_listado_despacho;
    }

    @Override
    public int tituloToolbarInicioAlistamiento() {
        return R.string.alista_toolbar_despacho;
    }

    @Override
    public int tituloToolbarListadoEquipos() {
        return R.string.alista_toolbar_listado_equipos_despacho;
    }
}
