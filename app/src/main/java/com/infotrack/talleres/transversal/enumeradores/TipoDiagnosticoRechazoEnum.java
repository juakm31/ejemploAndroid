package com.infotrack.talleres.transversal.enumeradores;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TipoDiagnosticoRechazoEnum {

    DiagnosticoInicial(1, ProcesoEquipoEnum.DiagnosticoInicial),
    DiagnosticoFinal(2, ProcesoEquipoEnum.DiagnosticoFinal),
    RechazoDiagnosticoInicial(3, ProcesoEquipoEnum.RechazoDiagnosticoInicial),
    RechazoDiagnosticoFinal(4, ProcesoEquipoEnum.RechazoDiagnosticoFinal);

    private final int id;
    private final ProcesoEquipoEnum procesoEquipo;
    private static Map map = new LinkedHashMap<>();

    TipoDiagnosticoRechazoEnum(int id, ProcesoEquipoEnum procesoEquipo) {
        this.id = id;
        this.procesoEquipo = procesoEquipo;
    }

    static {
        for (TipoDiagnosticoRechazoEnum item : TipoDiagnosticoRechazoEnum.values()) {
            map.put(item.id, item);
            map.put(item.procesoEquipo, item);
        }
    }

    public static TipoDiagnosticoRechazoEnum valueOf(ProcesoEquipoEnum procesoEquipo) {
        return (TipoDiagnosticoRechazoEnum) map.get(procesoEquipo);
    }

    public ProcesoEquipoEnum getProcesoEquipo() {
        return procesoEquipo;
    }
}
