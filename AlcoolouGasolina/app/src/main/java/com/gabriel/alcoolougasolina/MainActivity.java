package com.gabriel.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textoGasolina, textoAlcool;
    private TextView textoREsultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoAlcool = findViewById(R.id.editPrecoAcool);
        textoGasolina = findViewById(R.id.editPrecoGasolina);
        textoREsultado = findViewById(R.id.texto);
    }

    public void calcularPreco(View view){
        String precoAlcool = textoAlcool.getText().toString();
        String  precoGasolina = textoGasolina.getText().toString();
        Boolean validarCAmpos = validarCAmpos(precoAlcool, precoGasolina);

        if (validarCAmpos){
            Double valorAlcool = Double.parseDouble(precoAlcool);
            Double valorGasolina = Double.parseDouble(precoGasolina);
            if ((valorAlcool / valorGasolina) >= 0.7){
                textoREsultado.setText(" O valor da gasolina é melhor!");
            }else {
                textoREsultado.setText(" O valor do alcool é melhor!");

            }

        }else {
            textoREsultado.setText("Preencha os campos primeiro!");
        }
    }

    public Boolean validarCAmpos( String pAlcool, String pGasolina){
        Boolean camposValidados = true;

        if(pAlcool == null || pAlcool.equals("")){
            camposValidados =  false;
        }else if (pGasolina == null || pGasolina.equals("")){
            camposValidados = false;
        }
        return true;
    }
}