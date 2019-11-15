package com.infotrack.talleres.transversal.enumeradores;

import com.infotrack.talleres.R;

import java.util.HashMap;
import java.util.LinkedHashMap;

public enum EtapasListadoAlistamientoEnum {
    CREADA(0, R.color.colorEtapa6),
    EN_PROCESO(1, R.color.colorEtapa4),
    FINALIZAR(2, R.color.colorEtapa3);

    private final int id;
    private final int colorEtapa;
    private static HashMap<Integer, EtapasListadoAlistamientoEnum> map = new LinkedHashMap<>();

    EtapasListadoAlistamientoEnum(int id, int colorEtapa) {
        this.id = id;
        this.colorEtapa = colorEtapa;
    }

    static {
        for (EtapasListadoAlistamientoEnum item : EtapasListadoAlistamientoEnum.values()) {
            map.put(item.id, item);
            map.put(item.colorEtapa, item);
        }
    }

    public static EtapasListadoAlistamientoEnum valueOf(int idEtapa) {
        return map.get(idEtapa);
    }

    public int getValue() {
        return id;
    }

    public int getColorEtapa() {
        return colorEtapa;
    }
}
