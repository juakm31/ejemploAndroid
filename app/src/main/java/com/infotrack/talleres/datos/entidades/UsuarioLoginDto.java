package com.infotrack.talleres.datos.entidades;

import com.google.firebase.database.PropertyName;

import java.util.UUID;

public class UsuarioLoginDto {
    private int idTaller;
    private String nombreUsuario;
    private String contrasena;
    private Integer tipoUsuario;
    private UUID idEmpresa;
    private UUID idUsuario;

    @PropertyName("NombreUsuario")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @PropertyName("NombreUsuario")
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @PropertyName("Contrasena")
    public String getContrasena() {
        return contrasena;
    }

    @PropertyName("Contrasena")
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @PropertyName("TipoUsuario")
    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    @PropertyName("TipoUsuario")
    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @PropertyName("IdTaller")
    public int getIdTaller() {
        return idTaller;
    }

    @PropertyName("IdTaller")
    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    @PropertyName("IdEmpresa")
    public UUID getIdEmpresa() {
        return idEmpresa;
    }

    @PropertyName("IdEmpresa")
    public void setIdEmpresa(UUID idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @PropertyName("IdUsuario")
    public UUID getIdUsuario() {
        return idUsuario;
    }

    @PropertyName("IdUsuario")
    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
