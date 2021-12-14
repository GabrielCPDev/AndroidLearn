package com.gabriel.cadastrodepessoa.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.dao.PessoaDAO;
import com.gabriel.cadastrodepessoa.entities.Pessoa;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormularioPessoaActivity extends AppCompatActivity {

    public static final String NOVA_PESSOA = "Nova Pessoa";

    private Button botaoSalvar;
    private EditText nome, idade, endereco;
    private String mensagemDeErro = "";
    private PessoaDAO dao = new PessoaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pessoa);
        setTitle(NOVA_PESSOA);
        inicializaCampos();
        configuraBotaoSalvar();
    }

    private void inicializaCampos() {
        botaoSalvar = findViewById(R.id.botao_cadastrar);
        nome = findViewById(R.id.activity_formulario_pessoa_nome);
        endereco = findViewById(R.id.activity_formulario_pessoa_endereco);
        idade = findViewById(R.id.activity_formulario_pessoa_idade);
    }

    private void configuraBotaoSalvar() {
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validaDados(nome, endereco, idade)) {
                    Pessoa pessoa = constroePessoa();
                    salvaPessoa(pessoa);
                } else {
                    Snackbar.make(view, mensagemDeErro, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void salvaPessoa(Pessoa pessoa) {
        dao.salva(pessoa);
        finish();
    }

    private Pessoa constroePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome.getText().toString());
        pessoa.setEnderedo(endereco.getText().toString());
        pessoa.setIdade((Integer.parseInt(idade.getText().toString())));

        return pessoa;
    }

    private boolean validaDados(EditText nome, EditText endereco, EditText idade) {
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
            criaMensagemErro(erros);
            return false;
        } else {
            return true;
        }
    }

    public void criaMensagemErro(List<String> campos) {
        String mensage = "Preenchimento de campo: ";
        for (String i : campos) {
            mensage += i + ", ";
        }
        mensage += " é obrigatório";
        this.mensagemDeErro = mensage;
    }
}