package com.infotrack.talleres.datos.repositorios.origendatos.listachequeo;

import io.reactivex.Observable;

public interface ListaChequeoOrigenDatos {
    Observable<String> consultarListaChequeo(String recepcionId, String placa);
    Observable<Void> persistirListaChequeo(String recepcionId, String placa, String listaChequeo);
}
