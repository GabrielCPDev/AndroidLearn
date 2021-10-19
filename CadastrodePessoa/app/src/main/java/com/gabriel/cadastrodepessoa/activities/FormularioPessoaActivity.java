package com.gabriel.cadastrodepessoa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.dao.PessoaDAO;
import com.gabriel.cadastrodepessoa.entities.Pessoa;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormularioPessoaActivity extends AppCompatActivity {

    private Button botaoSalvar;
    private EditText nome, idade, endereco;
    private String mensagemDeErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pessoa);

        PessoaDAO dao = new PessoaDAO();
        botaoSalvar = findViewById(R.id.botao_cadastrar);
        nome = findViewById(R.id.activity_formulario_pessoa_nome);
        endereco = findViewById(R.id.activity_formulario_pessoa_endereco);
        idade = findViewById(R.id.activity_formulario_pessoa_idade);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            botaoSalvar.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    if(validaDados(nome, endereco, idade)){
                        Map<String, String> camposPessoa = new HashMap<>();
                        camposPessoa.put("nome", nome.getText().toString());
                        camposPessoa.put("endereco",endereco.getText().toString());
                        camposPessoa.put("idade", idade.getText().toString());

                        Pessoa pessoa = constroePessoa(camposPessoa);
                        dao.salva(pessoa);

                        //startActivity(new Intent(FormularioPessoaActivity.this, MainActivity.class));
                        finish();
                    }else{
                        Snackbar.make(view, mensagemDeErro, Snackbar.LENGTH_LONG).show();
                    }

                }
            });
        }

    }

    private Pessoa constroePessoa(Map<String , String> campos){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(campos.get("nome").toString());
        pessoa.setEnderedo(campos.get("endereco").toString());
        pessoa.setIdade((Integer.parseInt(campos.get("idade").toString())) );

        return pessoa;
    }

    private boolean validaDados(EditText nome, EditText endereco, EditText idade){
        List<String> erros = new ArrayList<>();

        if(nome.getText().toString().equals("") || nome.getText().toString().equals(null)){
            erros.add("nome");
        }if(endereco.getText().toString().equals("") || endereco.getText().toString().equals(null)){
            erros.add("endereco");
        }if(idade.getText().toString().equals("") || idade.getText().toString().equals(null)){
            erros.add("idade");
        }

        if (!erros.isEmpty()){
            criaMensagemErro(erros);
        }
        return true;
    }

    public void criaMensagemErro(List<String> campos){
        String mensage = "Preenchimento de campo: ";
        for (String i : campos){
            mensage += ", " + i;
        }
       mensage += " é obrigatório";

       this.mensagemDeErro = mensage;
    }

}