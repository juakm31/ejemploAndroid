package com.infotrack.talleres.transversal.constantes.negocio;

public class AlistamientoConstantes {
    //region Retrofit
    public static final String URL_CREAR_RECEPCION = "Recepcion/CrearRecepcion/";
    public static final String URL_CREAR_DESPACHO = "Despacho/CrearDespacho/";
    public static final String URL_ELIMINAR_RECEPCION = "Recepcion/EliminarRecepcion/{idRecepcion}";
    public static final String URL_ELIMINAR_DESPACHO = "Despacho/EliminarDespacho/{idDespacho}";
    //endregion

    //region Intencion
    public static final String EXTRA_TIPO_ALISTAMIENTO = "tipo_alistamiento";
    public static final String EXTRA_ID_ALISTAMIENTO = "id_alistamiento";
    public static final String EXTRA_CONDUCTOR = "conductor";
    public static final String EXTRA_ALISTAMIENTO_LOCAL = "conductor";
    //endregion

    //region Validacion
    public static final String requiereConsultaPlaca = "Por favor consulte una placa de equipo.";
    public static final String campoObservacionVacio = "LLene el campo de observación.";

    public static final String eliminacionNoPosible = "Tiene equipos relacionados, no se ha podido eliminar.";
    public static final String mensajeConfirmacionEliminacionAlistamiento = "¿Esta seguro que desea eliminar?";

    public static final String noPuedeFinalizar = "Diligencie todos los equipos para poder finalizar.";

    public static final String VAL_PLACA_INVALIDA = "Placa no valida.";
    //endregion
}
