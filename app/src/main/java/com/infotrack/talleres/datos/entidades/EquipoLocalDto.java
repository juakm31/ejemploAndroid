package com.infotrack.talleres.datos.entidades;

import com.google.firebase.database.PropertyName;

public class EquipoLocalDto {
    @PropertyName("IdAlistamiento")
    private String idAlistamiento;
    @PropertyName("IdPersona")
    private String idPersona;
    @PropertyName("Placa")
    private String placa;
    @PropertyName("IdClase")
    private int idClase;
    @PropertyName("Capacidad")
    private int capacidad;
    @PropertyName("Clase")
    private String clase;
    @PropertyName("Fabricante")
    private String fabricante;
    @PropertyName("CentroPlanificador")
    private String centroplanificador;
    @PropertyName("FechaIngreso")
    private String fechaIngreso;
    @PropertyName("Conductor")
    private String conductor;
    @PropertyName("Creador")
    private String creador;
    @PropertyName("DatosListaChequeo")
    private String datosListaChequeo;
    @PropertyName("Observaciones")
    private String observaciones;
    @PropertyName("Etapa")
    private int etapa;
    @PropertyName("EtapaChecklist")
    private int etapaChecklist;

    @PropertyName("Etapa")
    public int getEtapa() {
        return etapa;
    }

    @PropertyName("Etapa")
    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    @PropertyName("IdPersona")
    public String getIdPersona() {
        return idPersona;
    }

    @PropertyName("IdPersona")
    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    @PropertyName("IdAlistamiento")
    public String getIdAlistamiento() {
        return idAlistamiento;
    }

    @PropertyName("IdAlistamiento")
    public void setIdAlistamiento(String idAlistamiento) {
        this.idAlistamiento = idAlistamiento;
    }

    @PropertyName("Placa")
    public String getPlaca() {
        return placa;
    }

    @PropertyName("Placa")
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @PropertyName("IdClase")
    public int getIdClase() {
        return idClase;
    }

    @PropertyName("IdClase")
    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    @PropertyName("Capacidad")
    public int getCapacidad() {
        return capacidad;
    }

    @PropertyName("Capacidad")
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @PropertyName("Clase")
    public String getClase() {
        return clase;
    }

    @PropertyName("Clase")
    public void setClase(String clase) {
        this.clase = clase;
    }

    @PropertyName("Fabricante")
    public String getFabricante() {
        return fabricante;
    }

    @PropertyName("Fabricante")
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @PropertyName("CentroPlanificador")
    public String getCentroplanificador() {
        return centroplanificador;
    }

    @PropertyName("CentroPlanificador")
    public void setCentroplanificador(String centroplanificador) {
        this.centroplanificador = centroplanificador;
    }

    @PropertyName("FechaIngreso")
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    @PropertyName("FechaIngreso")
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    @PropertyName("DatosListaChequeo")
    public String getDatosListaChequeo() {
        return datosListaChequeo;
    }

    @PropertyName("DatosListaChequeo")
    public void setDatosListaChequeo(String datosListaChequeo) {
        this.datosListaChequeo = datosListaChequeo;
    }

    @PropertyName("Observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    @PropertyName("Observaciones")
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @PropertyName("EtapaChecklist")
    public int getEtapaChecklist() {
        return etapaChecklist;
    }

    @PropertyName("EtapaChecklist")
    public void setEtapaChecklist(int etapaChecklist) {
        this.etapaChecklist = etapaChecklist;
    }
}
