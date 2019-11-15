package com.infotrack.talleres.transversal.firebase.rutas;

public class AlistamientoFirebase {

    //region Rutas
    public static final String PersistirAlistamientoRecepcion = "Alistamiento/%s/Recepcion/%s";
    public static final String ConsultarAlistamientoRecepcion = "Alistamiento/%s/Recepcion";
    public static final String EliminarAlistamientoRecepcion = "Alistamiento/%s/Recepcion/%s";
    public static final String EliminarAlistamientoDespacho = "Alistamiento/%s/Despacho/%s";
    public static final String PersistirAlistamientoDespacho = "Alistamiento/%s/Despacho/%s";
    public static final String ConsultarAlistamientoDespacho = "Alistamiento/%s/Despacho";
    public static final String ActualizarPropiedadAlistamiento = "Alistamiento/%s/Recepcion/%s/%s";
    public static final String ActualizarPropiedadAlistamientoDespacho = "Alistamiento/%s/Despacho/%s/%s";

    public static final String alistamiento = "Alistamiento/";
    //endregion

    //region Propiedades
    public static final String PropiedadCantidadEquipos = "CantidadEquipos";
//    public static final String PropiedadPermiteFinalizar = "PermiteFinalizar";
    public static final String PropiedadEtapa = "Etapa";
    //endregion
}
