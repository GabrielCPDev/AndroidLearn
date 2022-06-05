package com.gabriel.organizze.configs;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {

    private static FirebaseAuth autenticacao;
    private static DatabaseReference firebase;

    public static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao == null){

            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

    public static DatabaseReference getFirebaseDatabase(){
        if (firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }
}
