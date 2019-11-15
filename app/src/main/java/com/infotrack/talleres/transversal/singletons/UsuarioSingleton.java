package com.infotrack.talleres.transversal.singletons;

import android.content.Context;

import com.infotrack.artefactos.utilitarios.base.PreferenciasManager;
import com.infotrack.talleres.presentacion.vistamodelos.UsuarioAutenticadoVM;
import com.infotrack.talleres.transversal.constantes.ConstantesCompartidas;

public class UsuarioSingleton {

    private PreferenciasManager manager;
    private static volatile UsuarioSingleton ourInstance;

    public static UsuarioSingleton obtenerInstancia(Context contexto) {
        if (ourInstance == null) {
            synchronized (UsuarioSingleton.class) {
                if (ourInstance == null) ourInstance = new UsuarioSingleton(contexto);
            }
        }
        return ourInstance;
    }

    private UsuarioSingleton(Context contexto) {
        manager = PreferenciasManager.obtenerInstancia(contexto);
        if (ourInstance != null)
            throw new RuntimeException("Use el método obtenerInstancia() para obtener la instancia única de esta clase");
    }

    public UsuarioAutenticadoVM obtenerUsuario() {
        return manager.obtener(ConstantesCompartidas.LLAVE_PREFERENCIA_SINGLETON_USUARIO, UsuarioAutenticadoVM.class);
    }

    public void almacenarUsuario(UsuarioAutenticadoVM usuarioAutenticadoVM) {
        manager.almacenar(ConstantesCompartidas.LLAVE_PREFERENCIA_SINGLETON_USUARIO, usuarioAutenticadoVM);
    }

   /* public void actualizarIdentificacion(String identificacion) {
        InformacionUsuarioAutenticadoVM usuarioAutenticado = obtenerUsuario();
        usuarioAutenticado.setIdentificacion(identificacion);
        almacenarUsuario(usuarioAutenticado);
    }
    public void actualizarNombreUsuario(String nombreUsuario) {
        InformacionUsuarioAutenticadoVM usuarioAutenticado = obtenerUsuario();
        usuarioAutenticado.setNombreUsuario(nombreUsuario);
        almacenarUsuario(usuarioAutenticado);
    }
    public void actualizarCorreoElectronico(String correoElectronico) {
        InformacionUsuarioAutenticadoVM usuarioAutenticado = obtenerUsuario();
        usuarioAutenticado.setCorreoElectronico(correoElectronico);
        almacenarUsuario(usuarioAutenticado);
    }*/
}
