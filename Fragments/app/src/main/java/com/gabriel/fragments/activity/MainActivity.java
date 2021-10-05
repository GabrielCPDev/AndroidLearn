package com.gabriel.fragments.activity;

import static com.gabriel.fragments.R.id.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gabriel.fragments.R;
import com.gabriel.fragments.fragment.ContatosFragment;
import com.gabriel.fragments.fragment.ConversasFragment;

public class MainActivity extends AppCompatActivity {

    private Button botaoConversa, botaoContato;
    private ContatosFragment contatosFragment;
    private ConversasFragment conversasFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoContato = findViewById(botaoContatos);
        botaoConversa = findViewById(botaoConversas);

        // Configurar objeto para o fragmento

        botaoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contatosFragment = new ContatosFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameCOnteudo, contatosFragment);
                transaction.commit();
            }
        });

        botaoConversa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conversasFragment = new ConversasFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameCOnteudo, conversasFragment);
                transaction.commit();
            }
        });


        // remover sra da action bar
        getSupportActionBar().setElevation(0);

    }
}