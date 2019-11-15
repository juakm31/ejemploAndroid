package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoequipos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.infotrack.artefactos.utilitarios.base.FragmentoBase;
import com.infotrack.artefactos.utilitarios.comunes.DialogosHelper;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.base.INavegador;
import com.infotrack.talleres.presentacion.componentes.alistamiento.fabrica.AccionesTipoAlistamiento;
import com.infotrack.talleres.presentacion.componentes.alistamiento.fabrica.AlmacenamientoFabrica;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.transversal.ControlsBottomSheetDialog;
import com.infotrack.talleres.transversal.constantes.CodigosIntencion;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.constantes.negocio.EquipoConstantes;
import com.infotrack.talleres.transversal.enumeradores.EtapasEquipoEnum;
import com.infotrack.talleres.transversal.enumeradores.EtapasListadoAlistamientoEnum;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;
import com.infotrack.talleres.transversal.firebase.rutas.AlistamientoFirebase;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ListadoEquiposFragmento extends FragmentoBase
        implements ListadoEquiposContrato,
        ListadoEquiposAdaptador.ListadoEquiposCallback,
        ControlsBottomSheetDialog.AccionesBottomSheet {

    //region Atributos
    @Inject
    INavegador navegador;
    @Inject
    ListadoEquiposPresentador presentador;

    @BindView(R.id.recycler_lista_equipos)
    RecyclerView equiposRecycler;
    private ProgressDialog ventanaProgreso;

    private ListadoEquiposAdaptador adaptador;
    private List<EquipoLocalVM> listadoEquipos;
    private TipoAlistamientoEnum tipoAlistamiento;
    private AccionesTipoAlistamiento fabricaAlistamiento;
    private UsuarioAutenticadoVM usuarioLogueado;

    private String idAlistamiento;
    private String conductor;
    private int itemSeleccionado;

    private int[] accionesEtapaCreada = new int[]{
            AccionesEquiposFabEnum.CheckList.getValue(),
            AccionesEquiposFabEnum.Eliminar.getValue()
    };
    //endregion

    //region Instancia
    public static ListadoEquiposFragmento obtenerInstancia(String conductor,
                                                           String idAlistamiento,
                                                           TipoAlistamientoEnum tipoAlistamientoEnum) {
        ListadoEquiposFragmento fragmento = new ListadoEquiposFragmento();
        Bundle argumentos = new Bundle();
        argumentos.putSerializable(AlistamientoConstantes.EXTRA_CONDUCTOR, conductor);
        argumentos.putSerializable(AlistamientoConstantes.EXTRA_ID_ALISTAMIENTO, idAlistamiento);
        argumentos.putSerializable(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamientoEnum);
        fragmento.setArguments(argumentos);
        return fragmento;
    }
    //endregion

    //region Constructor
    public ListadoEquiposFragmento() {
        listadoEquipos = new LinkedList<>();
    }
    //endregion

    //region Fragmento Base
    @Override
    public int asignarLayout() {
        return R.layout.fragmento_listado_equipos;
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) Objects.requireNonNull(getActivity()).getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void extras() {
        conductor = (String) Objects.requireNonNull(this.getArguments()).getSerializable(AlistamientoConstantes.EXTRA_CONDUCTOR);
        idAlistamiento = (String) this.getArguments().getSerializable(AlistamientoConstantes.EXTRA_ID_ALISTAMIENTO);
        tipoAlistamiento = (TipoAlistamientoEnum) this.getArguments().getSerializable(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO);
        fabricaAlistamiento = new AlmacenamientoFabrica().crear(tipoAlistamiento.toString());
    }

    @Override
    public void iniciarFragmento(View view, Bundle bundle) {
        ui();
        iniciarPresentador();
    }

    @Override
    public void ui() {
        Objects.requireNonNull(getActivity()).setTitle(fabricaAlistamiento.tituloToolbarListadoEquipos());
        adaptador = new ListadoEquiposAdaptador(getContext(), listadoEquipos, this);
        equiposRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        equiposRecycler.setAdapter(adaptador);
    }

    @Override
    public void iniciarPresentador() {
        usuarioLogueado = UsuarioSingleton.obtenerInstancia(getContext()).obtenerUsuario();
        presentador.establecerVista(this);
        presentador.consultarEquipos(String.valueOf(usuarioLogueado.getIdTaller()), tipoAlistamiento, idAlistamiento);
    }

    @Override
    public void tareaEnProceso() {
        ventanaProgreso = new DialogosHelper().mostrarDialogoProgreso(Objects.requireNonNull(getActivity()));
    }

    @Override
    public void tareaTerminada() {
        ventanaProgreso.dismiss();
    }
    //endregion

    @Override
    public void onDestroy() {
        Intent intencion = new Intent();
        intencion.putExtra(ConstantesCompartidas.codigoSeguridadAgregarEquipo, listadoEquipos.size());
        Objects.requireNonNull(this.getActivity()).setResult(Activity.RESULT_OK, intencion);
        super.onDestroy();
        presentador.destruir();
    }

    //region Propios

    private void actualizarAlistamiento() {
        int etapa;
        if (evaluarPermiteFinalizar())
            etapa = EtapasListadoAlistamientoEnum.FINALIZAR.getValue();
         else
            etapa = listadoEquipos.size() > 0 ? EtapasListadoAlistamientoEnum.EN_PROCESO.getValue() : EtapasListadoAlistamientoEnum.CREADA.getValue();
        HashMap<String, Object> mapPropiedades = new HashMap<>();
        mapPropiedades.put(obtenerRutaActualizacion(AlistamientoFirebase.PropiedadCantidadEquipos), listadoEquipos.size());
        mapPropiedades.put(obtenerRutaActualizacion(AlistamientoFirebase.PropiedadEtapa), etapa);
        presentador.actualizarPropiedadesAlistamiento(mapPropiedades);
    }

    private String obtenerRutaActualizacion(String propiedad) {
        if (tipoAlistamiento.equals(TipoAlistamientoEnum.Recepcion))
            return String.format(AlistamientoFirebase.ActualizarPropiedadAlistamiento, usuarioLogueado.getIdTaller(), idAlistamiento, propiedad);
        else
            return String.format(AlistamientoFirebase.ActualizarPropiedadAlistamientoDespacho, usuarioLogueado.getIdTaller(), idAlistamiento, propiedad);
    }

    private boolean evaluarPermiteFinalizar() {
        if (listadoEquipos.size() == 0) return false;

        for (EquipoLocalVM equipo : listadoEquipos) {
            if (EtapasEquipoEnum.valueOf(equipo.getEtapa()) == EtapasEquipoEnum.Creado)
                return false;
        }
        return true;
    }
    //endregion

    //region Contrato
    @Override
    public void respuestaConsultaEquipos(List<EquipoLocalVM> listaEquipos) {
        listadoEquipos.clear();
        listadoEquipos.addAll(listaEquipos);
        adaptador.notifyDataSetChanged();
        actualizarAlistamiento();
    }
    //endregion

    //region Eventos
    @Override
    public void seleccionEquipoOnClick(View v, int position) {
        EtapasEquipoEnum etapaEquipo = EtapasEquipoEnum.valueOf(listadoEquipos.get(position).getEtapa());
        if (etapaEquipo == EtapasEquipoEnum.Creado)
            mostrarBottomMenu(accionesEtapaCreada, position);
    }

    private void mostrarBottomMenu(int[] accionesEtapa, int position) {
        if (getFragmentManager() == null) return;

        ControlsBottomSheetDialog accionesDialog = ControlsBottomSheetDialog.obtenerInstancia(R.layout.plantilla_menu_bottom_equipos, accionesEtapa, this, position);
        accionesDialog.show(getFragmentManager(), "MenuEquipos");
    }

    @OnClick(R.id.fab_agregar_equipo)
    void agregarEquipoOnClick() {
        navegador.navegarDatosEquipoActividad(getContext(), conductor, idAlistamiento, tipoAlistamiento);
    }

    @Override
    public void onBottomSheetClick(int posicion, int itemSeleccionado) {
        switch (posicion) {
            case 0:
                if (listadoEquipos.size() > 0)
                    navegador.navegarListaChequeoActividad(this, listadoEquipos.get(itemSeleccionado), CodigosIntencion.CodigoIntencionListaChequeo, tipoAlistamiento);
                break;
            case 1:
                this.itemSeleccionado = itemSeleccionado;
                new DialogosHelper().mostrarDialogoOpciones(getActivity(), EquipoConstantes.ValidarEliminacionEquipo, dialogoConfirmacionEliminar);
                break;
            case 2:
                break;
        }
    }

    DialogInterface.OnClickListener dialogoConfirmacionEliminar = (dialog, which) -> {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            presentador.eliminarEquipo(listadoEquipos.get(itemSeleccionado).getIdAlistamiento(), listadoEquipos.get(itemSeleccionado).getPlaca(), tipoAlistamiento, usuarioLogueado.getIdTaller());
        }
    };
    //endregion
}
