package com.infotrack.talleres.transversal.enumeradores;

import java.util.HashMap;
import java.util.Map;

public enum AccionesListadoAlistamientoEnum {
    LISTADO_EQUIPOS(0),
    ELIMINAR(1),
    FINALIZAR(2);

    private final int id;
    private static Map map = new HashMap<>();

    AccionesListadoAlistamientoEnum(int id) {
        this.id = id;
    }

    static {
        for (AccionesListadoAlistamientoEnum item : AccionesListadoAlistamientoEnum.values()) {
            map.put(item.id, item);
        }
    }

    public static AccionesListadoAlistamientoEnum valueOf(int idEtapa) {
        return (AccionesListadoAlistamientoEnum) map.get(idEtapa);
    }

    public int getValue() {
        return id;
    }
}
