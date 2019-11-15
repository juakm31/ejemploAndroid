package com.infotrack.talleres.datos.entidades;

import com.google.firebase.database.PropertyName;

public class AlistamientoLocalDto {

    private String idAlistamiento;
    private int idTaller;
    private int idTransportador;
    private String transportadora;
    private String idConductor;
    private String conductor;
    private String creador;
    private String placa;
    private String observaciones;
    private String fechaInicio;
    private int cantidadEquipos;
    private int etapa;

    @PropertyName("IdAlistamiento")
    public String getIdAlistamiento() {
        return idAlistamiento;
    }

    @PropertyName("IdAlistamiento")
    public void setIdAlistamiento(String idAlistamiento) {
        this.idAlistamiento = idAlistamiento;
    }
    @PropertyName("IdTaller")
    public int getIdTaller() {
        return idTaller;
    }
    @PropertyName("IdTaller")
    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }
    @PropertyName("IdTransportadora")
    public int getIdTransportador() {
        return idTransportador;
    }
    @PropertyName("IdTransportadora")
    public void setIdTransportador(int idTransportador) {
        this.idTransportador = idTransportador;
    }
    @PropertyName("Transportadora")
    public String getTransportadora() {
        return transportadora;
    }
    @PropertyName("Transportadora")
    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }
    @PropertyName("IdConductor")
    public String getIdConductor() {
        return idConductor;
    }
    @PropertyName("IdConductor")
    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }
    @PropertyName("Conductor")
    public String getConductor() {
        return conductor;
    }
    @PropertyName("Conductor")
    public void setConductor(String conductor) {
        this.conductor = conductor;
    }
    @PropertyName("Creador")
    public String getCreador() {
        return creador;
    }
    @PropertyName("Creador")
    public void setCreador(String creador) {
        this.creador = creador;
    }
    @PropertyName("Placa")
    public String getPlaca() {
        return placa;
    }
    @PropertyName("Placa")
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    @PropertyName("Observaciones")
    public String getObservaciones() {
        return observaciones;
    }
    @PropertyName("Observaciones")
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    @PropertyName("FechaInicio")
    public String getFechaInicio() {
        return fechaInicio;
    }
    @PropertyName("FechaInicio")
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    @PropertyName("CantidadEquipos")
    public int getCantidadEquipos() {
        return cantidadEquipos;
    }
    @PropertyName("CantidadEquipos")
    public void setCantidadEquipos(int cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }
    @PropertyName("Etapa")
    public int getEtapa() {
        return etapa;
    }
    @PropertyName("Etapa")
    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
}
