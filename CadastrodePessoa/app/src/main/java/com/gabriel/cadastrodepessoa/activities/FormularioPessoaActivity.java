package com.gabriel.cadastrodepessoa.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.dao.PersonDAO;
import com.gabriel.cadastrodepessoa.db.RegisterDB;
import com.gabriel.cadastrodepessoa.entities.Person;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FormularioPessoaActivity extends AppCompatActivity implements AppConstants {

    private Button btnSave;
    private EditText name, age, adress;
    private String errorMessage = "";
    private PersonDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pessoa);
        setTitle(getString(R.string.title_appbar_new_person));
        initFields();
        configSaveButton();
        dao = Room.databaseBuilder(this, RegisterDB.class, NAME_DATABASE)
                .allowMainThreadQueries().build().getPersonDAO();
    }

    private void initFields() {
        btnSave = findViewById(R.id.botao_cadastrar);
        name = findViewById(R.id.activity_formulario_pessoa_nome);
        adress = findViewById(R.id.activity_formulario_pessoa_endereco);
        age = findViewById(R.id.activity_formulario_pessoa_idade);
    }

    private void configSaveButton() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidFields(name, adress, age)) {
                    Person person = registerPerson();
                    savePeople(person);
                    finish();
                } else {
                    Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void savePeople(Person person) {
        dao.save(person);
    }

    private Person registerPerson() {
        Person person = new Person();
        person.setName(name.getText().toString());
        person.setAddress(adress.getText().toString());
        person.setAge((Integer.parseInt(age.getText().toString())));

        return person;
    }


    private boolean isValidFields(EditText nome, EditText endereco, EditText idade) {
        List<String> erros = new ArrayList<>();

        if (nome.getText().toString().equals("") || nome.getText().toString().equals(null)) {
            erros.add("nome");
        }
        if (endereco.getText().toString().equals("") || endereco.getText().toString().equals(null)) {
            erros.add("endereço");
        }
        if (idade.getText().toString().equals("") || idade.getText().toString().equals(null)) {
            erros.add("idade");
        }

        if (!erros.isEmpty()) {
            getErrorMessage(erros);
            return false;
        } else {
            return true;
        }
    }

    public void getErrorMessage(List<String> campos) {
        String mensage = "Preenchimento de campo: ";
        for (String i : campos) {
            mensage += i + ", ";
        }
        mensage += " é obrigatório";
        this.errorMessage = mensage;
    }
}