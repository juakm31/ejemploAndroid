package com.infotrack.talleres.transversal.enumeradores;


import com.infotrack.talleres.R;

import java.util.HashMap;
import java.util.LinkedHashMap;

public enum EtapasEquipoEnum {
    Creado(1, R.color.colorEtapa6),
    Exitoso(2, R.color.colorEtapa3),
    Baja(3, R.color.colorEtapa3),
    Higiene(4, R.color.colorEtapa3),
    RequiereMantenimiento(5, R.color.colorEtapa3);

    private final int id;
    private final int colorEtapa;
    private static HashMap<Integer, EtapasEquipoEnum> map = new LinkedHashMap<>();

    EtapasEquipoEnum(int id, int colorEtapa) {
        this.id = id;
        this.colorEtapa = colorEtapa;
    }

    static {
        for (EtapasEquipoEnum item : EtapasEquipoEnum.values()) {
            map.put(item.id, item);
            map.put(item.colorEtapa, item);
        }
    }

    public static EtapasEquipoEnum valueOf(int estado) {
        return map.get(estado);
    }

    public int getValue() {
        return id;
    }

    public int getColorEtapa() {
        return colorEtapa;
    }
}
