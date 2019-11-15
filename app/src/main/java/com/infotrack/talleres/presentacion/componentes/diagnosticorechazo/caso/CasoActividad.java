package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.caso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.artefactos.utilitarios.comunes.DialogosHelper;
import com.infotrack.artefactos.utilitarios.comunes.FechasHelper;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.componentes.comunes.consultaequipo.ConsultaEquipoCallback;
import com.infotrack.talleres.presentacion.componentes.comunes.consultaequipo.ConsultaEquipoFragmento;
import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.fabrica.AccionesDiagnosticoRechazo;
import com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.fabrica.DiagnosticoRechazoFabrica;
import com.infotrack.talleres.presentacion.vistamodelos.CasoVM;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoVM;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;
import com.infotrack.talleres.transversal.constantes.negocio.CasoConstantes;
import com.infotrack.talleres.transversal.enumeradores.EtapasListadoDiagnosticoRechazoEnum;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class CasoActividad extends ActividadBase
        implements CasoContrato,
        ConsultaEquipoCallback, CasoFabricaCallback {

    //region Atributos
    @Inject
    CasoPresentador presentador;

    @BindView(R.id.switchBaja)
    Switch switchBaja;
    @BindView(R.id.switchHigiene)
    Switch switchHigiene;
    @BindView(R.id.card_observacion_informacion_equipo)
    CardView cardObservacion;
    @BindView(R.id.edt_observaciones_informacion_equipo)
    EditText edtObservacion;
    @BindView(R.id.contenedorObservaciones)
    LinearLayout seccionObservaciones;

    private EquipoVM equipoConsulta;
    private TipoDiagnosticoRechazoEnum tipoDiagnostico;
    private UsuarioAutenticadoVM usuarioLogueado;
    private AccionesDiagnosticoRechazo accionesDiagnosticoRechazo;
    //endregion

    //region Navegador
    public static Intent obtenerIntencion(Context contexto, TipoDiagnosticoRechazoEnum tipoDiagnostico) {
        Intent intencion = new Intent(contexto, CasoActividad.class);
        intencion.putExtra(CasoConstantes.EXTRA_TIPO_DIAGNOSTICO, tipoDiagnostico);
        return intencion;
    }
    //endregion

    //region Actividad Base
    @Override
    public int asignarLayout() {
        return R.layout.actividad_caso;
    }

    @Override
    public void extras() {
        usuarioLogueado = UsuarioSingleton.obtenerInstancia(this).obtenerUsuario();
        tipoDiagnostico = (TipoDiagnosticoRechazoEnum) getIntent().getSerializableExtra(CasoConstantes.EXTRA_TIPO_DIAGNOSTICO);
        accionesDiagnosticoRechazo = new DiagnosticoRechazoFabrica().crear(tipoDiagnostico.toString());
    }

    @Override
    public void iniciarActividad(Bundle bundle) {
        ui();
        iniciarPresentador();
    }

    @Override
    public void iniciarInyector() {
        super.iniciarInyector();
    }

    @Override
    public void ui() {
        super.ui();
        accionesDiagnosticoRechazo.configurarTarjetaObservaciones(this);
        inyectarFragmento();
    }

    private void inyectarFragmento() {
        Fragment fragmento = getSupportFragmentManager().findFragmentById(R.id.contenedorConsulta);
        if (fragmento == null) {
            fragmento = ConsultaEquipoFragmento.obtenerInstancia(tipoDiagnostico.getProcesoEquipo());
            agregarFragmento(R.id.contenedorConsulta, fragmento);
        }
    }

    @Override
    public void iniciarPresentador() {
        super.iniciarPresentador();
        presentador.establecerVista(this);
    }
    //endregion

    //region Contrato Presentador
    @Override
    public void tareaEnProceso() {

    }

    @Override
    public void tareaTerminada() {

    }

    @Override
    public void finalizarActividad() {
        this.finish();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        new DialogosHelper().AlertaSimple(this, mensaje);
    }
    //endregion

    //region Contrato Consulta Equipo
    @Override
    public void retornoConsultaEquipo(EquipoVM equipo) {
        this.equipoConsulta = equipo;
    }
    //endregion

    //region Propios
    private void evaluarEstadoObservacion() {
        boolean any = switchBaja.isChecked() || switchHigiene.isChecked();
        edtObservacion.setEnabled(any);
        if (!any) edtObservacion.setText("");
    }

    private boolean validarCampos() {
        if (equipoConsulta == null) {
            new DialogosHelper().AlertaSimple(this, CasoConstantes.requiereConsultaPlaca);
            return false;
        }

        return true;
    }

    private CasoVM obtenerInstanciaCaso() {
        CasoVM caso = new CasoVM();
        caso.setCapacidad(equipoConsulta.getCapacidad());
        caso.setCentroplanificador(equipoConsulta.getCentroPlanificador());
        caso.setClase(equipoConsulta.getDescripcionClase());
        caso.setEtapa(EtapasListadoDiagnosticoRechazoEnum.CREADO.getValue());
        caso.setFabricante(equipoConsulta.getFabricante());
        caso.setFechaIngreso(new FechasHelper().obtieneFechaSistemaConFormato(ConstantesCompartidas.formatoFecha));
        caso.setIdCaso(String.valueOf(equipoConsulta.getIdCaso()));
        caso.setIdClase(equipoConsulta.getClase());
        caso.setIdTecnico(usuarioLogueado.getIdUsuario());
        caso.setPlaca(equipoConsulta.getPlaca());
        caso.setTecnico(usuarioLogueado.getNombreUsuario());
        return caso;
    }
    //endregion

    //region Eventos
    @OnCheckedChanged({R.id.switchBaja})
    void bajaOnSwitchChanged() {
        if (switchBaja.isChecked())
            switchHigiene.setChecked(false);
        evaluarEstadoObservacion();
    }

    @OnCheckedChanged(R.id.switchHigiene)
    void higieneOnSwitchChanged() {
        if (switchHigiene.isChecked())
            switchBaja.setChecked(false);
        evaluarEstadoObservacion();
    }

    @OnClick(R.id.btn_agregar_equipo)
    void agregarOnClick() {
        if (validarCampos()) {
            presentador.persistirCaso(String.valueOf(usuarioLogueado.getIdTaller()), obtenerInstanciaCaso(), tipoDiagnostico);
        }
    }
    //endregion

    //region Contrato Fabrica
    @Override
    public void permitirObservaciones(boolean permitir) {
        seccionObservaciones.setVisibility(permitir ? View.VISIBLE : View.GONE);
    }
    //endregion
}
