package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoalistamientos;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoLocalVM;
import com.infotrack.talleres.transversal.ControlsBottomSheetDialog;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.enumeradores.AccionesListadoAlistamientoEnum;
import com.infotrack.talleres.transversal.enumeradores.EtapasListadoAlistamientoEnum;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.infotrack.talleres.transversal.constantes.ConstantesCompartidas.BOTTOM_SHEET_FRAGMENTO;
import static com.infotrack.talleres.transversal.constantes.ConstantesCompartidas.TITULO_DIALOGOS_ERROR;

public class ListadoAlistamientoFragmento extends FragmentoBase
        implements ListadoAlistamientoContrato, ListadoAlistamientoAdaptador.ListadoAlistamientoCallback,
        ControlsBottomSheetDialog.AccionesBottomSheet {

    //region Atributos
    @Inject
    INavegador navegador;
    @Inject
    ListadoAlistamientoPresentador presentador;

    @BindView(R.id.recycler_lista_alistamiento)
    RecyclerView alistamientoRecycler;
    @BindView(R.id.fab_agregar_alistamiento)
    FloatingActionButton fabAgregarAlistamiento;

    private ListadoAlistamientoAdaptador adaptador;
    private List<AlistamientoLocalVM> listadoAlistamiento;
    private TipoAlistamientoEnum tipoAlistamiento;
    private AccionesTipoAlistamiento fabricaAlistamiento;
    private ProgressDialog ventanaProgreso;

    private int idTaller;
    private AlistamientoLocalVM alistamientoSeleccionado;

    int[] accionesCreada = new int[]{
            AccionesListadoAlistamientoEnum.LISTADO_EQUIPOS.getValue(),
            AccionesListadoAlistamientoEnum.ELIMINAR.getValue()
    };
    int[] accionesEnProceso = new int[]{
            AccionesListadoAlistamientoEnum.LISTADO_EQUIPOS.getValue()
//            AccionesListadoAlistamientoEnum.FINALIZAR.getValue()
    };
    int[] accionesFinalizar = new int[]{
            AccionesListadoAlistamientoEnum.LISTADO_EQUIPOS.getValue(),
            AccionesListadoAlistamientoEnum.FINALIZAR.getValue()
    };
    //endregion

    //region Instancia
    public static ListadoAlistamientoFragmento obtenerInstancia(TipoAlistamientoEnum tipoAlistamientoEnum) {
        ListadoAlistamientoFragmento fragmento = new ListadoAlistamientoFragmento();
        Bundle argumentos = new Bundle();
        argumentos.putSerializable(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamientoEnum);
        fragmento.setArguments(argumentos);
        return fragmento;
    }
    //endregion

    //region Constructor
    public ListadoAlistamientoFragmento() {
        listadoAlistamiento = new LinkedList<>();
    }
    //endregion

    //region Sobrecarga
    @Override
    public int asignarLayout() {
        return R.layout.fragmento_listado_alistamientos;
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) Objects.requireNonNull(getActivity()).getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void extras() {
        tipoAlistamiento = (TipoAlistamientoEnum) Objects.requireNonNull(this.getArguments()).getSerializable(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO);
        fabricaAlistamiento = new AlmacenamientoFabrica().crear(tipoAlistamiento.toString());
        idTaller = UsuarioSingleton.obtenerInstancia(getContext()).obtenerUsuario().getIdTaller();
    }

    @Override
    public void iniciarFragmento(View view, Bundle bundle) {
        ui();
        iniciarPresentador();
    }

    @Override
    public void ui() {
        Objects.requireNonNull(getActivity()).setTitle(fabricaAlistamiento.tituloToolbarListadoAlistamiento());
        adaptador = new ListadoAlistamientoAdaptador(getContext(), listadoAlistamiento, this);
        alistamientoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        alistamientoRecycler.setAdapter(adaptador);
    }

    @Override
    public void iniciarPresentador() {
        presentador.establecerVista(this);
        presentador.consultarListadoAlistamiento(String.valueOf(idTaller), tipoAlistamiento);
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

    //region Propios
    void desplegarToolbarAcciones(int[] accionesPorMostrar, int alistamientoSeleccionado) {
        ControlsBottomSheetDialog accionesDialog = ControlsBottomSheetDialog.obtenerInstancia(R.layout.plantilla_menu_bottom_alistamiento, accionesPorMostrar, this, alistamientoSeleccionado);
        accionesDialog.show(Objects.requireNonNull(getFragmentManager()), BOTTOM_SHEET_FRAGMENTO);
    }

    void navegarListadoEquipos(AlistamientoLocalVM alistamientoSeleccionado) {
        navegador.navegarListadoEquiposActividad(getContext(), alistamientoSeleccionado.getConductor(), alistamientoSeleccionado.getIdAlistamiento(), tipoAlistamiento);
    }

    void eliminarAlistamiento(AlistamientoLocalVM alistamientoSeleccionado) {
        if (alistamientoSeleccionado.getCantidadEquipos() > 0) {
            new DialogosHelper().AlertaSimple(Objects.requireNonNull(getActivity()), AlistamientoConstantes.eliminacionNoPosible);
        } else {
            new DialogosHelper().mostrarDialogoOpciones(getActivity(), AlistamientoConstantes.mensajeConfirmacionEliminacionAlistamiento, dialogoConfirmacionEliminacion);
        }
    }

    void navegarFirmaActividad(AlistamientoLocalVM alistamientoSeleccionado) {
//        if (alistamientoSeleccionado.isPermiteFinalizar()) {
        navegador.navegarFirmaAlistamiento(getContext(), alistamientoSeleccionado, tipoAlistamiento);
//        } else {
//            new DialogosHelper().AlertaSimple(Objects.requireNonNull(getActivity()), AlistamientoConstantes.noPuedeFinalizar);
//        }
    }
    //endregion

    //region Contrato
    @Override
    public void actualizarListadoAlistamiento(List<AlistamientoLocalVM> listaAlistamiento) {
        listadoAlistamiento.clear();
        listadoAlistamiento.addAll(listaAlistamiento);
        adaptador.notifyDataSetChanged();
    }

    @Override
    public void monstrarMensajesError(String mensaje) {
        new DialogosHelper().AlertaSimple(Objects.requireNonNull(getActivity()), mensaje, TITULO_DIALOGOS_ERROR);
    }
    //endregion

    //region Eventos
    @OnClick(R.id.fab_agregar_alistamiento)
    public void clickAgregarAlistamiento() {
        navegador.navegarInicioAlistamientoActividad(getContext(), tipoAlistamiento);
    }

    DialogInterface.OnClickListener dialogoConfirmacionEliminacion = (dialog, which) -> {
        if (which == DialogInterface.BUTTON_POSITIVE)
            presentador.eliminarAlistamiento(String.valueOf(idTaller), alistamientoSeleccionado.getIdAlistamiento(), tipoAlistamiento);
        else
            dialog.dismiss();
    };
    //endregion

    //region Callbacks
    @Override
    public void onBottomSheetClick(int posicion, int itemSeleccionado) {
        AlistamientoLocalVM alistamientoSeleccionado = listadoAlistamiento.get(itemSeleccionado);
        switch (AccionesListadoAlistamientoEnum.valueOf(posicion)) {
            case LISTADO_EQUIPOS:
                navegarListadoEquipos(alistamientoSeleccionado);
                break;
            case ELIMINAR:
                eliminarAlistamiento(alistamientoSeleccionado);
                break;
            case FINALIZAR:
                navegarFirmaActividad(alistamientoSeleccionado);
                break;
        }
    }

    @Override
    public void seleccionAlistamientoOnClick(View v, int position) {
        alistamientoSeleccionado = listadoAlistamiento.get(position);
        EtapasListadoAlistamientoEnum etapaAlistamiento = EtapasListadoAlistamientoEnum.valueOf(listadoAlistamiento.get(position).getEtapa());
        if (etapaAlistamiento == EtapasListadoAlistamientoEnum.FINALIZAR)
            desplegarToolbarAcciones(accionesFinalizar, position);
        else
            desplegarToolbarAcciones(etapaAlistamiento == EtapasListadoAlistamientoEnum.CREADA ? accionesCreada : accionesEnProceso, position);
    }
    //endregion
}
