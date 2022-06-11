package com.gabriel.organizze.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gabriel.organizze.R;
import com.gabriel.organizze.activities.CadastroActivity;
import com.gabriel.organizze.activities.LoginActivity;
import com.gabriel.organizze.configs.FirebaseConfig;
import com.gabriel.organizze.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificaUsuarioLogado();
    }

    public void entrar(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    public void verificaUsuarioLogado(){
        autenticacao = FirebaseConfig.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null){
            getTelaPrincipal();
        }
    }

    private void getTelaPrincipal() {
        startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
    }

    public void cadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}