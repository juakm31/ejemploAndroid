package com.infotrack.talleres.presentacion.componentes.alistamiento.crear;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.infotrack.artefactos.utilitarios.base.FragmentoBase;
import com.infotrack.artefactos.utilitarios.comunes.DialogosHelper;
import com.infotrack.artefactos.utilitarios.comunes.FechasHelper;
import com.infotrack.talleres.R;
import com.infotrack.talleres.datos.entidades.AlistamientoDto;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.componentes.alistamiento.fabrica.AccionesTipoAlistamiento;
import com.infotrack.talleres.presentacion.componentes.alistamiento.fabrica.AlmacenamientoFabrica;
import com.infotrack.talleres.presentacion.vistamodelos.TransportadorVM;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.constantes.negocio.TransportadorConstantes;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.infotrack.talleres.transversal.constantes.ConstantesCompartidas.TITULO_DIALOGOS_ERROR;

public class CrearAlistamientoFragmento extends FragmentoBase implements CrearAlistamientoContrato {

    //region Atributos
    @Inject
    CrearAlistamientoPresentador presentador;

    @BindView(R.id.et_consulta_placa_vehiculo)
    EditText etPlacaVehiculo;
    @BindView(R.id.txt_placa_vehiculo)
    TextView txtPlacaVehiculo;
    @BindView(R.id.txt_transportadora_inicio_recepcion)
    TextView txtTransportadora;
    @BindView(R.id.txt_conductor_inicio_recepcion)
    TextView txtConductor;
    @BindView(R.id.edt_observaciones_inicio_recepcion)
    EditText edtObservacion;

    private TipoAlistamientoEnum tipoAlistamiento;
    private AccionesTipoAlistamiento fabricaAlistamiento;
    private TransportadorVM transportadorVM;
    private UsuarioAutenticadoVM usuario;
    private ProgressDialog ventanaProgreso;
    //endregion

    //region Instancia
    public static CrearAlistamientoFragmento obtenerInstancia(TipoAlistamientoEnum tipoAlistamientoEnum) {
        CrearAlistamientoFragmento fragmento = new CrearAlistamientoFragmento();
        Bundle argumentos = new Bundle();
        argumentos.putSerializable(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamientoEnum);
        fragmento.setArguments(argumentos);
        return fragmento;
    }
    //endregion

    //region Sobrecargas
    @Override
    public int asignarLayout() {
        return R.layout.fragmento_crear_alistamiento;
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) Objects.requireNonNull(getActivity()).getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void extras() {
        usuario = UsuarioSingleton.obtenerInstancia(getContext()).obtenerUsuario();
        tipoAlistamiento = (TipoAlistamientoEnum) Objects.requireNonNull(this.getArguments()).getSerializable(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO);
        fabricaAlistamiento = new AlmacenamientoFabrica().crear(tipoAlistamiento.toString());
    }

    @Override
    public void iniciarFragmento(View view, Bundle bundle) {
        ui();
        iniciarPresentador();
    }

    @Override
    public void ui() {
        etPlacaVehiculo.addTextChangedListener(watcherPlaca);
        Objects.requireNonNull(getActivity()).setTitle(fabricaAlistamiento.tituloToolbarInicioAlistamiento());
    }

    @Override
    public void iniciarPresentador() {
        presentador.establecerVista(this);
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
    private boolean validarCamposLLenos() {
        if (txtTransportadora.getText().toString().isEmpty()) {
            new DialogosHelper().AlertaSimple(Objects.requireNonNull(getActivity()), TransportadorConstantes.camposVacios);
            return false;
        }
        return true;
    }

    private AlistamientoDto llenarAlistamientoDto() {
        AlistamientoDto alistamientoDto = new AlistamientoDto();
        alistamientoDto.setIdTaller(usuario.getIdTaller());
        alistamientoDto.setPlaca(transportadorVM.getPlaca());
        alistamientoDto.setConductor(txtConductor.getText().toString());
        alistamientoDto.setIdConductor(transportadorVM.getIdConductor());
        alistamientoDto.setTransportadora(transportadorVM.getTransportadora());
        alistamientoDto.setIdTransportador(transportadorVM.getIdTransportadora());
        alistamientoDto.setFechaInicio(FechasHelper.obtenerinstancia().obtieneFechaSistemaConFormato(ConstantesCompartidas.formatoFechajson));
        alistamientoDto.setObservaciones(edtObservacion.getText().toString());
        alistamientoDto.setIdTaller(usuario.getIdTaller());
        alistamientoDto.setIdAlistamiento("");
        return alistamientoDto;
    }
    //endregion

    //region Contrato
    @Override
    public void llenarFormularioTransportador(TransportadorVM transportadorVMRespuesta) {
        transportadorVM = transportadorVMRespuesta;
        txtPlacaVehiculo.setText(transportadorVMRespuesta.getPlaca());
        txtTransportadora.setText(transportadorVMRespuesta.getTransportadora());
        txtConductor.setText(transportadorVMRespuesta.getConductor());
        edtObservacion.requestFocus();
    }

    @Override
    public void monstrarMensajesError(String mensaje) {
        new DialogosHelper().AlertaSimple(Objects.requireNonNull(getActivity()), mensaje, TITULO_DIALOGOS_ERROR);
    }

    @Override
    public void finalizarActividad() {
        Objects.requireNonNull(getActivity()).finish();
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
            if (s.length() > 0) {
                char lastCharacter = s.charAt(s.length() - 1);

                if (lastCharacter == '\n') {
                    String query = s.toString().replaceAll("[\\r\\n]", "");

                    if (!query.isEmpty())
                        presentador.consultarVehiculoPorPlaca(query, tipoAlistamiento);

                    reiniciarControlFiltro();
                }
            }
        }
    };

    private void reiniciarControlFiltro() {
        etPlacaVehiculo.setText("");
    }

    @OnClick(R.id.btn_iniciar_recepcion)
    public void iniciarRecepcionClick() {
        if (validarCamposLLenos()) {
            AlistamientoDto alistamientoDto = llenarAlistamientoDto();
            presentador.crearAlistamiento(alistamientoDto, tipoAlistamiento, usuario);
        }
    }
    //endregion
}
