package com.gabriel.organizze.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gabriel.organizze.R;
import com.gabriel.organizze.configs.FirebaseConfig;
import com.gabriel.organizze.databinding.ActivityLoginBinding;
import com.gabriel.organizze.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEmail = binding.editEmail.getText().toString();
                String textoSenha = binding.editSenha.getText().toString();

                if(validaCamposLogin(textoEmail, textoSenha)){
                    fazLogin(textoEmail, textoSenha);
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.campos_nao_preenchidos),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private Boolean validaCamposLogin(String email, String senha){
        return !email.isEmpty() && !senha.isEmpty();
    }

    private void fazLogin(String email, String senha) {
        autenticacao = FirebaseConfig.getFirebaseAutenticacao();
        getUsuario(email, senha);

        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    getTelaPrincipal();
                } else {
                    String message = "";
                    try {
                        throw task.getException();
                    }
                    catch (FirebaseAuthInvalidUserException e){
                        message = "Usu??rio n??o existe!";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        message = "Email ou senha inv??lidos!";
                    }
                    catch (Exception e){
                        message = "Email ou senha inv??lidos: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(
                            getApplicationContext(),
                            message,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void getTelaPrincipal(){
        startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
        finish();
    }

    private void getUsuario(String email, String senha) {
        usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
    }
}