package com.infotrack.talleres.di.componente;

import android.content.Context;

import com.infotrack.talleres.di.modulo.ModuloPrincipal;
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
import com.infotrack.talleres.presentacion.componentes.autenticacion.IniciarSesionActividad;
import com.infotrack.talleres.presentacion.componentes.comunes.consultaequipo.ConsultaEquipoFragmento;
import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.caso.CasoActividad;
import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado.ListadoDiagnosticoRechazoActividad;
import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado.ListadoDiagnosticoRechazoFragmento;
import com.infotrack.talleres.presentacion.componentes.menu.MenuActividad;
import com.infotrack.talleres.presentacion.componentes.menu.MenuFragmento;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ModuloPrincipal.class)
public interface ComponentePrincipal {

    Context contexto();

    void inject(IniciarSesionActividad iniciarSesionActividad);

    void inject(ListadoAlistamientoActividad listadoAlistamientoActividad);

    void inject(ListadoAlistamientoFragmento listadoAlistamientoFragmento);

    void inject(ListadoEquiposActividad listadoAlistamientoActividad);

    void inject(ListadoEquiposFragmento listadoAlistamientoFragmento);

    void inject(CrearAlistamientoActividad crearAlistamientoActividad);

    void inject(CrearAlistamientoFragmento inicioAlistamientoFragmento);

    void inject(DatosEquipoActividad datosEquipoActividad);

    void inject(ListaChequeoActividad listaChequeoActividad);

    void inject(ListaChequeoFragmento listaChequeoFragmento);

    void inject(FirmaActividad firmaActividad);

    void inject(MenuActividad menuActividad);

    void inject(MenuFragmento bandejaEntradaFragmento);

    void inject(ConsultaEquipoFragmento consultaEquipoFragmento);

    void inject(ListadoDiagnosticoRechazoActividad listadoDiagnosticoRechazoActividad);

    void inject(ListadoDiagnosticoRechazoFragmento listadoDiagnosticoRechazoFragmento);

    void inject(CasoActividad casoActividad);
}
