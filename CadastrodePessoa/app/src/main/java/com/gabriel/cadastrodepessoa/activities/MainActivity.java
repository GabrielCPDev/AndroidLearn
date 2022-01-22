package com.gabriel.cadastrodepessoa.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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

    private AdapterListaPessoas adapter;
    private RecyclerView recyclerListaNotas;
    private FloatingActionButton fab;
    private PessoaDAO dao;
    private List<Pessoa> pessoas = new ArrayList();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        configuraDao();
        pessoas = getPessoas();
        configuraRecyclerView(pessoas);
        inicializaCampos();
        configuraFabNovaPessoa();

    }
    private void configuraDao(){
        dao = Room.databaseBuilder(this, CadastroDB.class, NAME_DATABASE)
                .allowMainThreadQueries().build().getPessoaDAO();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (isResultadoComPessoa(requestCode, resultCode, data)) {
            Pessoa PessoaRecebida = (Pessoa) data.getSerializableExtra(AppConstantes.CHAVE_PESSOA);
            adiciona(PessoaRecebida);
        }    }

    private void configuraFabNovaPessoa() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioPessoaActivity();
            }
        });
    }
    private void adiciona(Pessoa pessoa) {
        dao.salva(pessoa);
        adapter.adiciona(pessoa);
    }

    private void inicializaCampos() {
        fab = findViewById(R.id.fab);
        recyclerListaNotas = findViewById(R.id.listPessoa);
    }

    private void abreFormularioPessoaActivity() {
        startActivity(new Intent(this, FormularioPessoaActivity.class));
    }
    public void configuraRecyclerView(List<Pessoa> pessoas){
        configuraAdapter(pessoas);
        adapter.notifyDataSetChanged();
    }

    private void configuraAdapter(List<Pessoa> pessoasList) {
        adapter = new AdapterListaPessoas(pessoasList,this);
    }

    private List<Pessoa> getPessoas() {
        return dao.buscaTodos();
    }

    private boolean temPessoa(Intent data){
        return data.hasExtra(AppConstantes.CHAVE_PESSOA);
    }

    private boolean isCodigoResultadoPessoaCriada(int resultCode){
        return resultCode == AppConstantes.CODIGO_RESULTADO_PESSOA_CRIADA;
    }
    private boolean isCodigoRequisicaoInserePessoa(int resultCode){
        return resultCode == AppConstantes.CODIGO_REQUISICAO_INSERE_PESSOA;
    }

    private boolean isResultadoComPessoa(int requestCode, int resultCode, Intent data) {
        return isCodigoRequisicaoInserePessoa(requestCode) &&
                isCodigoResultadoPessoaCriada(resultCode) &&
                temPessoa(data);
    }

}