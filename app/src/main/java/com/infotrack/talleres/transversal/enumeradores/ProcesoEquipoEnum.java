package com.infotrack.talleres.transversal.enumeradores;

import java.util.HashMap;
import java.util.LinkedHashMap;

public enum ProcesoEquipoEnum {

    Recepcion(1),
    Despacho(2),
    DiagnosticoInicial(3),
    DiagnosticoFinal(4),
    RechazoDiagnosticoInicial(5),
    RechazoDiagnosticoFinal(6);

    private final int id;
    private static HashMap<Integer, ProcesoEquipoEnum> map = new LinkedHashMap<>();

    ProcesoEquipoEnum(int id) {
        this.id = id;
    }

    static {
        for (ProcesoEquipoEnum item : ProcesoEquipoEnum.values()) {
            map.put(item.id, item);
        }
    }

    public static ProcesoEquipoEnum valueOf(int id) {
        return map.get(id);
    }

    public int getValue() {
        return id;
    }
}
