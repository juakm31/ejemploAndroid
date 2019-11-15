package com.infotrack.talleres.transversal.enumeradores;

import java.util.HashMap;
import java.util.LinkedHashMap;

public enum EtapasChecklistEnum {
    Exitoso(0),
    Baja(1),
    Higiene(2);

    private final int id;
    private static HashMap<Integer, EtapasChecklistEnum> map = new LinkedHashMap<>();

    EtapasChecklistEnum(int id) {
        this.id = id;
    }

    static {
        for (EtapasChecklistEnum item : EtapasChecklistEnum.values()){
            map.put(item.id, item);
        }
    }

    public static EtapasChecklistEnum valueOf(int estado) {
        return map.get(estado);
    }

    public int getValue() {
        return id;
    }
}
