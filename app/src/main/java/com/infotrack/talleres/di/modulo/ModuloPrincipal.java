package com.infotrack.talleres.di.modulo;

import android.content.Context;

import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AlistamientoRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.AutenticacionRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.CasoRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.ConfiguracionRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.DiagnosticoRechazoRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.EquipoRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.ListaChequeoRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.MenuRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.TransportadorRepositorio;
import com.infotrack.talleres.datos.repositorios.fachada.usos.AlistamientoUso;
import com.infotrack.talleres.datos.repositorios.fachada.usos.AutenticacionUso;
import com.infotrack.talleres.datos.repositorios.fachada.usos.CasoUso;
import com.infotrack.talleres.datos.repositorios.fachada.usos.ConfiguracionUso;
import com.infotrack.talleres.datos.repositorios.fachada.usos.DiagnosticoRechazoUso;
import com.infotrack.talleres.datos.repositorios.fachada.usos.EquipoUso;
import com.infotrack.talleres.datos.repositorios.fachada.usos.ListaChequeoUso;
import com.infotrack.talleres.datos.repositorios.fachada.usos.MenuUso;
import com.infotrack.talleres.datos.repositorios.fachada.usos.TransportadorUso;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.base.INavegador;
import com.infotrack.talleres.presentacion.base.Navegador;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ModuloPrincipal {

    private final AplicacionPrincipal aplicacion;

    public ModuloPrincipal(AplicacionPrincipal aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Provides
    @Singleton
    Context proporcionaContextoDeAplicacion() {
        return aplicacion;
    }

    @Provides
    @Singleton
    INavegador obtenerNavegador(Navegador navegador) {
        return navegador;
    }

    @Provides
    @Named("hilo_ejecutor")
    Scheduler proporcionaHiloEjecutor() {
        return Schedulers.io();
    }

    @Provides
    @Named("hilo_interfaz_usuario")
    Scheduler proporcionaHiloUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    AlistamientoRepositorio alistamientoRepositorio(AlistamientoUso alistamientoUso) {
        return alistamientoUso;
    }

    @Provides
    @Singleton
    AutenticacionRepositorio autenticacionRepositorio(AutenticacionUso autenticacionUso) {
        return autenticacionUso;
    }

    @Provides
    @Singleton
    EquipoRepositorio equipoRepositorio(EquipoUso equipoUso) {
        return equipoUso;
    }

    @Provides
    @Singleton
    TransportadorRepositorio transportadorRepositorio(TransportadorUso transportadorUso) {
        return transportadorUso;
    }

    @Provides
    @Singleton
    ListaChequeoRepositorio listaChequeoRepositorio(ListaChequeoUso listaChequeoUso) {
        return listaChequeoUso;
    }

    @Provides
    @Singleton
    ConfiguracionRepositorio configuracionRepositorio(ConfiguracionUso configuracionUso) {
        return configuracionUso;
    }

    @Provides
    @Singleton
    MenuRepositorio menuRepositorio(MenuUso menuUso) {
        return menuUso;
    }

    @Provides
    @Singleton
    DiagnosticoRechazoRepositorio diagnosticoRechazoRepositorio(DiagnosticoRechazoUso diagnosticoRechazoUso) {
        return diagnosticoRechazoUso;
    }

    @Provides
    @Singleton
    CasoRepositorio casoRepositorio(CasoUso casoUso){
        return casoUso;
    }
}
