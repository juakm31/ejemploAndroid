package com.infotrack.talleres.presentacion.vistamodelos;

import java.util.HashMap;

public class ArchivoChecklist {
    private String fechaArchivo;
    private String nombreArchivo;
    private HashMap<String, String> metadatos;
    private String idUsuario;
    private String rutaLocal;

    public String getFechaArchivo() {
        return fechaArchivo;
    }

    public void setFechaArchivo(String fechaArchivo) {
        this.fechaArchivo = fechaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public HashMap<String, String> getMetadatos() {
        return metadatos;
    }

    public void setMetadatos(HashMap<String, String> metadatos) {
        this.metadatos = metadatos;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRutaLocal() {
        return rutaLocal;
    }

    public void setRutaLocal(String rutaLocal) {
        this.rutaLocal = rutaLocal;
    }
}
