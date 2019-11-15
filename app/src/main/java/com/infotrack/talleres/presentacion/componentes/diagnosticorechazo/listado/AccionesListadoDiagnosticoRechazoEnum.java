package com.infotrack.talleres.presentacion.componentes.diagnosticorechazo.listado;

import java.util.HashMap;
import java.util.LinkedHashMap;

public enum AccionesListadoDiagnosticoRechazoEnum {
    CheckList(0),
    Sintomas(1),
    Servicios(2),
    Componentes(3);

    private final int id;
    private static HashMap<Integer, AccionesListadoDiagnosticoRechazoEnum> map = new LinkedHashMap<>();

    AccionesListadoDiagnosticoRechazoEnum(int id) {
        this.id = id;
    }

    static {
        for (AccionesListadoDiagnosticoRechazoEnum item : AccionesListadoDiagnosticoRechazoEnum.values()) {
            map.put(item.id, item);
        }
    }

    public static AccionesListadoDiagnosticoRechazoEnum valueOf(int estado) {
        return map.get(estado);
    }

    public int getValue() {
        return id;
    }
}