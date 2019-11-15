package com.infotrack.talleres.presentacion.componentes.alistamiento.crear;

import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadoresComunes;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.comunes.CadenasHelper;
import com.infotrack.artefactos.utilitarios.generales.enumeradores.TipoNotificacionEnum;
import com.infotrack.talleres.datos.entidades.AlistamientoDto;
import com.infotrack.talleres.dominio.casosdeuso.alistamiento.CrearAlistamiento;
import com.infotrack.talleres.dominio.casosdeuso.alistamiento.PersistirAlistamiento;
import com.infotrack.talleres.dominio.casosdeuso.transportador.ConsultarVehiculoPorPlaca;
import com.infotrack.talleres.dominio.modelos.AlistamientoLocalMdl;
import com.infotrack.talleres.dominio.modelos.AlistamientoMdl;
import com.infotrack.talleres.dominio.modelos.TransportadorMdl;
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoLocalVM;
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoVM;
import com.infotrack.talleres.presentacion.vistamodelos.TransportadorVM;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import javax.inject.Inject;

public class CrearAlistamientoPresentador extends PresentadorBase<CrearAlistamientoContrato> {

    //region Atributos
    private ConsultarVehiculoPorPlaca consultarVehiculoPorPlaca;
    private CrearAlistamiento crearAlistamiento;
    private PersistirAlistamiento persistirAlistamiento;

    private MapeadorGenerico<AlistamientoDto, AlistamientoMdl> mapAlistamiento;
    private MapeadorGenerico<AlistamientoLocalVM, AlistamientoLocalMdl> mapPersistirRecepcion;

    private UsuarioAutenticadoVM usuario;
    private AlistamientoDto alistamiento;
    private TipoAlistamientoEnum tipoAlistamiento;
    //endregion

    //region Constructor
    @Inject
    public CrearAlistamientoPresentador(ConsultarVehiculoPorPlaca consultarVehiculoPorPlaca,
                                        CrearAlistamiento crearAlistamiento,
                                        PersistirAlistamiento persistirAlistamiento,
                                        MapeadorGenerico<AlistamientoDto, AlistamientoMdl> mapAlistamiento,
                                        MapeadorGenerico<AlistamientoLocalVM, AlistamientoLocalMdl> mapPersistirRecepcion) {
        this.consultarVehiculoPorPlaca = consultarVehiculoPorPlaca;
        this.crearAlistamiento = crearAlistamiento;
        this.mapAlistamiento = mapAlistamiento;
        this.persistirAlistamiento = persistirAlistamiento;
        this.mapPersistirRecepcion = mapPersistirRecepcion;
    }
    //endregion

    //region Sobrecarga
    @Override
    public void destruir() {
        consultarVehiculoPorPlaca.desechar();
        crearAlistamiento.desechar();
    }
    //endregion

    //region Capacidades
    void consultarVehiculoPorPlaca(String placa, TipoAlistamientoEnum tipoAlistamientoEnum) {
        obtenerVista().tareaEnProceso();
        consultarVehiculoPorPlaca.setPlaca(placa);
        consultarVehiculoPorPlaca.setTipoAlistamiento(tipoAlistamientoEnum);
        consultarVehiculoPorPlaca.ejecutar(new ConsultarVehiculoPorPlacaRecepcionObservador());
    }

    void crearAlistamiento(AlistamientoDto alistamientoDto, TipoAlistamientoEnum tipoAlistamiento, UsuarioAutenticadoVM usuario) {
        obtenerVista().tareaEnProceso();
        this.usuario = usuario;
        this.alistamiento = alistamientoDto;
        this.tipoAlistamiento = tipoAlistamiento;
        crearAlistamiento.setAlistamientoMdl(mapAlistamiento.mapear(alistamientoDto, new AlistamientoMdl()));
        crearAlistamiento.setTipoAlistamiento(tipoAlistamiento);
        crearAlistamiento.ejecutar(new CrearAlistamientoObservador());
    }

    private void persistirAlistamientoLocal(AlistamientoVM alistamientoCreado) {
        AlistamientoLocalVM alistamientoLocal = new AlistamientoLocalVM();
        alistamientoLocal.setCreador(usuario.getNombreUsuario());
        alistamientoLocal.setIdTaller(alistamientoCreado.getIdTaller());
        alistamientoLocal.setIdTransportador(alistamientoCreado.getIdTransportador());
        alistamientoLocal.setTransportadora(alistamientoCreado.getTransportadora());
        alistamientoLocal.setConductor(alistamiento.getConductor());
        alistamientoLocal.setTransportadora(alistamiento.getTransportadora());
        alistamientoLocal.setIdConductor(alistamiento.getIdConductor());
        alistamientoLocal.setPlaca(alistamientoCreado.getPlaca());
        alistamientoLocal.setObservaciones(alistamientoCreado.getObservaciones());
        alistamientoLocal.setFechaInicio(alistamientoCreado.getFechaInicio());
        alistamientoLocal.setIdAlistamiento(alistamientoCreado.getIdAlistamiento());
        alistamientoLocal.setCantidadEquipos(0);
//        alistamientoLocal.setPermiteFinalizar(false);

        persistirAlistamiento.setTipoAlistamientoEnum(tipoAlistamiento);
        persistirAlistamiento.setAlistamientoLocalVM(mapPersistirRecepcion.mapear(alistamientoLocal, new AlistamientoLocalMdl()));
        persistirAlistamiento.ejecutar(new PersistirAlistamientoObservador());
    }
    //endregion

    //region Observadores
    class ConsultarVehiculoPorPlacaRecepcionObservador extends AccionesObservador<Respuesta<TransportadorMdl>> {
        @Override
        public void onNext(Respuesta<TransportadorMdl> value) {
            super.onNext(value);
            obtenerVista().tareaTerminada();
            if (value.isResultado())
                obtenerVista().llenarFormularioTransportador(MapeadoresComunes.MapearEntidadesIguales(value.getEntidades().get(0), new TransportadorVM()));
            else if (value.getTipoNotificacion().getValue() != TipoNotificacionEnum.Exitoso.getValue())
                obtenerVista().monstrarMensajesError(new CadenasHelper().encadenarListaMensajesConComodin(value.getMensajes(), CadenasHelper.SALTO_LINEA));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            obtenerVista().tareaTerminada();
            obtenerVista().monstrarMensajesError(AlistamientoConstantes.VAL_PLACA_INVALIDA);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    class CrearAlistamientoObservador extends AccionesObservador<Respuesta<AlistamientoMdl>> {
        @Override
        public void onNext(Respuesta<AlistamientoMdl> value) {
            super.onNext(value);
            obtenerVista().tareaTerminada();
            if (value.isResultado())
                persistirAlistamientoLocal(MapeadoresComunes.MapearEntidadesIguales(value.getEntidades().get(0), new AlistamientoVM()));
            else if (value.getTipoNotificacion().getValue() != TipoNotificacionEnum.Exitoso.getValue())
                obtenerVista().monstrarMensajesError(new CadenasHelper().encadenarListaMensajesConComodin(value.getMensajes(), CadenasHelper.SALTO_LINEA));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    class PersistirAlistamientoObservador extends AccionesObservador<Void> {
        @Override
        public void onComplete() {
            super.onComplete();
            obtenerVista().finalizarActividad();
        }
    }
    //endregion
}
