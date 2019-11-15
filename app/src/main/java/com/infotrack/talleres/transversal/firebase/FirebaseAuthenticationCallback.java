package com.infotrack.talleres.transversal.firebase;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaseAuthenticationCallback {
    void autenticacionUsuario(boolean autenticado);
    void cambioEstadoAutenticacion(FirebaseUser usuario);
}
