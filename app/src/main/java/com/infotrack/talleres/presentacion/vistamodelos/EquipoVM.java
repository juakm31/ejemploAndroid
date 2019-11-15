package com.infotrack.talleres.presentacion.vistamodelos;

public class EquipoVM {

    private String placa;
    private int clase;
    private int capacidad;
    private String fabricante;
    private String descripcionClase;
    private int idCaso;

    public String getDescripcionClase() {
        return descripcionClase;
    }

    public void setDescripcionClase(String descripcionClase) {
        this.descripcionClase = descripcionClase;
    }

    private String centroPlanificador;

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
