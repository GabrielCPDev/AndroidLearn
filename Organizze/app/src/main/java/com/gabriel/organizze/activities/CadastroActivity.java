package com.gabriel.organizze.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gabriel.organizze.R;
import com.gabriel.organizze.configs.FirebaseConfig;
import com.gabriel.organizze.databinding.ActivityCadastroBinding;
import com.gabriel.organizze.models.Usuario;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
                    usuario = new Usuario(textoNome, textoEmail, textoSenha);
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
                if(task.isComplete()){
                    Toast.makeText(
                            CadastroActivity.this,
                            getString(R.string.sucesso_cadastro_usuario),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                            CadastroActivity.this,
                            getString(R.string.erro_cadastro_usuario),
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}