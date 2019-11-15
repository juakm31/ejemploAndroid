package com.infotrack.talleres.transversal.firebase;

import com.google.firebase.database.FirebaseDatabase;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

public class FirebaseSync {

    public static  FirebaseSync obtenerInstancia() {
        return new FirebaseSync();
    }

//    private static String nodoCatalogo = ConstantesCompartidas.FIREBASE_NODO_RAIZ + "/" + Constantes.FIREBASE_NODO_CATALOGO_VALORES;
//    private static String nodoConfiguracion = ConstantesCompartidas.FIREBASE_NODO_RAIZ + "/" + Constantes.FIREBASE_NODO_CONFIGURACION;
//    private static String nodoTerceros = ConstantesCompartidas.FIREBASE_NODO_RAIZ + "/" + Constantes.FIREBASE_NODO_TERCEROS;
//    private static String[] nodos = new String[]{
//            Constantes.FIREBASE_NODO_MOVILIZACION_ENCABEZADO,
//            Constantes.FIREBASE_NODO_MOVILIZACION,
//            Constantes.FIREBASE_NODO_PLANEACION,
//            Constantes.FIREBASE_NODO_LISTAS_CHEQUEO,
//            Constantes.FIREBASE_NODO_FORMATOS_MOVILIZACION
//    };

    public void mantenerSincronizacionNodos(String usuarioId) {
//        for (String nodo : nodos) {
//            String x = String.format("%s/%s/%s", ConstantesCompartidas.FIREBASE_NODO_RAIZ, nodo, usuarioId);
//            FirebaseDatabase.getInstance().getReference(x).keepSynced(true);
//        }
//        FirebaseDatabase.getInstance().getReference(nodoCatalogo).keepSynced(true);
//        FirebaseDatabase.getInstance().getReference(nodoConfiguracion).keepSynced(true);
//        FirebaseDatabase.getInstance().getReference(nodoTerceros).keepSynced(true);
    }
}
