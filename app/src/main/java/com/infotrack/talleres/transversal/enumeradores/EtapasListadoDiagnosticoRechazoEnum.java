package com.infotrack.talleres.transversal.enumeradores;

import com.infotrack.talleres.R;

import java.util.HashMap;
import java.util.LinkedHashMap;

public enum EtapasListadoDiagnosticoRechazoEnum {
    CREADO(0, R.color.colorEtapa6),
    EN_PROCESO(1, R.color.colorEtapa4);

    private final int id;
    private final int colorEtapa;
    private static HashMap<Integer, EtapasListadoDiagnosticoRechazoEnum> map = new LinkedHashMap<>();

    EtapasListadoDiagnosticoRechazoEnum(int id, int colorEtapa) {
        this.id = id;
        this.colorEtapa = colorEtapa;
    }

    static {
        for (EtapasListadoDiagnosticoRechazoEnum item : EtapasListadoDiagnosticoRechazoEnum.values()) {
            map.put(item.id, item);
            map.put(item.colorEtapa, item);
        }
    }

    public static EtapasListadoDiagnosticoRechazoEnum valueOf(int idEtapa) {
        return map.get(idEtapa);
    }

    public int getValue() {
        return id;
    }

    public int getColorEtapa() {
        return colorEtapa;
    }
}
