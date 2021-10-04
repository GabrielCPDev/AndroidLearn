package com.gabriel.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.media.ImageReader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ResultadoActivity extends AppCompatActivity {

    private ImageView imagemREsultado;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        imagemREsultado = findViewById(R.id.imageResultado);
        botaoVoltar = findViewById(R.id.botaoVoltar);

        Bundle dados = getIntent().getExtras();
        int numero = dados.getInt("numero");
        if(numero == 0){
            imagemREsultado.setImageResource(R.drawable.moeda_cara);
        }else {
            imagemREsultado.setImageResource(R.drawable.moeda_coroa);
        }

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}