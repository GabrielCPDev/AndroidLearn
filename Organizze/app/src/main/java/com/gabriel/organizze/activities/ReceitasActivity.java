package com.gabriel.organizze.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gabriel.organizze.R;
import com.gabriel.organizze.configs.FirebaseConfig;
import com.gabriel.organizze.databinding.ActivityReceitasBinding;
import com.gabriel.organizze.helper.Base64Custom;
import com.gabriel.organizze.helper.DateUtil;
import com.gabriel.organizze.models.Movimentacao;
import com.gabriel.organizze.models.Usuario;
import com.google.android.gms.common.util.DataUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {

    private ActivityReceitasBinding binding;
    private Movimentacao movimentacao;
    private DatabaseReference database = FirebaseConfig.getFirebaseDatabase();
    private FirebaseAuth autenticacao = FirebaseConfig.getFirebaseAutenticacao();
    private final String tipoReceita = "";
    private Double receitaTotal;
    private Double receitaAtualizada;
    private DatabaseReference usuariosRef = database.child("usuarios")
            .child(Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail()));

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReceitasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setDataAtual();
        setFabClickSalvarReceita();
        getReceitaTotal();
    }

    private void getReceitaTotal() {
        usuariosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setFabClickSalvarReceita() {
        binding.fabSalvar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (isValidCamposReceita()) {
                    movimentacaoInstance();
                    receitaAtualizada = movimentacao.getValor() + receitaTotal;
                    movimentacao.salvar();
                    atualizarReceita(receitaAtualizada);
                }
            }
        });
    }

    private void atualizarReceita(Double receitaAtualizada) {
        usuariosRef.child("receitaTotal").setValue(receitaAtualizada);
    }

    private void movimentacaoInstance() {
        movimentacao = new Movimentacao(
                binding.editData.getText().toString(),
                binding.editCategoria.getText().toString(),
                binding.editDescricao.getText().toString(),
                tipoReceita,
                Double.parseDouble(binding.editValor.getText().toString())
        );
    }

    private boolean isValidCamposReceita() {
        try {
            if (!binding.editValor.getText().toString().equals("") &&
                    !binding.editCategoria.getText().toString().equals("") &&
                    !binding.editDescricao.getText().toString().equals("")) {
                if (!(Double.parseDouble(binding.editValor.getText().toString()) <= 0)) {
                    return true;
                } else {
                    Toast.makeText(this, R.string.erro_cadastro_valor, Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(this, R.string.erro_cadastro_despesa, Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.erro_formato_preco, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            finish();
            throw new NumberFormatException();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDataAtual() {
        binding.editData.setText(DateUtil.dataAtual());
    }
}