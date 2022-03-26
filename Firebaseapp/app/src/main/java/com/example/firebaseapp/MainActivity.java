package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference usuarios = referencia.child("usuarios");
        DatabaseReference produtos = referencia.child("produtos");

        //Cadastrando usuario
//        auth.createUserWithEmailAndPassword(
//                "gabrielcpcontato@gmailc.com",
//                "123456")
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Log.i("SUCESSSO", "onComplete: ");
//                        }
//                        else {
//                            Log.i("ERRO", "cadastro nao sucedido: ");
//
//                        }
//                    }
//                });

        //Verificando usuario logado
        FirebaseUser usuarioLogado = auth.getCurrentUser();
        if(usuarioLogado != null){
            Log.i("SUCESSO", "usuario logado");
        }else {
            Log.i("FALHA ", "usuario nao logado");

        }

//        Usuario u = new Usuario("Naruto", "Usumaki",17);
//        Produto p = new Produto("Lamen","Ichiraku", 10.0 );
//        usuarios.child("002").setValue(u);
//        produtos.child("001").setValue(p);

//        usuarios.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.i("FIREBASE", snapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
}