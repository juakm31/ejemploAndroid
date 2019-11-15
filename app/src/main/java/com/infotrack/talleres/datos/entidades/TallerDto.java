package com.infotrack.talleres.datos.entidades;

import com.google.firebase.database.PropertyName;

public class TallerDto {

    @PropertyName("IdTaller")
    private int idTaller;
    @PropertyName("Taller")
    private String taller;

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }
}
