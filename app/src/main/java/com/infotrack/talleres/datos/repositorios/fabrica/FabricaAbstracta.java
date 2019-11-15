package com.infotrack.talleres.datos.repositorios.fabrica;


public interface FabricaAbstracta<T> {
    T crear(String Tipo);
}
