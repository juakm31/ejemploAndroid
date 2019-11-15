package com.infotrack.talleres.datos.entidades;

import com.google.gson.annotations.SerializedName;

public class AlistamientoDto {
    @SerializedName(value = "idAlistamiento", alternate = {"IdAlistamiento"})
    private String idAlistamiento;
    @SerializedName(value = "idTaller", alternate = {"IdTaller"})
    private int idTaller;
    @SerializedName(value = "idTransportador", alternate = {"IdTransportadora"})
    private int idTransportador;
    @SerializedName(value = "transportadora", alternate = {"Transportadora"})
    private String transportadora;
    @SerializedName(value = "idConductor", alternate = {"IdConductor"})
    private String idConductor;
    @SerializedName(value = "conductor", alternate = {"Conductor"})
    private String conductor;
    @SerializedName(value = "placa", alternate = {"Placa"})
    private String placa;
    @SerializedName(value = "observaciones", alternate = {"Observaciones"})
    private String observaciones;
    @SerializedName(value = "fechaInicio", alternate = {"FechaInicio"})
    private String fechaInicio;

    public String getIdAlistamiento() {
        return idAlistamiento;
    }

    public void setIdAlistamiento(String idAlistamiento) {
        this.idAlistamiento = idAlistamiento;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public int getIdTransportador() {
        return idTransportador;
    }

    public void setIdTransportador(int idTransportador) {
        this.idTransportador = idTransportador;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
