package com.gabriel.organizze.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.gabriel.organizze.databinding.ActivityDespesasBinding;
import com.gabriel.organizze.helper.DateUtil;

public class DespesasActivity extends AppCompatActivity {

    private ActivityDespesasBinding binding;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDespesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setDataAtual();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDataAtual() {
        binding.editData.setText(DateUtil.dataAtual());
    }
}