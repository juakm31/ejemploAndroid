package com.infotrack.talleres.datos.entidades;

import com.google.gson.annotations.SerializedName;

public class EquipoResDto {

    @SerializedName(value = "idPersona", alternate = {"IdPersona"})
    private String idPersona;
    @SerializedName(value = "idAlistamiento", alternate = {"IdAlistamiento"})
    private String idAlistamiento;
    @SerializedName(value = "placa", alternate = {"Placa"})
    private String placa;
    @SerializedName(value = "idClase", alternate = {"IdClase"})
    private int idClase;
    @SerializedName(value = "capacidad", alternate = {"Capacidad"})
    private int capacidad;
    @SerializedName(value = "clase", alternate = {"Clase"})
    private String clase;
    @SerializedName(value = "fabricante", alternate = {"Fabricante"})
    private String fabricante;
    @SerializedName(value = "centroplanificador", alternate = {"CentroPlanificador"})
    private String centroplanificador;
    @SerializedName(value = "fechaIngreso", alternate = {"FechaIngreso"})
    private String fechaIngreso;
    @SerializedName(value = "conductor", alternate = {"Conductor"})
    private String conductor;
    @SerializedName(value = "creador", alternate = {"Creador"})
    private String creador;
    @SerializedName(value = "requiereMantenimiento", alternate = {"RequiereMantenimiento"})
    private boolean requiereMantenimiento;
    @SerializedName(value = "baja", alternate = {"Baja"})
    private boolean baja;
    @SerializedName(value = "higiene", alternate = {"Higiene"})
    private boolean higiene;
    @SerializedName(value = "datosListaChequeo", alternate = {"DatosListaChequeo"})
    private String datosListaChequeo;
    @SerializedName(value = "observaciones", alternate = {"Observaciones"})
    private String observaciones;
    @SerializedName(value = "etapa", alternate = {"Etapa"})
    private int etapa;

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

    public boolean isRequiereMantenimiento() {
        return requiereMantenimiento;
    }

    public void setRequiereMantenimiento(boolean requiereMantenimiento) {
        this.requiereMantenimiento = requiereMantenimiento;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public boolean isHigiene() {
        return higiene;
    }

    public void setHigiene(boolean higiene) {
        this.higiene = higiene;
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
}
