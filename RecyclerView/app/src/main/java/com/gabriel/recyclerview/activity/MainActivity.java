package com.gabriel.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gabriel.recyclerview.R;
import com.gabriel.recyclerview.RecyclerItemClickListener;
import com.gabriel.recyclerview.adapter.Adapter;
import com.gabriel.recyclerview.model.Filme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Filme> filmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        // cofigurar adapter
        this.criarFilmes();
        Adapter adapter = new Adapter(filmes);

        // configurar recyclerView

        RecyclerView.LayoutManager layoutMAnager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutMAnager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        // evento de click
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Filme filme = filmes.get(position);
                                Toast.makeText(getApplicationContext(),"Item precionado: " + filme.getTitulo() , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Filme filme = filmes.get(position);
                                Toast.makeText(getApplicationContext(),"Item precionado clique longo: " + filme.getTitulo() , Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

    }

    public void criarFilmes (){
        Filme filme = new Filme("titulo", "genero ", "ano");
        Filme filme1 = new Filme("titulo", "genero ", "ano");
        Filme filme2 = new Filme("titulo", "genero ", "ano");
        Filme filme3 = new Filme("titulo", "genero ", "ano");
        Filme filme4 = new Filme("titulo", "genero ", "ano");
        Filme filme5 = new Filme("titulo", "genero ", "ano");
        Filme filme6 = new Filme("titulo", "genero ", "ano");
        Filme filme7 = new Filme("titulo", "genero ", "ano");
        Filme filme8 = new Filme("titulo", "genero ", "ano");
        Filme filme9 = new Filme("titulo", "genero ", "ano");
        Filme filme10 = new Filme("titulo", "genero ", "ano");
        Filme filme11 = new Filme("titulo", "genero ", "ano");
        this.filmes.addAll(Arrays.asList(filme, filme1,filme2,filme3, filme4,filme6, filme5, filme7,filme8,filme9,filme10,filme11));
    }
}