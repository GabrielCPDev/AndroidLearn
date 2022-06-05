package com.gabriel.organizze.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gabriel.organizze.R;
import com.gabriel.organizze.configs.FirebaseConfig;
import com.gabriel.organizze.databinding.ActivityCadastroBinding;
import com.gabriel.organizze.helper.Base64Custom;
import com.gabriel.organizze.models.Usuario;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FirebaseApp.initializeApp(this);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setBotaoCadastrar();

    }

    private Boolean validaCamposCadastro(String nome, String email, String senha){
        return !nome.isEmpty() && !email.isEmpty() && !senha.isEmpty();
    }
    private void setBotaoCadastrar(){
        binding.btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNome = binding.editNome.getText().toString();
                String textoEmail = binding.editEmail.getText().toString();
                String textoSenha = binding.editSenha.getText().toString();

                if(validaCamposCadastro(textoNome, textoEmail, textoSenha)){
                    usuario = new Usuario(null, textoNome, textoEmail, textoSenha);
                    cadastrarUsuario(usuario);
                } else {
                    Toast.makeText(CadastroActivity.this, getString(R.string.campos_nao_preenchidos), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cadastrarUsuario(Usuario usuario){

        autenticacao = FirebaseConfig.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful() ){
                    usuario.setIdUsuario(Base64Custom.codificarBase64(usuario.getEmail()));
                    usuario.salvar();
                    finish();
                }else {
                    String message = "";
                    try {
                        throw task.getException();
                    }
                    catch (FirebaseAuthWeakPasswordException e) {
                        message = "Senha fraca para um caralho!";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        message = "digite um email válido";
                    }
                    catch (FirebaseAuthUserCollisionException e){
                        message = "Essa conta já foi cadastrada!";
                    }
                    catch (Exception e){
                        message = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this,
                            message,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}