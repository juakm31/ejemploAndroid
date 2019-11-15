package com.infotrack.talleres.presentacion.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.infotrack.artefactos.utilitarios.base.FragmentoBase;
import com.infotrack.artefactos.utilitarios.base.NavegadorAbstracto;
import com.infotrack.talleres.presentacion.vistamodelos.AlistamientoLocalVM;
import com.infotrack.talleres.presentacion.vistamodelos.EquipoLocalVM;
import com.infotrack.talleres.transversal.enumeradores.TipoAlistamientoEnum;
import com.infotrack.talleres.transversal.enumeradores.TipoDiagnosticoRechazoEnum;

public interface INavegador extends NavegadorAbstracto {

    void navegarListadoAlistamientoActividad(Context contexto, TipoAlistamientoEnum tipoAlistamientoEnum);

    FragmentoBase navegarListadoAlistamientoFragmento(TipoAlistamientoEnum tipoAlistamientoEnum);

    void navegarListadoEquiposActividad(Context contexto, String conductor, String idAlistamiento, TipoAlistamientoEnum tipoAlistamientoEnum);

    FragmentoBase navegarListadoEquiposFragmento(String conductor, String idAlistamiento, TipoAlistamientoEnum tipoAlistamientoEnum);

    void navegarDatosEquipo(Context contexto);

    void navegarInicioAlistamientoActividad(Context contexto, TipoAlistamientoEnum tipoAlistamientoEnum);

    FragmentoBase navegarInicioAlistamientoFragmento(TipoAlistamientoEnum tipoAlistamientoEnum);

    void navegarDatosEquipoActividad(Context contexto, String conductor, String idAlistamiento, TipoAlistamientoEnum tipoAlistamientoEnum);

    void navegarListaChequeoActividad(Fragment fragmento, EquipoLocalVM equipoLocal, int codigoIntencion, TipoAlistamientoEnum tipoAlistamientoEnum);

    FragmentoBase navegarListaChequeoFragmento(EquipoLocalVM equipoLocal, TipoAlistamientoEnum tipoAlistamientoEnum);

    void navegarFirmaAlistamiento(Context contexto, AlistamientoLocalVM alistamientoSeleccionado, TipoAlistamientoEnum tipoAlistamientoEnum);

    void navegarMenuActividad(Context contexto);

    FragmentoBase navegarMenuFragmento();

    void navegarListadoDiagnosticoRechazoActividad(Context contexto, TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazoEnum);

    FragmentoBase navegarListadoDiagnosticoRechazoFragmento(TipoDiagnosticoRechazoEnum tipoDiagnosticoRechazoEnum);

    void navegarCasoActividad(Context contexto, TipoDiagnosticoRechazoEnum tipoDiagnostico);
}
