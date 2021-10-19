package com.gabriel.cadastrodepessoa.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.dao.PessoaDAO;
import com.gabriel.cadastrodepessoa.entities.Pessoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton fab;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FormularioPessoaActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PessoaDAO dao = new PessoaDAO();
        listView = findViewById(R.id.listPessoa);

        List<Pessoa> pessoas = dao.listaPessoas();
        listView.setAdapter(new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoas));
    }
}