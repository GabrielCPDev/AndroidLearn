package com.gabriel.organizze.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gabriel.organizze.R;
import com.gabriel.organizze.activities.CadastroActivity;
import com.gabriel.organizze.activities.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void entrar(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    public void cadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}