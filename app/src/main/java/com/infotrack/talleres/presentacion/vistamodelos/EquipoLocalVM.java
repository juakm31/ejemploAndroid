package com.infotrack.talleres.presentacion.vistamodelos;

import java.io.Serializable;

public class EquipoLocalVM implements Serializable {
    private String idPersona;
    private String idAlistamiento;
    private String placa;
    private int idClase;
    private int capacidad;
    private String clase;
    private String fabricante;
    private String centroplanificador;
    private String fechaIngreso;
    private String conductor;
    private String creador;
    private String datosListaChequeo;
    private String observaciones;
    private int etapa;
    private int etapaChecklist;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdAlistamiento() {
        return idAlistamiento;
    }

    public void setIdAlistamiento(String idAlistamiento) {
        this.idAlistamiento = idAlistamiento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCentroplanificador() {
        return centroplanificador;
    }

    public void setCentroplanificador(String centroplanificador) {
        this.centroplanificador = centroplanificador;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getDatosListaChequeo() {
        return datosListaChequeo;
    }

    public void setDatosListaChequeo(String datosListaChequeo) {
        this.datosListaChequeo = datosListaChequeo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEtapaChecklist() {
        return etapaChecklist;
    }

    public void setEtapaChecklist(int etapaChecklist) {
        this.etapaChecklist = etapaChecklist;
    }
}
