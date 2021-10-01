package com.gabriel.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirDialog (View view){

        // instanciar o dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        // configurar o titulop e a mensagem
        dialog.setTitle("titulo da dialog");
        dialog.setMessage("MENSAGEM DA DIALOG");
        // configurar acoes
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"executa acao do botao sim", Toast.LENGTH_LONG).show();
            }
        });

        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"acao ao clicar em não", Toast.LENGTH_SHORT).show();
            }
        });

    }
}