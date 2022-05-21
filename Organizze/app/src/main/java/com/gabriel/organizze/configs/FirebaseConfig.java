package com.gabriel.organizze.configs;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseConfig {

    private static FirebaseAuth autenticacao;

    public  static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao.equals(null)){
            autenticacao = FirebaseAuth.getInstance();
            return autenticacao;
        } else {
            return autenticacao;
        }
    }
}
