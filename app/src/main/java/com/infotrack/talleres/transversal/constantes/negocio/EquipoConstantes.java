package com.infotrack.talleres.transversal.constantes.negocio;

public class EquipoConstantes {
    //region Retrofit
    public static final String URL_CONSULTAR_EQUIPO_PLACA = "Equipo/ConsultarEquipo/{placa}/{idPersona}/{procesoEquipo}";
    public static final String URL_AGREGAR_EQUIPO_RECEPCION = "Recepcion/AgregarEquipoRecepcion/";
    public static final String URL_AGREGAR_EQUIPO_DESPACHO = "Despacho/AgregarEquipoDespacho/";
    public static final String ValidarEliminacionEquipo = "¿Está seguro de eliminar este equipo?";
    public static final String ActualizarEtapaEquipos = "Etapa";
    //endregion

    //region Intencion
    public static final String EXTRA_PROCESO_EQUIPO = "procesoEquipo";
    //endregion

    public static final String PersistirEquipoRecepcion = "%s/EquiposRecepcion/%s/%s/%s";
    public static final String PersistirEquipoDespacho = "%s/EquiposDespacho/%s/%s/%s";
}
