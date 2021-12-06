package com.example.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textoResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    private SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.botaoSalvar);
        editNome = findViewById(R.id.inputNome);
        textoResultado = findViewById(R.id.textNome);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                if (editNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Preencha o nome", Toast.LENGTH_LONG).show();
                }else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    textoResultado.setText("Olá, " + nome);
                }
            }
        });
        if(preferences.contains("nome")){
            String nome = preferences.getString("nome","usuário não definido");
            textoResultado.setText("Olá, " + nome);

        }else {
            textoResultado.setText("Olá, usuario não definido");
        }
    }
}