package com.gabriel.organizze.configs;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseConfig {

    private static FirebaseAuth autenticacao;

    public static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao == null){

            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
