package com.gabriel.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private CheckBox boxVerde, boxAzul, boxVermelho;
    private RadioButton sexoMAsculino, sexoFeminino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boxAzul = findViewById(R.id.boxAzul);
        boxVermelho = findViewById(R.id.boxVervelho);
        boxVerde = findViewById(R.id.boxVerde);

        sexoFeminino = findViewById(R.id.sexoFeminino);
        sexoMAsculino = findViewById(R.id.sexoMAsculino);
    }

    public void checkBox(View view){

        if(boxAzul.isChecked()){

        } else if(boxVermelho.isChecked()){

        }else if (boxVerde.isChecked()){
        }
    }

    public void radioButton (View view){
            if(sexoFeminino.isChecked()){
                System.out.println("sexo feminino");


            }else{
                System.out.println("sexo masculino");
            }

    }

    public void enviar (View view){

        EditText campoNome = findViewById(R.id.editNome);
        TextView campoREsultado = findViewById(R.id.resultado);
        TextInputEditText campoEmail = findViewById(R.id.editEmail);

        String nome =  campoNome.getText().toString();
        String email =  campoEmail.getText().toString();
        campoREsultado.setText("Nome : " + nome + " tem o email: " + email);

        checkBox(view);
        radioButton(view);

    }
}