package com.infotrack.talleres.presentacion.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.infotrack.artefactos.utilitarios.base.FragmentoBase;
import com.infotrack.talleres.presentacion.componentes.alistamiento.crear.CrearAlistamientoActividad;
import com.infotrack.talleres.presentacion.componentes.alistamiento.crear.CrearAlistamientoFragmento;
import com.infotrack.talleres.presentacion.componentes.alistamiento.datosequipo.DatosEquipoActividad;
import com.infotrack.talleres.presentacion.componentes.alistamiento.firma.FirmaActividad;
import com.infotrack.talleres.presentacion.componentes.alistamiento.listachequeo.ListaChequeoActividad;
import com.infotrack.talleres.presentacion.componentes.alistamiento.listachequeo.ListaChequeoFragmento;
import com.infotrack.talleres.presentacion.componentes.alistamiento.listadoalistamientos.ListadoAlistamientoActividad;
import com.infotrack.talleres.presentacion.componentes.alistamiento.listadoalistamientos.ListadoAlistamientoFragmento;
import com.infotrack.talleres.presentacion.componentes.alistamiento.listadoequipos.ListadoEquiposActividad;
import com.infotrack.talleres.presentacion.componentes.alistamiento.listadoequipos.ListadoEquiposFragmento;
import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.caso.CasoActividad;
import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado.ListadoDiagnosticoRechazoActividad;
import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado.ListadoDiagnosticoRechazoFragmento;
import com.infotrack.talleres.presentacion.componentes.menu.MenuActividad;
import com.infotrack.talleres.presentacion.componentes.menu.MenuFragmento;
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoLocalVM;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class Navegador implements INavegador {

    @Inject
    public Navegador() {
    }

    @Override
    public void seleccionar(int i, Context context) {

    }

    @Override
    public void navegarListadoAlistamientoActividad(Context contexto, TipoAlistamientoEnum tipoAlistamientoEnum) {
        contexto.startActivity(ListadoAlistamientoActividad.obtenerIntencion(contexto, tipoAlistamientoEnum));
    }

    @Override
    public FragmentoBase navegarListadoAlistamientoFragmento(TipoAlistamientoEnum tipoAlistamientoEnum) {
        return ListadoAlistamientoFragmento.obtenerInstancia(tipoAlistamientoEnum);
    }


    @Override
    public void navegarListadoEquiposActividad(Context contexto, String conductor, String idAlistamiento, TipoAlistamientoEnum tipoAlistamientoEnum) {
        contexto.startActivity(ListadoEquiposActividad.obtenerIntencion(contexto, conductor, idAlistamiento, tipoAlistamientoEnum));
    }

    @Override
    public FragmentoBase navegarListadoEquiposFragmento(String conductor, String idAlistamiento, TipoAlistamientoEnum tipoAlistamientoEnum) {
        return ListadoEquiposFragmento.obtenerInstancia(conductor, idAlistamiento, tipoAlistamientoEnum);
    }

    @Override
    public void navegarDatosEquipo(@NonNull Context contexto) {

    }

    @Override
    public void navegarInicioAlistamientoActividad(Context contexto, TipoAlistamientoEnum tipoAlistamientoEnum) {
        contexto.startActivity(CrearAlistamientoActividad.obtenerIntencion(contexto, tipoAlistamientoEnum));
    }

    @Override
    public FragmentoBase navegarInicioAlistamientoFragmento(TipoAlistamientoEnum tipoAlistamientoEnum) {
        return CrearAlistamientoFragmento.obtenerInstancia(tipoAlistamientoEnum);
    }

    @Override
    public void navegarDatosEquipoActividad(Context contexto, String conductor, String idAlistamiento, TipoAlistamientoEnum tipoAlistamientoEnum) {
        contexto.startActivity(DatosEquipoActividad.obtenerIntencion(contexto, conductor, idAlistamiento, tipoAlistamientoEnum));
    }

    @Override
    public void navegarListaChequeoActividad(Fragment fragmento, EquipoLocalVM equipoLocal, int codigoIntencion, TipoAlistamientoEnum tipoAlistamientoEnum) {
        Intent intent = ListaChequeoActividad.obtenerIntencion(fragmento.getContext(), equipoLocal, tipoAlistamientoEnum);
        fragmento.startActivityForResult(intent, codigoIntencion);
    }

    @Override
    public FragmentoBase navegarListaChequeoFragmento(EquipoLocalVM equipoLocal, TipoAlistamientoEnum tipoAlistamientoEnum) {
        return ListaChequeoFragmento.obtenerInstancia(equipoLocal, tipoAlistamientoEnum);
    }

    @Override
    public void navegarFirmaAlistamiento(Context contexto, AlistamientoLocalVM alistamientoSeleccionado, TipoAlistamientoEnum tipoAlistamientoEnum) {
        contexto.startActivity(FirmaActividad.obtenerIntencion(contexto, alistamientoSeleccionado, tipoAlistamientoEnum));
    }

    @Override
    public void navegarMenuActividad(Context contexto) {
        contexto.startActivity(MenuActividad.obtenerIntencion(contexto));
    }

    @Override
    public FragmentoBase navegarMenuFragmento() {
        return MenuFragmento.obtenerInstancia();
    }

    @Override
    public void navegarListadoDiagnosticoRechazoActividad(Context contexto, TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazoEnum) {
        contexto.startActivity(ListadoDiagnosticoRechazoActividad.obtenerIntencion(contexto, tipoDiagnosticoRechazoEnum));
    }

    @Override
    public FragmentoBase navegarListadoDiagnosticoRechazoFragmento(TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazoEnum) {
        return ListadoDiagnosticoRechazoFragmento.obtenerInstancia(tipoDiagnosticoRechazoEnum);
    }

    @Override
    public void navegarCasoActividad(Context contexto, TipoDiagnosticoRechazoEnum tipoDiagnostico) {
        contexto.startActivity(CasoActividad.obtenerIntencion(contexto, tipoDiagnostico));
    }
}
