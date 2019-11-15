package com.infotrack.talleres.presentacion.componentes.autenticacion;

import com.infotrack.artefactos.utilitarios.base.PresentadorBase;

public interface IniciarSesionContrato extends PresentadorBase.VistaBase {

    void loginFirebaseInvalido();
    void navegarSiguienteActividad();
    void usuarioInvalido(String mensaje);
    void errorServicioAutenticacion(Throwable throwable);

}
