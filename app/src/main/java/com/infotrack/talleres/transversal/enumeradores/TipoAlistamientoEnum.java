package com.infotrack.talleres.transversal.enumeradores;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

public enum TipoAlistamientoEnum implements Serializable {
    Recepcion(ProcesoEquipoEnum.Recepcion),
    Despacho(ProcesoEquipoEnum.Despacho);

    private final ProcesoEquipoEnum procesoEquipo;
    private static HashMap<ProcesoEquipoEnum, TipoAlistamientoEnum> map = new LinkedHashMap<>();

    TipoAlistamientoEnum(ProcesoEquipoEnum procesoEquipo) {
        this.procesoEquipo = procesoEquipo;
    }

    static {
        for (TipoAlistamientoEnum item : TipoAlistamientoEnum.values()) {
            map.put(item.procesoEquipo, item);
        }
    }

    public static TipoAlistamientoEnum valueOf(ProcesoEquipoEnum procesoEquipo) {
        return map.get(procesoEquipo);
    }

    public ProcesoEquipoEnum getProcesoEquipo() {
        return procesoEquipo;
    }
}
