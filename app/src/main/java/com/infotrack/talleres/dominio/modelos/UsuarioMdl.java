package com.infotrack.talleres.dominio.modelos;

public class UsuarioMdl {
    private String Avatar;
    private int IdPerfil;
    private String IdUsuario;
    private String Nombre;
    private String Telefono;

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public int getIdPerfil() {
        return IdPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        IdPerfil = idPerfil;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
