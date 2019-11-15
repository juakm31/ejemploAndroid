package com.infotrack.talleres.datos.entidades;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

public class UsuarioAutenticadoDto {

    @SerializedName(value = "idTaller", alternate = {"IdTaller"})
    private int idTaller;
    @SerializedName(value = "identificacion", alternate = {"Identificacion"})
    private String identificacion;
    @SerializedName(value = "nombreUsuario", alternate = {"NombreUsuario"})
    private String nombreUsuario;
    @SerializedName(value = "login", alternate = {"Login"})
    private String login;
    @SerializedName(value = "correoElectronico", alternate = {"CorreoElectronico"})
    private String correoElectronico;
    @SerializedName(value = "idUsuario", alternate = {"IdUsuario"})
    private String idUsuario;
    @SerializedName(value = "idEmpresa", alternate = {"IdEmpresa"})
    private String idEmpresa;
    @SerializedName(value = "token", alternate = {"Token"})
    private String token;
    @SerializedName(value = "taller", alternate = {"Taller"})
    private String taller;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
