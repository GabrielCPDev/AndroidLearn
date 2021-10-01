package com.gabriel.toggleswit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Switch switchSenha;
    private ToggleButton toogleButton;
    private TextView resultado;
    private Button botaoEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchSenha = findViewById(R.id.switchSenha);
        toogleButton = findViewById(R.id.toggleButton);
        botaoEnviar = findViewById(R.id.botao);
        resultado = findViewById(R.id.resultado);

        adicionarListener();
    }

    public void adicionarListener (){
        switchSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    resultado.setText("ligadpo");
                }else{
                    resultado.setText("desligado" +
                            "");
                }
            }
        });
    }

    public void enviar (*View view){

    }
}