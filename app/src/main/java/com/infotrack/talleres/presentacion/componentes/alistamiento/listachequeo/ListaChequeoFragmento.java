package com.infotrack.talleres.presentacion.componentes.alistamiento.listachequeo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.infotrack.artefactolistachequeo.DTO.ListaChequeoViewModel;
import com.infotrack.artefactolistachequeo.DTO.PreguntaViewModel;
import com.infotrack.artefactolistachequeo.DTO.RespuestaViewModel;
import com.infotrack.artefactolistachequeo.DTO.SeccionViewModel;
import com.infotrack.artefactolistachequeo.ListaChequeo.ListaDeChequeoControl;
import com.infotrack.artefactolistachequeo.TiposRespuesta.ListaChequeoCallBack;
import com.infotrack.artefactos.utilitarios.base.FragmentoBase;
import com.infotrack.artefactos.utilitarios.comunes.DialogosHelper;
import com.infotrack.artefactos.utilitarios.comunes.FechasHelper;
import com.infotrack.talleres.R;
import com.infotrack.talleres.presentacion.base.AplicacionPrincipal;
import com.infotrack.talleres.presentacion.vistamodelos.ArchivoChecklist;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoResVM;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.transversal.archivos.AlmacenarArchivoHelper;
import com.infotrack.talleres.transversal.constantes.CodigosIntencion;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;
import com.infotrack.talleres.transversal.constantes.negocio.AlistamientoConstantes;
import com.infotrack.talleres.transversal.constantes.negocio.ListaChequeoConstantes;
import com.infotrack.talleres.transversal.enumeradores.EtapasChecklistEnum;
import com.infotrack.talleres.transversal.enumeradores.EtapasEquipoEnum;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;
import com.infotrack.talleres.transversal.singletons.UsuarioSingleton;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import infotrack.artefactos.galeria.FotoDto;
import infotrack.artefactos.galeria.GaleriaActivity;

public class ListaChequeoFragmento extends FragmentoBase implements
        ListaChequeoCallBack,
        ListaChequeoContrato {

    //region Atributos
    @Inject
    ListaChequeoPresentador presentador;

    @BindView(R.id.listaChequeo)
    ListaDeChequeoControl listaChequeo;

    private ProgressDialog ventanaProgreso;

    private TipoAlistamientoEnum tipoAlistamiento;

    private Activity actividad;

    private PreguntaViewModel pregunta;
    private EquipoLocalVM equipoLocal;
    private String tagBotonCamara;

    private UsuarioAutenticadoVM usuarioLogueado;
    //endregion

    //region Instancia
    public static ListaChequeoFragmento obtenerInstancia(EquipoLocalVM equipoLocal, TipoAlistamientoEnum tipoAlistamiento) {
        ListaChequeoFragmento fragmento = new ListaChequeoFragmento();
        Bundle argumentos = new Bundle();
        argumentos.putSerializable(ListaChequeoConstantes.EXTRA_EQUIPO_LOCAL, equipoLocal);
        argumentos.putSerializable(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO, tipoAlistamiento);
        fragmento.setArguments(argumentos);
        return fragmento;
    }
    //endregion

    //region Fragmento Base Contrato
    @Override
    public int asignarLayout() {
        return R.layout.fragmento_lista_chequeo;
    }

    @Override
    public void iniciarInyector() {
        AplicacionPrincipal app = (AplicacionPrincipal) this.actividad.getApplication();
        app.obtenerComponentePrincipal().inject(this);
    }

    @Override
    public void extras() {
        if (getArguments() != null) {
            equipoLocal = (EquipoLocalVM) getArguments().getSerializable(ListaChequeoConstantes.EXTRA_EQUIPO_LOCAL);
            tipoAlistamiento = (TipoAlistamientoEnum) this.getArguments().getSerializable(AlistamientoConstantes.EXTRA_TIPO_ALISTAMIENTO);
            usuarioLogueado = UsuarioSingleton.obtenerInstancia(getContext()).obtenerUsuario();
        }
    }

    @Override
    public void iniciarFragmento(View view, Bundle bundle) {
        iniciarPresentador();
        ui();
    }

    @Override
    public void ui() {
        listaChequeo.setListaChequeoCallBack(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void iniciarPresentador() {
        presentador.establecerVista(this);
        presentador.setTipoAlistamiento(tipoAlistamiento);
        presentador.consultarListaChequeo(
                equipoLocal.getIdAlistamiento(),
                equipoLocal.getPlaca(),
                String.valueOf(equipoLocal.getIdClase())
        );
    }
    //endregion

    //region Fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        actividad = (Activity) context;
    }

    @Override
    public void onPause() {
        super.onPause();
        presentador.persistirListaChequeo(
                equipoLocal.getIdAlistamiento(),
                equipoLocal.getPlaca(),
                listaChequeo.obtenerInformacionCompleta()
        );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_navegacion_guardar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_guardar) {
            if (listaChequeo.validaListaDeChequeo() && validaListaChequeoNegocio()) {
                EtapasChecklistEnum etapasChecklist = EtapasChecklistEnum.valueOf(equipoLocal.getEtapaChecklist());
                if (etapasChecklist == EtapasChecklistEnum.Exitoso)
                    new DialogosHelper().mostrarDialogoOpciones(actividad, ListaChequeoConstantes.DialogoPreguntaMantenimiento, dialogoMantenimiento);
                else {
                    consumirServicioEquipo(etapasChecklist == EtapasChecklistEnum.Higiene ? EtapasEquipoEnum.Higiene.getValue() : EtapasEquipoEnum.Baja.getValue());
                }
            } else {
                Toast.makeText(actividad, listaChequeo.obtenerMensajeRespuestaValidacion(), Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void consumirServicioEquipo(int etapaFinal) {
        equipoLocal.setEtapa(etapaFinal);
        equipoLocal.setDatosListaChequeo((new Gson()).toJson(listaChequeo.obtenerInformacionCompleta()));
        EquipoResVM equipoRes = new EquipoResVM(equipoLocal);
        presentador.enviarEquipoREST(equipoRes, tipoAlistamiento, usuarioLogueado.getIdTaller(), equipoLocal);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presentador.destruir();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CodigosIntencion.CodigoIntencionGaleria) {
                administraRetornoGaleriaPlanilla(data.getParcelableArrayListExtra(GaleriaActivity.EXTRA_LISTA_FOTO_GALERIA));
            }
        }
    }
    //endregion

    //region Propios
    private void administraRetornoGaleriaPlanilla(ArrayList<FotoDto> listaFotos) {
        ImageButton btn = actividad.getWindow().getDecorView().findViewWithTag(tagBotonCamara);

        boolean tieneFotos = listaFotos.size() > 0;
        btn.setBackgroundColor(tieneFotos ? getResources().getColor(R.color.colorPrimary) : Color.LTGRAY);
        this.pregunta.setRutaFoto(tieneFotos ? listaFotos.get(0).getUrl() : "");
    }

    private void iniciarComponenteGaleria(String identificadorItemChecklist) {
        String directorio = String.format("%s/%s_%s", ConstantesCompartidas.DIRECTORIO_BASE_IMAGENES, equipoLocal.getPlaca(), identificadorItemChecklist);
        startActivityForResult(
                GaleriaActivity.obtenerIntent(
                        getContext(),
                        directorio,
                        1,
                        1
                ), CodigosIntencion.CodigoIntencionGaleria);
    }

    private void almacenarImagenesChecklist() {
        List<ArchivoChecklist> archivosImagen = obtenerListaArchivos();
        persistirArchivos(archivosImagen);
    }

    private void persistirArchivos(List<ArchivoChecklist> archivosImagen) {
        AlmacenarArchivoHelper helper;
        try {
            helper = AlmacenarArchivoHelper.obtenerInstancia();
            for (ArchivoChecklist archivo : archivosImagen)
                helper.almacenar(archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<ArchivoChecklist> obtenerListaArchivos() {
        List<ArchivoChecklist> listaArchivos = new ArrayList<>();
        for (SeccionViewModel seccion : listaChequeo.obtenerInformacionCompleta().getSections()) {
            for (PreguntaViewModel pregunta : seccion.getQuestions()) {
                if (pregunta.getValorOpcionNoAplica() == null || !pregunta.getValorOpcionNoAplica()) {
                    evaluarCapturaImagen(pregunta, listaArchivos);
                }
            }
        }
        return listaArchivos;
    }

    private void evaluarCapturaImagen(PreguntaViewModel pregunta, List<ArchivoChecklist> listaArchivos) {
        if (pregunta.getRutaFoto() == null || pregunta.getRutaFoto().isEmpty()) return;

        ArchivoChecklist archivoChecklist = new ArchivoChecklist();
        archivoChecklist.setFechaArchivo(obtenerFechaArchivo(pregunta.getRutaFoto()));
        archivoChecklist.setNombreArchivo("");
        archivoChecklist.setMetadatos(obtenerMetadatos());
        archivoChecklist.setIdUsuario(usuarioLogueado.getIdUsuario());
        archivoChecklist.setRutaLocal(pregunta.getRutaFoto());

        listaArchivos.add(archivoChecklist);
    }

    private HashMap<String, String> obtenerMetadatos() {
        HashMap<String, String> metadatos = new HashMap<>();
        /*metadatos.put(Constantes.META_NOMBRE_AGENCIA, sucursal);
        metadatos.put(Constantes.META_ORDEN_SERVICIO_ID, ordenServicioId);
        metadatos.put(Constantes.META_ORDEN_SERVICIO_NUMERO, ordenServicioNumero);
        metadatos.put(Constantes.META_TIPO_IMAGEN, tipoImagen);*/
        return metadatos;
    }

    private String obtenerFechaArchivo(String rutaArchivo){
        Date fechaModificacion = new Date(new File(rutaArchivo).lastModified());
        return FechasHelper.obtenerinstancia().formatearFecha(fechaModificacion, ConstantesCompartidas.FORMATO_FECHA_YMD_HMS);
    }
    //endregion

    //region Componente Lista Chequeo Contrato
    @Override
    public void onClickCamara(View view, PreguntaViewModel preguntaViewModel) {
        this.tagBotonCamara = view.getTag().toString();
        this.pregunta = preguntaViewModel;
        iniciarComponenteGaleria(String.valueOf(preguntaViewModel.getId()));
    }

    @Override
    public boolean validaListaChequeoNegocio() {
        ListaChequeoViewModel modelo = listaChequeo.obtenerInformacionCompleta();
        boolean validacion = true;

        for (SeccionViewModel seccion : modelo.getSections()) {
            for (PreguntaViewModel pregunta : seccion.getQuestions()) {
                if (pregunta.getValorOpcionNoAplica() == null || !pregunta.getValorOpcionNoAplica()) {

                    int posicionRespuesta = 0;
                    int valorCantidadIngresada = 0;
                    int valorCantidadMalEstado = 0;

                    for (RespuestaViewModel respuesta : pregunta.getAnswers()) {

                        if (posicionRespuesta == 0)
                            valorCantidadIngresada = Integer.parseInt(respuesta.getValorRespuesta() != null && !respuesta.getValorRespuesta().equals("") ? respuesta.getValorRespuesta() : "0");

                        if (respuesta.getText().equals(ConstantesCompartidas.VALIDADOR_NOMBRE_ELEMENTO)) {

                            valorCantidadMalEstado = Integer.parseInt(respuesta.getValorRespuesta() != null && !respuesta.getValorRespuesta().equals("") ? respuesta.getValorRespuesta() : "0");

                            /*Se debe validar que la cantidad en mal estado no sea mayor que la cantidad de componentes ingresada*/
                            if (valorCantidadIngresada < valorCantidadMalEstado) {
                                validacion = false;
                                listaChequeo.setMensajeRespuestaValidacion(seccion.getName(), pregunta.getOrder() + " la cantidad en mal estado no puede ser mayor a la cantidad de componentes ingresados.");
                            }

                            if (respuesta.getValorRespuesta() != null && !respuesta.getValorRespuesta().equals("")) {
                                if (Integer.parseInt(respuesta.getValorRespuesta()) > 0 && (pregunta.getRutaFoto() == null || pregunta.getRutaFoto().equals(""))) {
                                    validacion = false;
                                    listaChequeo.setMensajeRespuestaValidacion(seccion.getName(), pregunta.getOrder() + " se debe tomar una foto");
                                }
                            }
                        }
                        posicionRespuesta += 1;
                        //totalElementosMalEstado = totalElementosMalEstado + valorCantidadMalEstado;

                    }
                }
            }
        }
        return validacion;
    }
    //endregion

    //region Lista Chequeo Contrato
    @Override
    public void tareaEnProceso() {
        ventanaProgreso = new DialogosHelper().mostrarDialogoProgreso(actividad);
    }

    @Override
    public void tareaTerminada() {
        ventanaProgreso.dismiss();
    }

    @Override
    public void respuestaListaChequeo(String json) {
        listaChequeo.setJsonDatosDeEntrada(json);
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        new DialogosHelper().AlertaSimple(actividad, mensaje);
    }

    @Override
    public void finalizarActividad() {
        actividad.finish();
    }
    //endregion

    //region Eventos
    DialogInterface.OnClickListener dialogoMantenimiento = (dialog, which) -> {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                consumirServicioEquipo(EtapasEquipoEnum.RequiereMantenimiento.getValue());
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                consumirServicioEquipo(EtapasEquipoEnum.Exitoso.getValue());
                break;
        }
    };
    //endregion
}
