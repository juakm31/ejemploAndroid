package com.infotrack.talleres.datos.entidades;

import com.google.gson.annotations.SerializedName;

public class EquipoDto {

    @SerializedName(value = "placa", alternate = {"Placa"})
    private String placa;
    @SerializedName(value = "clase", alternate = {"ClaseEquipoComercial"})
    private int clase;
    @SerializedName(value = "capacidad", alternate = {"Capacidad"})
    private int capacidad;
    @SerializedName(value = "fabricante", alternate = {"Fabricante"})
    private String fabricante;
    @SerializedName(value = "centroPlanificador", alternate = {"CentroPlanificador"})
    private String centroPlanificador;
    @SerializedName(value = "descripcionClase", alternate = {"DescripcionEqClaseComercial"})
    private String descripcionClase;
    @SerializedName(value = "idCaso", alternate = {"IdCaso"})
    private int idCaso;

    public String getDescripcionClase() {
        return descripcionClase;
    }

    public void setDescripcionClase(String descripcionClase) {
        this.descripcionClase = descripcionClase;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCentroPlanificador() {
        return centroPlanificador;
    }

    public void setCentroPlanificador(String centroPlanificador) {
        this.centroPlanificador = centroPlanificador;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }
}
