package com.infotrack.talleres.transversal.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthentication {

    private static final FirebaseAuthentication ourInstance = new FirebaseAuthentication();

    public static FirebaseAuthentication getInstance() {
        return ourInstance;
    }

    private FirebaseAuthentication() {
        mAuth = FirebaseAuth.getInstance();
    }

    private FirebaseAuth mAuth;
    private FirebaseAuthenticationCallback callback;

    public void loginTokenPersonalizado(FirebaseAuthenticationCallback listener, String tokenPersonalizado) {

        this.callback = listener;

        mAuth.signInWithCustomToken(tokenPersonalizado)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) subscribeStateListener();
                        callback.autenticacionUsuario(task.isSuccessful());
                    }
                });
    }

    public void logOut() {
        mAuth.signOut();
    }

    private void subscribeStateListener() {
        mAuth.addAuthStateListener(authStateListener);
    }

    public void subscribeStateListener(FirebaseAuthenticationCallback listener) {
        this.callback = listener;
        subscribeStateListener();
    }

    public void unSubscribeStateListener(FirebaseAuthenticationCallback listener) {
        this.callback = listener;
        mAuth.removeAuthStateListener(authStateListener);
    }

    public FirebaseUser obtenerUsuarioFirebase() {
        return mAuth.getCurrentUser();
    }

    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            callback.cambioEstadoAutenticacion(firebaseAuth.getCurrentUser());
        }
    };
}
