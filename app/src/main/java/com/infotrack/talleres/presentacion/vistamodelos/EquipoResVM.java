package com.infotrack.talleres.presentacion.vistamodelos;

import com.infotrack.talleres.transversal.enumeradores.EtapasChecklistEnum;
import com.infotrack.talleres.transversal.enumeradores.EtapasEquipoEnum;

import java.io.Serializable;

public class EquipoResVM implements Serializable {

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
    private boolean requiereMantenimiento;
    private boolean baja;
    private boolean higiene;
    private String datosListaChequeo;
    private String observaciones;
    private int etapa;

    public EquipoResVM(EquipoLocalVM equipoLocalVM) {
        this.idPersona = equipoLocalVM.getIdPersona();
        this.idAlistamiento = equipoLocalVM.getIdAlistamiento();
        this.placa = equipoLocalVM.getPlaca();
        this.idClase = equipoLocalVM.getIdClase();
        this.capacidad = equipoLocalVM.getCapacidad();
        this.clase = equipoLocalVM.getClase();
        this.fabricante = equipoLocalVM.getFabricante();
        this.centroplanificador = equipoLocalVM.getCentroplanificador();
        this.fechaIngreso = equipoLocalVM.getFechaIngreso();
        this.conductor = equipoLocalVM.getConductor();
        this.creador = equipoLocalVM.getCreador();
        this.requiereMantenimiento = equipoLocalVM.getEtapa() == EtapasEquipoEnum.RequiereMantenimiento.getValue();
        this.baja = equipoLocalVM.getEtapaChecklist() == EtapasChecklistEnum.Baja.getValue();
        this.higiene = equipoLocalVM.getEtapaChecklist() == EtapasChecklistEnum.Higiene.getValue();
        this.datosListaChequeo = equipoLocalVM.getDatosListaChequeo();
        this.observaciones = equipoLocalVM.getObservaciones();
        this.etapa = equipoLocalVM.getEtapa();

    }

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
