package com.infotrack.talleres.datos.repositorios.fachada.interfaces;

import io.reactivex.Observable;

public interface ListaChequeoRepositorio {
    Observable<String> consultarListaChequeo(String recepcionId, String placa);
    Observable<Void> persistirListaChequeo(String recepcionId, String placa, String listaChequeo);
}
