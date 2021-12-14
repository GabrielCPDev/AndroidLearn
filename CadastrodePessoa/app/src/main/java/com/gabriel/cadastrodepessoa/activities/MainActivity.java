package com.gabriel.cadastrodepessoa.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.activities.adapters.AdapterListaPessoas;
import com.gabriel.cadastrodepessoa.dao.PessoaDAO;
import com.gabriel.cadastrodepessoa.entities.Pessoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton fab;
    private PessoaDAO dao = new PessoaDAO();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaCampos();
        configuraFabNovaPessoa();
    }
    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraFabNovaPessoa() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioPessoaActivity();
            }
        });
    }

    private void inicializaCampos() {
        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listPessoa);
    }

    private void abreFormularioPessoaActivity() {
        startActivity(new Intent(this, FormularioPessoaActivity.class));
    }

    private void configuraLista() {
        List<Pessoa> pessoas = dao.listaPessoas();
        AdapterListaPessoas adapter = new AdapterListaPessoas(pessoas, this);
        listView.setAdapter(adapter);
    }


}