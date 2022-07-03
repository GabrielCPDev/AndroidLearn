package com.gabriel.whatsapp_clone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gabriel.whatsapp_clone.R;
import com.gabriel.whatsapp_clone.configuration.FirebaseConfig;
import com.gabriel.whatsapp_clone.databinding.ActivityLoginBinding;
import com.gabriel.whatsapp_clone.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Usuario usuario;
    private FirebaseAuth auth;
    private OnCompleteListener<AuthResult> authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        goToCadastro();
        setBtnLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
    }

    private void setBtnLogin() {
        binding.btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar(
                   binding.textInputEmail.getEditText().getText().toString(),
                   binding.textInputSenha.getEditText().getText().toString()
                );
            }
        });
    }

    private void logar(String email, String senha) {
                if(emailESenhaIsEmpty(email, senha)){
                    addAuthListener();
                    auth.signInWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(authListener);
                }

    }

    private void addAuthListener() {
        authListener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser currentUser = auth.getCurrentUser();
                    getTelaPrincipal(currentUser);
                } else {
                    String message = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        message = "Email ou senha incorretos!";
                    } catch (FirebaseAuthInvalidUserException e){
                        message = "Usuário não cadastrado!";
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(
                            getApplicationContext(),
                            message,
                            Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private void getTelaPrincipal(FirebaseUser currentUser) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("usuarioLogado", currentUser);
        startActivity(intent);
        finish();
    }

    private Boolean emailESenhaIsEmpty(String email, String senha){
        boolean isValid = true;
        if (email.isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.textInputEmail), Toast.LENGTH_LONG).show();
            isValid = false;
        } else if (senha.isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.textInputSenha), Toast.LENGTH_LONG).show();
            isValid = false;
        }
        return isValid;
    }

    private void goToCadastro(){
        binding.textCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
            }
        });
    }
}