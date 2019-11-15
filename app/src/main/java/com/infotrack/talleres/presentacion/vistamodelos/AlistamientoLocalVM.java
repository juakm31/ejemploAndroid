package com.infotrack.talleres.presentacion.vistamodelos;

import com.infotrack.artefactos.utilitarios.comunes.CadenasHelper;

import java.io.Serializable;

public class AlistamientoLocalVM implements Serializable {
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

    public AlistamientoLocalVM() {
    }

    public AlistamientoLocalVM(AlistamientoVM alistamientoVM) {
        this.idTaller = alistamientoVM.getIdTaller();
        this.idTransportador = alistamientoVM.getIdTransportador();
    }

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
        return CadenasHelper.obtenerInstancia().formatoPascal(transportadora);
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
        return CadenasHelper.obtenerInstancia().formatoPascal(conductor);
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getCreador() {
        return  CadenasHelper.obtenerInstancia().formatoPascal(creador);
    }

    public void setCreador(String creador) {
        this.creador = creador;
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

    public int getCantidadEquipos() {
        return cantidadEquipos;
    }

    public void setCantidadEquipos(int cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
}
