package com.infotrack.talleres.presentacion.componentes.comunes.consultaequipo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.infotrack.artefactos.utilitarios.base.FragmentoBase;
import com.infotrack.artefactos.utilitarios.comunes.DialogosHelper;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoVM;
import com.infotrack.talleres.transversal.constantes.negocio.EquipoConstantes;
import com.infotrack.talleres.transversal.enumeradores.ProcesoEquipoEnum;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

public class ConsultaEquipoFragmento extends FragmentoBase implements ConsultaEquipoContrato {

    //region Atributos
    @Inject
    ConsultaEquipoPresentador presentador;

    @BindView(R.id.etConsultaPlaca)
    EditText etFiltroPlaca;
    @BindView(R.id.txtPlacaEquipo)
    TextView txtPlaca;
    @BindView(R.id.txt_clase_informacion_equipo)
    TextView txtClase;
    @BindView(R.id.txt_fabricante_informacion_equipo)
    TextView txtFabricante;
    @BindView(R.id.txt_capacidad_informacion_equipo)
    TextView txtCapacidad;
    @BindView(R.id.txt_centro_informacion_equipo)
    TextView txtCentro;

    private ConsultaEquipoCallback listener;
    private Activity contexto;
    private ProgressDialog ventanaProgreso;
    private String idUsuario;
    private ProcesoEquipoEnum procesoEquipo;
    //endregion

    //region Instancia
    public static ConsultaEquipoFragmento obtenerInstancia(ProcesoEquipoEnum procesoEquipo) {
        ConsultaEquipoFragmento fragmento = new ConsultaEquipoFragmento();
        Bundle argumentos = new Bundle();
        argumentos.putSerializable(EquipoConstantes.EXTRA_PROCESO_EQUIPO, procesoEquipo);
        fragmento.setArguments(argumentos);
        return fragmento;
    }
    //endregion

    //region Contrato Fragmento Base
    @Override
    public int asignarLayout() {
        return R.layout.fragmento_consulta_equipo;
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) Objects.requireNonNull(getActivity()).getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void extras() {
        idUsuario = UsuarioSingleton.obtenerInstancia(contexto).obtenerUsuario().getIdUsuario();
        procesoEquipo = (ProcesoEquipoEnum) (this.getArguments() != null ? this.getArguments().getSerializable(EquipoConstantes.EXTRA_PROCESO_EQUIPO) : null);
    }

    @Override
    public void iniciarFragmento(View view, Bundle bundle) {
        ui();
        iniciarPresentador();
    }

    @Override
    public void ui() {
        etFiltroPlaca.addTextChangedListener(watcherPlaca);
    }

    @Override
    public void iniciarPresentador() {
        presentador.establecerVista(this);
    }
    //endregion

    //region Sobreescitura
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.contexto = (Activity) context;
        this.listener = (ConsultaEquipoCallback) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presentador.establecerVista(null);
        presentador.destruir();
    }
    //endregion

    //region Propios
    private void evaluarEntrada(Editable s) {
        if (s.length() < 1 || s.charAt(s.length() - 1) != '\n') return;

        String query = s.toString().replaceAll("[\\r\\n]", "");
        if (!query.isEmpty())
            presentador.consultarEquipo(query, idUsuario, procesoEquipo);

        reiniciarControlFiltro();
    }

    private void reiniciarControlFiltro() {
        etFiltroPlaca.clearFocus();
        etFiltroPlaca.setText("");
    }
    //endregion

    //region Contrato Consulta Equipo
    @Override
    public void respuestaConsultaEquipo(EquipoVM equipo) {
        listener.retornoConsultaEquipo(equipo);

        txtPlaca.setText(equipo.getPlaca());
        txtClase.setText(equipo.getDescripcionClase());
        txtFabricante.setText(equipo.getFabricante());
        txtCapacidad.setText(String.valueOf(equipo.getCapacidad()));
        txtCentro.setText(equipo.getCentroPlanificador());

        etFiltroPlaca.clearFocus();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        new DialogosHelper().AlertaSimple(contexto, mensaje);
        listener.retornoConsultaEquipo(null);
    }

    @Override
    public void tareaEnProceso() {
        ventanaProgreso = new DialogosHelper().mostrarDialogoProgreso(contexto, "Consultando equipo");
    }

    @Override
    public void tareaTerminada() {
        if (ventanaProgreso != null && ventanaProgreso.isShowing())
            ventanaProgreso.dismiss();
    }
    //endregion

    //region Eventos
    TextWatcher watcherPlaca = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            evaluarEntrada(s);
        }
    };
    //endregion
}
