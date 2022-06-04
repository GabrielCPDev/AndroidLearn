package com.gabriel.organizze.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gabriel.organizze.databinding.ActivityReceitasBinding;

public class ReceitasActivity extends AppCompatActivity {

    private ActivityReceitasBinding bindig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindig = ActivityReceitasBinding.inflate(getLayoutInflater());
        setContentView(bindig.getRoot());
    }
}