package com.infotrack.talleres.datos.entidades;

import com.google.gson.annotations.SerializedName;

public class MenuDto {

    @SerializedName(value = "descripcionItem", alternate = {"DescripcionItem"})
    private String descripcionItem;
    @SerializedName(value = "cantidad", alternate = {"Cantidad"})
    private int cantidad;
    @SerializedName(value = "codigo", alternate = {"Codigo"})
    private String codigo;
    @SerializedName(value = "idCodigo", alternate = {"IdCodigo"})
    private int idCodigo;
    @SerializedName(value = "ordenPorMostrar", alternate = {"OrdenPorMostrar"})
    private int ordenPorMostrar;
    @SerializedName(value = "codigoRelacionMovil", alternate = {"CodigoRelacionMovil"})
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
