package com.gabriel.pedrapapeloutesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

    public void selecionadoPedra(View view) {
        this.opcaoSelecionada("pedra");
    }

    public void selecionadoTesoura(View view) {
        this.opcaoSelecionada("tesoura");
    }

    public void selecionadoPapel(View view) {
        this.opcaoSelecionada("papel");
    }

    public void opcaoSelecionada(String escolhaUsuario) {
        ImageView imagemREsultado = findViewById(R.id.imagemResultado);
        TextView textoResultado = findViewById(R.id.textoResuultado);
        int numero = new Random().nextInt(3);
        List<String> opcoes = new ArrayList<>(Arrays.asList("pedra", "papel", "tesoura"));
        String escolhaApp = opcoes.get(numero);

        switch (escolhaApp) {
            case "pedra":
                imagemREsultado.setImageResource(R.drawable.pedra);
                break;
            case "tesoura":
                imagemREsultado.setImageResource(R.drawable.tesoura);
                break;
            case "papel":
                imagemREsultado.setImageResource(R.drawable.papel);
        }

        if (
                (escolhaApp == "tesoura" && escolhaUsuario == "papel") ||
                (escolhaApp == "papel" && escolhaUsuario == "pedral") ||
                (escolhaApp == "pedra" && escolhaUsuario == "tesoura")) {
            textoResultado.setText("Você perdeu :(");


        } else if (
                (escolhaUsuario == "tesoura" && escolhaApp == "papel") ||
                (escolhaUsuario == "papel" && escolhaApp == "pedral") ||
                (escolhaUsuario == "pedra" && escolhaApp == "tesoura")) {
            textoResultado.setText("Você ganhou!! :)");

        } else {
                textoResultado.setText("Empate!!!!");
        }
    }
}