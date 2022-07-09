package com.gabriel.cadastrodepessoa.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.activities.adapters.AdapterListPerson;
import com.gabriel.cadastrodepessoa.dao.PersonDAO;
import com.gabriel.cadastrodepessoa.db.RegisterDB;
import com.gabriel.cadastrodepessoa.entities.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AppConstants {

    private FloatingActionButton fab;
    private PersonDAO dao;
    private RecyclerView recyclerView;
    private AdapterListPerson adapter;
    private List<Person> people = new ArrayList();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFields();

    }

    @Override
    protected void onStart() {
        super.onStart();
        configDao();
        people = getPeoples();
        configAdapter();
        configRecyclerView();
        configFabNewPerson();

    }
    private void configDao(){
        dao = Room.databaseBuilder(this, RegisterDB.class, NAME_DATABASE)
                .allowMainThreadQueries().build().getPersonDAO();
    }
    private void configFabNewPerson() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormPersonActivity();
            }
        });
    }
    private void initFields() {
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.listPessoa);
    }

    private void openFormPersonActivity() {
        startActivity(new Intent(this, FormularioPessoaActivity.class));
    }
    public void configRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter( this.adapter );
    }

    private void configAdapter() {
        this.adapter = new AdapterListPerson(people,this);

    }

    private List<Person> getPeoples() {
        return this.dao.findAll();
    }

}