package com.infotrack.talleres.dominio.modelos;

public class MenuMdl {

    private String descripcionItem;
    private int cantidad;
    private String codigo;
    private int idCodigo;
    private int ordenPorMostrar;
    private int codigoRelacionMovil;

    public String getDescripcionItem() {
        return descripcionItem;
    }

    public void setDescripcionItem(String descripcionItem) {
        this.descripcionItem = descripcionItem;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(int idCodigo) {
        this.idCodigo = idCodigo;
    }

    public int getOrdenPorMostrar() {
        return ordenPorMostrar;
    }

    public void setOrdenPorMostrar(int ordenPorMostrar) {
        this.ordenPorMostrar = ordenPorMostrar;
    }

    public int getCodigoRelacionMovil() {
        return codigoRelacionMovil;
    }

    public void setCodigoRelacionMovil(int codigoRelacionMovil) {
        this.codigoRelacionMovil = codigoRelacionMovil;
    }
}
