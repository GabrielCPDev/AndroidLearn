package com.gabriel.cardview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gabriel.cardview.R;
import com.gabriel.cardview.model.Postagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPostagem;
    private List<Postagem> postagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPostagem = findViewById(R.id.recyclerView);

        //Definir layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPostagem.setLayoutManager(layoutManager);
        this.prepararPostagens();
        //Definir adapter
        PostagemAdapter adapter = new PostagemAdapter(postagens);
        recyclerViewPostagem.setAdapter(adapter);
    }

    public void prepararPostagens(){
        Postagem p = new Postagem("Naruto", "seria melhor com o sasuke!!", R.drawable.imagem2);
        Postagem p3 = new Postagem("Sakura", "seria melhor com o sasuke!!", R.drawable.imagem3);
        Postagem p2 = new Postagem("Hinata", "Natuto!!", R.drawable.imagem4);
        Postagem p4 = new Postagem("Oroshimaru", "sasuke S2!!", R.drawable.imagem1);
        postagens.addAll(Arrays.asList(p,p2,p3,p4));
    }
}