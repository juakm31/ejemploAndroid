package com.infotrack.talleres.datos.repositorios.fachada.usos;

import com.infotrack.talleres.datos.repositorios.fabrica.FabricaAbstracta;
import com.infotrack.talleres.datos.repositorios.fabrica.GeneradorFabrica;
import com.infotrack.talleres.datos.repositorios.fachada.interfaces.ListaChequeoRepositorio;
import com.infotrack.talleres.datos.repositorios.origendatos.listachequeo.ListaChequeoOrigenDatos;
import com.infotrack.talleres.transversal.enumeradores.FabricasEnum;
import com.infotrack.talleres.transversal.enumeradores.OrigenDatosEnum;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ListaChequeoUso implements ListaChequeoRepositorio {

    @Inject
    public ListaChequeoUso() {

    }

    private ListaChequeoOrigenDatos generarFabricaFirebase() {
        FabricaAbstracta fabricaAbstracta = GeneradorFabrica.crearFabrica(FabricasEnum.ListaChequeo);
        return (ListaChequeoOrigenDatos) fabricaAbstracta.crear(OrigenDatosEnum.FIREBASE.toString());
    }

    @Override
    public Observable<String> consultarListaChequeo(String recepcionId, String placa) {
        return generarFabricaFirebase().consultarListaChequeo(recepcionId, placa);
    }

    @Override
    public Observable<Void> persistirListaChequeo(String recepcionId, String placa, String listaChequeo) {
        return generarFabricaFirebase().persistirListaChequeo(recepcionId, placa, listaChequeo);
    }
}
