package com.infotrack.talleres.presentacion.componentes.alistamiento.datosequipo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.Switch;

import com.infotrack.artefactos.utilitarios.base.ActividadBase;
import com.infotrack.artefactos.utilitarios.comunes.DialogosHelper;
import com.infotrack.artefactos.utilitarios.comunes.FechasHelper;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.componentes.comunes.consultaequipo.ConsultaEquipoCallback;
import com.infotrack.talleres.presentacion.componentes.comunes.consultaequipo.ConsultaEquipoFragmento;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoVM;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.enumeradores.EtapasChecklistEnum;
import com.infotrack.talleres.transversal.enumeradores.EtapasEquipoEnum;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class DatosEquipoActividad extends ActividadBase
        implements ConsultaEquipoCallback,
        DatosEquipoContrato {

    @Inject
    DatosEquipoPresentador presentador;

    @BindView(R.id.switchBaja)
    Switch switchBaja;
    @BindView(R.id.switchHigiene)
    Switch switchHigiene;
    @BindView(R.id.card_observacion_informacion_equipo)
    CardView cardObservacion;
    @BindView(R.id.edt_observaciones_informacion_equipo)
    EditText edtObservacion;

    private EquipoVM equipoRespuesta;
    private TipoAlistamientoEnum tipoAlistamiento;
    private String idAlistamiento;
    private String conductor;
    private UsuarioAutenticadoVM usuarioLogueado;

    public static Intent obtenerIntencion(Context contexto, String conductor, String idAlistamiento, TipoAlistamientoEnum tipoAlistamientoEnum) {
        Intent intent = new Intent(contexto, DatosEquipoActividad.class);
        intent.putExtra(AlistamientoConstantes.EXTRA_CONDUCTOR, conductor);
        intent.putExtra(AlistamientoConstantes.EXTRA_ID_ALISTAMIENTO, idAlistamiento);
        intent.putExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamientoEnum);
        return intent;
    }

    @Override
    public int asignarLayout() {
        return R.layout.actividad_datos_equipo;
    }

    @Override
    public void extras() {
        super.extras();
        usuarioLogueado = UsuarioSingleton.obtenerInstancia(this).obtenerUsuario();
        conductor = (String) this.getIntent().getSerializableExtra(AlistamientoConstantes.EXTRA_CONDUCTOR);
        idAlistamiento = (String) this.getIntent().getSerializableExtra(AlistamientoConstantes.EXTRA_ID_ALISTAMIENTO);
        tipoAlistamiento = (TipoAlistamientoEnum) this.getIntent().getSerializableExtra(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO);
    }

    @Override
    public void iniciarActividad(Bundle bundle) {
        iniciarPresentador();
        Fragment fragmento = getSupportFragmentManager().findFragmentById(R.id.contenedorConsulta);
        if (fragmento == null) {
            fragmento = ConsultaEquipoFragmento.obtenerInstancia(tipoAlistamiento.getProcesoEquipo());
            agregarFragmento(R.id.contenedorConsulta, fragmento);
        }
    }

    @Override
    public void iniciarPresentador() {
        presentador.establecerVista(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presentador.establecerVista(null);
        presentador.destruir();
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal aplicacionPrincipal = (AplicacionPrincipal) this.getApplication();
        aplicacionPrincipal.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public boolean usarBarraNavegacion() {
        return false;
    }

    @OnClick(R.id.btn_agregar_equipo)
    public void agregarEquipo() {
        EtapasChecklistEnum etapaChecklist = obtenerEtapaChecklist();
        if (validarCampos(etapaChecklist)) {
            presentador.persistirEquipo(
                    obtenerInstanciaEquipo(etapaChecklist.getValue()),
                    tipoAlistamiento,
                    usuarioLogueado.getIdTaller()
            );
        }
    }

    @OnCheckedChanged({R.id.switchBaja})
    public void bajaOnSwitchChanged() {
        if (switchBaja.isChecked())
            switchHigiene.setChecked(false);
        evaluarEstadoObservacion();
    }

    private void evaluarEstadoObservacion() {
        boolean any = switchBaja.isChecked() || switchHigiene.isChecked();
        edtObservacion.setEnabled(any);
        if (!any) edtObservacion.setText("");
    }

    @OnCheckedChanged(R.id.switchHigiene)
    public void higieneOnSwitchChanged() {
        if (switchHigiene.isChecked())
            switchBaja.setChecked(false);
        evaluarEstadoObservacion();
    }

    private EquipoLocalVM obtenerInstanciaEquipo(int etapaListaChequeo) {
        EquipoLocalVM equipo = new EquipoLocalVM();
        equipo.setPlaca(equipoRespuesta.getPlaca());
        equipo.setClase(equipoRespuesta.getDescripcionClase());
        equipo.setCapacidad(equipoRespuesta.getCapacidad());
        equipo.setFabricante(equipoRespuesta.getFabricante());
        equipo.setCentroplanificador(equipoRespuesta.getCentroPlanificador());
        equipo.setIdClase(equipoRespuesta.getClase());
        equipo.setIdAlistamiento(idAlistamiento);
        equipo.setIdPersona(usuarioLogueado.getIdUsuario());
        equipo.setFechaIngreso(FechasHelper.obtenerinstancia().obtieneFechaSistemaConFormato(ConstantesCompartidas.formatoFecha));
        equipo.setConductor(conductor);
        equipo.setDatosListaChequeo("");
        equipo.setObservaciones(edtObservacion.getText().toString());
        equipo.setCreador(usuarioLogueado.getNombreUsuario());
        equipo.setEtapa(EtapasEquipoEnum.Creado.getValue());
        equipo.setEtapaChecklist(etapaListaChequeo);
        return equipo;
    }


    private boolean validarCampos(EtapasChecklistEnum etapaChecklist) {
        if (equipoRespuesta == null) {
            new DialogosHelper().AlertaSimple(this, AlistamientoConstantes.requiereConsultaPlaca);
            return false;
        }
        if (etapaChecklist != EtapasChecklistEnum.Exitoso && edtObservacion.getText().toString().isEmpty()) {
            new DialogosHelper().AlertaSimple(this, AlistamientoConstantes.campoObservacionVacio);
            return false;
        }
        return true;
    }

    private EtapasChecklistEnum obtenerEtapaChecklist() {
        EtapasChecklistEnum etapaChecklist = EtapasChecklistEnum.Exitoso;
        if (switchHigiene.isChecked())
            etapaChecklist = EtapasChecklistEnum.Higiene;
        if (switchBaja.isChecked())
            etapaChecklist = EtapasChecklistEnum.Baja;
        return etapaChecklist;
    }

    @Override
    public void retornoConsultaEquipo(EquipoVM equipo) {
        this.equipoRespuesta = equipo;
    }

    @Override
    public void finalizarActividad() {
        this.finish();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        new DialogosHelper().AlertaSimple(this, mensaje);
    }

    @Override
    public void tareaEnProceso() {

    }

    @Override
    public void tareaTerminada() {

    }
}
