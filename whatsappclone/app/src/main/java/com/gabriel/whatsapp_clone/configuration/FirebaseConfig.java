package com.gabriel.whatsapp_clone.configuration;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {

    private static DatabaseReference database;
    private static FirebaseAuth auth;

    public static DatabaseReference getDatabase(){
        if (database == null){
            database =  FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }
    public static FirebaseAuth getFirebaseAuth(){
        if (auth == null){
            auth =  FirebaseAuth.getInstance();
        }
        return auth;
    }
}
