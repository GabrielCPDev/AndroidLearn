package com.gabriel.alinhamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gerarNovaFrase (View view){

        List<String> frases = new ArrayList<>(Arrays.asList(" OldBruce","Antigo","Gabriel"));
        int numero = new Random().nextInt(frases.size());
        TextView texto = findViewById(R.id.textREsultado);
        texto.setText(frases.get(numero));
    }

}