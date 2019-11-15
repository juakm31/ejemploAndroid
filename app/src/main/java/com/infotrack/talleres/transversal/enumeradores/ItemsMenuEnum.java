package com.infotrack.talleres.transversal.enumeradores;

import com.infotrack.talleres.R;

import java.util.HashMap;
import java.util.LinkedHashMap;

public enum ItemsMenuEnum {
    Recepcion(1, R.drawable.img_2),
    Despacho(2, R.drawable.img_3),
    DiagnosticoInicial(3, R.drawable.img_4),
    DiagnosticoFinal(4, R.drawable.img_5),
    BandejaRechazoDiagnosticoInicial(5, R.drawable.img_6),
    BandejaRechazosDiagnosticoFinal(6, R.drawable.img_7),
    MantenimientoAgendado(7, R.drawable.img_8),
    Higiene(8, R.drawable.img_9),
    ConsultaEquipos(9, R.drawable.img_10),
    ValidacionMantenimientoDiagnosticoInicial(10, R.drawable.img_11),
    ValidacionMantenimientoDiagnosticoFinal(11, R.drawable.img_10),
    BajaRecepcionDiagnosticoInicial(12, R.drawable.img_9),
    BajaIntervencion(13, R.drawable.img_8),
    ConsultaEquiposAprobador(14, R.drawable.img_7);

    private final int id;
    private final int idRecurso;
    private static HashMap<Integer, ItemsMenuEnum> map = new LinkedHashMap<>();

    ItemsMenuEnum(int id, int idRecurso) {
        this.id = id;
        this.idRecurso = idRecurso;
    }

    static {
        for (ItemsMenuEnum item : ItemsMenuEnum.values()) {
            map.put(item.id, item);
            map.put(item.idRecurso, item);
        }
    }

    public static ItemsMenuEnum valueOf(int idMenu) {
        return map.get(idMenu);
    }

    public int getValue() {
        return id;
    }
    public int getRecursoId(){return idRecurso;}
}
