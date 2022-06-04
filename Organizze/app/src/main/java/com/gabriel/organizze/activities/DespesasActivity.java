package com.gabriel.organizze.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gabriel.organizze.databinding.ActivityDespesasBinding;

public class DespesasActivity extends AppCompatActivity {

    private ActivityDespesasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDespesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}