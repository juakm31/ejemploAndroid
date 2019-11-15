package com.infotrack.talleres.presentacion.componentes.alistamiento.listadoequipos;

import java.util.HashMap;
import java.util.Map;

public enum AccionesEquiposFabEnum {
    CheckList(0),
    Eliminar(1);

    private final int id;
    private static Map map = new HashMap<>();

    AccionesEquiposFabEnum(int id) {
        this.id = id;
    }

    static {
        for (AccionesEquiposFabEnum item : AccionesEquiposFabEnum.values())
            map.put(item.id, item);
    }

    public static AccionesEquiposFabEnum valueOf(int estado) {
        return (AccionesEquiposFabEnum) map.get(estado);
    }

    public int getValue() {
        return id;
    }
}
