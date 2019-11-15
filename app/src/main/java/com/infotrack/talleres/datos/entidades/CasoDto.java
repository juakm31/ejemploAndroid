package com.infotrack.talleres.datos.entidades;

import com.google.firebase.database.PropertyName;

public class CasoDto {
    @PropertyName("IdCaso")
    private String idCaso;
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
    @PropertyName("tecnico")
    private String tecnico;
    @PropertyName("idTecnico")
    private String idTecnico;
    @PropertyName("Etapa")
    private int etapa;

    public String getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(String idCaso) {
        this.idCaso = idCaso;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
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

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
}
