package com.gabriel.cadastrodepessoa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.test.espresso.remote.EspressoRemoteMessage;

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
    private int positionReceived = INVALID_POSITION;

    private PersonDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pessoa);
        setTitle(getString(R.string.title_appbar_new_person));
        initFields();
        getIntentToUpdatePerson();
        configSaveButton();
        dao = Room.databaseBuilder(this, RegisterDB.class, NAME_DATABASE)
                .allowMainThreadQueries().build().getPersonDAO();
    }

    private void getIntentToUpdatePerson() {
        Intent dataReceived = getIntent();
        if(dataReceived.hasExtra(KEY_PERSON)){
            setTitle(R.string.title_appbar_update_person);
            Person personReceived = (Person) dataReceived.getSerializableExtra(KEY_PERSON);
            positionReceived = dataReceived.getIntExtra(KEY_POSITION, INVALID_POSITION);
            initFieldsWithPerson(personReceived);
        }
    }

    private void initFieldsWithPerson(Person person){
        name.setText(person.getName());
        adress.setText(person.getAddress());
        age.setText(String.valueOf(person.getAge()));
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
        if(positionReceived != -1){
            int id = positionReceived + 1;
            person.setId(Long.parseLong(String.valueOf(id)));
            dao.update(person);
        } else {
        dao.save(person);
        }
        String message = getMessageSuccess();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    private String getMessageSuccess() {
        String message = "";
        if(positionReceived != -1) {
        message = getString(R.string.message_person_updated);
        } else {
            message = getString(R.string.message_person_saved);
        } return message;
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