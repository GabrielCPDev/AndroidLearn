package com.gabriel.cadastrodepessoa.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.activities.adapters.AdapterListaPessoas;
import com.gabriel.cadastrodepessoa.dao.PessoaDAO;
import com.gabriel.cadastrodepessoa.db.CadastroDB;
import com.gabriel.cadastrodepessoa.entities.Pessoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AppConstantes{

    private FloatingActionButton fab;
    private PessoaDAO dao;
    private RecyclerView recyclerView;
    private AdapterListaPessoas adapter;
    private List<Pessoa> pessoas = new ArrayList();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaCampos();

    }

    @Override
    protected void onStart() {
        super.onStart();
        configuraDao();
        pessoas = getPessoas();
        configuraAdapter();
        configuraRecyclerView();
        configuraFabNovaPessoa();

    }
    private void configuraDao(){
        dao = Room.databaseBuilder(this, CadastroDB.class, NAME_DATABASE)
                .allowMainThreadQueries().build().getPessoaDAO();
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
        recyclerView = findViewById(R.id.listPessoa);
    }

    private void abreFormularioPessoaActivity() {
        startActivity(new Intent(this, FormularioPessoaActivity.class));
    }
    public void configuraRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter( this.adapter );
    }

    private void configuraAdapter() {
        this.adapter = new AdapterListaPessoas(pessoas,this);

    }

    private List<Pessoa> getPessoas() {
        return this.dao.buscaTodos();
    }

}