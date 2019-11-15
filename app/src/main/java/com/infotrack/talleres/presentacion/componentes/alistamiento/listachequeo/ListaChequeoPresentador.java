package com.infotrack.talleres.presentacion.componentes.alistamiento.listachequeo;

import com.google.gson.Gson;
import com.infotrack.artefactolistachequeo.DTO.ListaChequeoViewModel;
import com.infotrack.artefactos.utilitarios.base.AccionesObservador;
import com.infotrack.artefactos.utilitarios.base.Mapeadores.MapeadorGenerico;
import com.infotrack.artefactos.utilitarios.base.PresentadorBase;
import com.infotrack.artefactos.utilitarios.base.Respuesta.Respuesta;
import com.infotrack.artefactos.utilitarios.comunes.CadenasHelper;
import com.infotrack.talleres.datos.excepciones.ListaChequeoNoEncontradaException;
import com.infotrack.talleres.dominio.casosdeuso.configuracion.ConsultarPlantillaTipoEquipo;
import com.infotrack.talleres.dominio.casosdeuso.equipo.ActualizarEtapaEquipo;
import com.infotrack.talleres.dominio.casosdeuso.equipo.AgregarEquipo;
import com.infotrack.talleres.dominio.casosdeuso.listachequeo.ConsultarListaChequeo;
import com.infotrack.talleres.dominio.casosdeuso.listachequeo.PersistirListaChequeo;
import com.infotrack.talleres.dominio.modelos.EquipoResMdl;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoResVM;
import com.infotrack.talleres.transversal.constantes.negocio.EquipoConstantes;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;

import java.util.HashMap;

import javax.inject.Inject;

public class ListaChequeoPresentador extends PresentadorBase<ListaChequeoContrato> {

    //region Atributos
    private ConsultarListaChequeo consultarListaChequeo;
    private PersistirListaChequeo persistirListaChequeo;
    private ConsultarPlantillaTipoEquipo consultarPlantillaTipoEquipo;
    private ActualizarEtapaEquipo actualizarEtapaEquipo;

    private String claseEquipo;

    private AgregarEquipo agregarEquipo;
    private MapeadorGenerico<EquipoResVM, EquipoResMdl> mapAgregarEquipo;

    private TipoAlistamientoEnum tipoAlistamiento;

    private int idTaller;
    private EquipoLocalVM equipoLocalVM;
    //endregion

    //region Constructor
    @Inject
    ListaChequeoPresentador(ConsultarListaChequeo consultarListaChequeo,
                            PersistirListaChequeo persistirListaChequeo,
                            ConsultarPlantillaTipoEquipo consultarPlantillaTipoEquipo,
                            ActualizarEtapaEquipo actualizarEtapaEquipo,
                            AgregarEquipo agregarEquipo,
                            MapeadorGenerico<EquipoResVM, EquipoResMdl> mapAgregarEquipo) {
        this.consultarListaChequeo = consultarListaChequeo;
        this.persistirListaChequeo = persistirListaChequeo;
        this.consultarPlantillaTipoEquipo = consultarPlantillaTipoEquipo;
        this.actualizarEtapaEquipo = actualizarEtapaEquipo;
        this.agregarEquipo = agregarEquipo;
        this.mapAgregarEquipo = mapAgregarEquipo;
    }
    //endregion

    //region Presentador base Contrato
    @Override
    public void destruir() {
        consultarListaChequeo.desechar();
        persistirListaChequeo.desechar();
        consultarPlantillaTipoEquipo.desechar();
        actualizarEtapaEquipo.desechar();
    }
    //endregion

    //region Propios


    public void setTipoAlistamiento(TipoAlistamientoEnum tipoAlistamiento) {
        this.tipoAlistamiento = tipoAlistamiento;
    }

    void consultarListaChequeo(String recepcionId, String placa, String claseEquipo) {
        this.claseEquipo = claseEquipo;
        consultarListaChequeo.setRecepcionId(recepcionId);
        consultarListaChequeo.setPlaca(placa);
        consultarListaChequeo.ejecutar(new ObservadorConsultarListaChequeo());
    }

    void persistirListaChequeo(String recepcionId, String placa, ListaChequeoViewModel listaChequeo) {
        persistirListaChequeo.setRecepcionId(recepcionId);
        persistirListaChequeo.setPlaca(placa);
        persistirListaChequeo.setListaChequeo(new Gson().toJson(listaChequeo));
        persistirListaChequeo.ejecutar(new ObservadorPersistirListaChequeo());
    }

    private void consultarPlantillaListaChequeo(String claseEquipo) {
        consultarPlantillaTipoEquipo.setTipoEquipo(claseEquipo);
        consultarPlantillaTipoEquipo.ejecutar(new ObservadorConsultarPlantilla());
    }

    void actualizarEtapaEquipo(int idTaller, EquipoLocalVM equipo) {
        HashMap<String, Object> mapEtapa = new HashMap<>();
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            mapEtapa.put(String.format(EquipoConstantes.PersistirEquipoRecepcion, idTaller, equipo.getIdAlistamiento(), equipo.getPlaca(), EquipoConstantes.ActualizarEtapaEquipos), equipo.getEtapa());
        else
            mapEtapa.put(String.format(EquipoConstantes.PersistirEquipoDespacho, idTaller, equipo.getIdAlistamiento(), equipo.getPlaca(), EquipoConstantes.ActualizarEtapaEquipos), equipo.getEtapa());
        actualizarEtapaEquipo.setMapPropiedad(mapEtapa);
        actualizarEtapaEquipo.ejecutar(new ObservadorActualizarEtapaEquipo());
    }

    void enviarEquipoREST(EquipoResVM equipoResVM, TipoAlistamientoEnum tipoAlistamiento, int idTaller, EquipoLocalVM equipoLocalVM) {
        obtenerVista().tareaEnProceso();
        this.idTaller = idTaller;
        this.equipoLocalVM = equipoLocalVM;
        agregarEquipo.setEquipoResMdl(mapAgregarEquipo.mapear(equipoResVM, new EquipoResMdl()));
        agregarEquipo.setTipoAlistamientoEnum(tipoAlistamiento);
        agregarEquipo.ejecutar(new ObservadorAgregarEquipo());
    }
    //endregion

    //region Observadores Desechables
    private final class ObservadorConsultarListaChequeo extends AccionesObservador<String> {
        @Override
        public void onNext(String value) {
            super.onNext(value);
            if (value == null || value.isEmpty())
                consultarPlantillaListaChequeo(claseEquipo);
            else
                obtenerVista().respuestaListaChequeo(value);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (e instanceof ListaChequeoNoEncontradaException)
                consultarPlantillaListaChequeo(claseEquipo);
            else
                obtenerVista().mostrarMensajeError(e.getCause().toString());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    private final class ObservadorConsultarPlantilla extends AccionesObservador<String> {
        @Override
        public void onNext(String value) {
            super.onNext(value);
            obtenerVista().respuestaListaChequeo(value);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            obtenerVista().mostrarMensajeError(e.getCause().toString());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    private final class ObservadorPersistirListaChequeo extends AccionesObservador<Void> {
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            obtenerVista().mostrarMensajeError(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    private final class ObservadorActualizarEtapaEquipo extends AccionesObservador<Void> {
        @Override
        public void onNext(Void value) {
            super.onNext(value);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
            obtenerVista().finalizarActividad();
        }
    }

    private final class ObservadorAgregarEquipo extends AccionesObservador<Respuesta<Boolean>> {
        @Override
        public void onNext(Respuesta<Boolean> value) {
            super.onNext(value);
            if (value.isResultado())
                actualizarEtapaEquipo(idTaller, equipoLocalVM);
            else
                obtenerVista().mostrarMensajeError(new CadenasHelper().encadenarListaMensajesConComodin(value.getMensajes(), CadenasHelper.SALTO_LINEA));

            obtenerVista().tareaTerminada();
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
    //endregion
}
