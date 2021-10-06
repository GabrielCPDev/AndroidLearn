package com.gabriel.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button botaoAbrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoAbrir = findViewById(R.id.botaoAbrir);
        botaoAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Botão precionado", Snackbar.LENGTH_LONG).setAction(
                        "Confirmar", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                botaoAbrir.setText("Botão abrir alterado");
                            }
                        }
                ).show();
            }
        });
    }
}