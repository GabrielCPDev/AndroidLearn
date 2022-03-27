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
import com.google.firebase.database.Query;
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

        Query usuarioPesquisa = usuarios.orderByChild("nome")
                .equalTo("Gabriel");

        Query usuarioPesquisaLimitada = usuarios.orderByKey()
                .limitToFirst(2);


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
//        FirebaseUser usuarioLogado = auth.getCurrentUser();
//        if(usuarioLogado != null){
//            Log.i("SUCESSO", "usuario logado");
//        }else {
//            Log.i("FALHA ", "usuario nao logado");
//
//        }

        //Deslogar usuario
        auth.signOut();

        //Logando usuario
//        auth.signInWithEmailAndPassword(
//                "gabrielcpcontato@gmailc.com",
//                "123456")
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                        Log.i("SUCESSO", "usuario fez login");
//                        }else {
//                        Log.i("ERRO", "usuario nao fez login");
//                        }
//                    }
//                });

        Usuario u = new Usuario("Gabriel", "Carvalho",17);
        Usuario u1 = new Usuario("Marcelo", "Carvalho",20);
        Usuario u2 = new Usuario("Andre", "Carvalho",18);
//        Produto p = new Produto("Lamen","Ichiraku", 10.0 );
//        usuarios.child("002").setValue(u);
//        produtos.child("001").setValue(p);
//        usuarios.push().setValue(u);
        usuarios.push().setValue(u1);
        usuarios.push().setValue(u2);

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