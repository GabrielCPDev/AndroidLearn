package com.gabriel.whatsapp_clone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gabriel.whatsapp_clone.R;
import com.gabriel.whatsapp_clone.configuration.FirebaseConfig;
import com.gabriel.whatsapp_clone.databinding.ActivityCadastroBinding;
import com.gabriel.whatsapp_clone.models.Usuario;
import com.gabriel.whatsapp_clone.utils.Base64Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private FirebaseAuth auth;

    private Usuario usuario;
    private OnCompleteListener<AuthResult> listenerUsuarioAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setCadastroUsuario();
    }

    private void setCadastroUsuario() {
        binding.btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        if(validaCamposPreenchidos()){
            usuario = new Usuario(
                    binding.textInputNome.getEditText().getText().toString(),
                    binding.textInputEmail.getEditText().getText().toString(),
                    binding.textInputSenha.getEditText().getText().toString()
            );
            salvaUsuario(usuario);
        }
            }
        });

    }

    private void salvaUsuario(Usuario usuario) {
        if (auth == null){
            auth = FirebaseConfig.getFirebaseAuth();
        }
        addAuthListenerCadastro();
        auth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(this, listenerUsuarioAuth);
    }

    private void addAuthListenerCadastro() {
        listenerUsuarioAuth = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), getString(R.string.sucessoCadastroUsuario), Toast.LENGTH_LONG).show();
                    finish();
                    salvaUsuarioNoBanco();
                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthEmailException e){
                        excecao = getString(R.string.erroCadastroUsuarioEmail);
                    } catch (FirebaseAuthUserCollisionException e){
                        excecao = getString(R.string.erroCadastroUsuarioExistente);
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = getString(R.string.erroCadastroUsuarioSenha);
                    } catch (Exception e){
                        excecao = getString(R.string.erroCadastroUsuario);
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), excecao, Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private void salvaUsuarioNoBanco() {
        try {
            usuario.setId(Base64Utils.codificarBase64(usuario.getEmail()));
            usuario.salvar();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean validaCamposPreenchidos() {
        boolean result = true;
        String message = "Campos: ";
        if (binding.textInputSenha.getEditText().getText().toString().isEmpty()){
            message += "Senha,";
            result = false;
        } else if (binding.textInputEmail.getEditText().getText().toString().isEmpty()){
            message += "Email,";
            result = false;
        } else if (binding.textInputNome.getEditText().getText().toString().isEmpty()){
            result = false;
            message += "Nome,";
        }
        if (result == false){
            Toast.makeText(getApplicationContext(), message += " precisam ser preenchidos!", Toast.LENGTH_LONG).show();
        }
        return result;
    }
}