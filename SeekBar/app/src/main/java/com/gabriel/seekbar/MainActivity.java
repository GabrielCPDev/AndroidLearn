package com.gabriel.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBArEscala;
    private TextView textoResultado;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBArEscala = findViewById(R.id.seekBarEscala);
        botao = findViewById(R.id.botao);
        textoResultado = findViewById(R.id.textoResultado);
        seekBArEscala.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    textoResultado.setText("Progresso: " + i + " de " + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textoResultado.setText("ontRAQCKINCHANGED");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textoResultado.setText("onsTOPtRAQCKINCHANGED");

            }
        });
    }

    public void recuperarProgresso (View view) {
        textoResultado.setText("Progresso: " + i + " de " + seekBArEscala.getProgress());

    }
}