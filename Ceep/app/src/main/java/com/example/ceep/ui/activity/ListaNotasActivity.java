package com.example.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.R;
import com.example.ceep.dao.NotaDAO;
import com.example.ceep.model.Nota;
import com.example.ceep.ui.adapter.ListaNotasAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    private TextView botaoInsereNota;
    private ListaNotasAdapter adapter;
    private List<Nota> todasNotas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        iniciaCampos();

        botaoInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciaFormularioNota = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivityForResult(iniciaFormularioNota, 1);
            }
        });
    }

    private void iniciaCampos() {
        botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private List<Nota> getNotas() {
        NotaDAO dao = new NotaDAO();
        List<Nota> todasNotas = dao.todos();

        for (int i = 1; i < 10000; i++) {
            dao.insere(new Nota("Primeira Nota", "primeira descrição"));
        }
        return todasNotas;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 2 && data.hasExtra("nota")) {
            Nota notaCriada = (Nota) data.getSerializableExtra("nota");
            adapter.adiciona(notaCriada);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void configuraReciclerView(List<Nota> todasNotas) {
        RecyclerView listaDeNotas = findViewById(R.id.lista_notas_recyclerview);

        configuraAdapter(todasNotas, listaDeNotas);
        configuraLayoutManager(listaDeNotas);
    }

    private void configuraLayoutManager(RecyclerView listaDeNotas) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listaDeNotas.setLayoutManager(layoutManager);
    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaDeNotas) {
        adapter = new ListaNotasAdapter(this, todasNotas);
        listaDeNotas.setAdapter(adapter);
    }
}
