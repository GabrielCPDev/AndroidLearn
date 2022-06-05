package com.gabriel.organizze.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.gabriel.organizze.databinding.ActivityDespesasBinding;
import com.gabriel.organizze.helper.DateUtil;
import com.gabriel.organizze.models.Movimentacao;

import java.time.LocalDate;

public class DespesasActivity extends AppCompatActivity {

    private ActivityDespesasBinding binding;
    private Movimentacao movimentacao;
    private final String tipoDespesa = "";

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDespesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setDataAtual();
        setFabClickSalvarDespesa();
    }

    private void setFabClickSalvarDespesa() {
        binding.fabSalvarDespesa.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                movimentacaoInstance();
                movimentacao.salvar();
            }
        });
    }
    private void movimentacaoInstance() {
        double valor = Double.parseDouble(binding.editValor.getText().toString());
        movimentacao = new Movimentacao(
                binding.editData.getText().toString(),
                binding.editCategoria.getText().toString(),
                binding.editDescricao.getText().toString(),
                tipoDespesa,
                valor
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDataAtual() {
        binding.editData.setText(DateUtil.dataAtual());
    }
}