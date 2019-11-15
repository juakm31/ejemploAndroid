package com.infotrack.talleres.datos.entidades;

import com.google.gson.annotations.SerializedName;

public class TransportadorDto {

    @SerializedName(value = "idTransportadora", alternate = {"IdTransportadora"})
    private int idTransportadora;
    @SerializedName(value = "transportadora", alternate = {"Transportadora"})
    private String transportadora;
    @SerializedName(value = "placa", alternate = {"Placa"})
    private String placa;
    @SerializedName(value = "idConductor", alternate = {"IdConductor"})
    private String idConductor;
    @SerializedName(value = "conductor", alternate = {"Conductor"})
    private String conductor;

    public int getIdTransportadora() {
        return idTransportadora;
    }

    public void setIdTransportadora(int idTransportadora) {
        this.idTransportadora = idTransportadora;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }
}
