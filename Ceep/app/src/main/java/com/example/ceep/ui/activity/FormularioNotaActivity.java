package com.example.ceep.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.ceep.R;
import com.example.ceep.dao.NotaDAO;
import com.example.ceep.model.Nota;

public class FormularioNotaActivity extends AppCompatActivity {

    private EditText titulo;
    private EditText descricao;
    private NotaDAO notaDAO = new NotaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.menu_formulario_nota_ic_salva){
            iniciaCamposFormulario();
            Nota notaCriada = new Nota(titulo.toString(), descricao.toString());
            salvaNota(notaCriada);
            Intent resultadoInsercao = new Intent();
            resultadoInsercao.putExtra("nota",notaCriada);
            setResult(2, resultadoInsercao );
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvaNota(Nota nota) {

        notaDAO.insere(nota);
    }

    private void iniciaCamposFormulario() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
    }
}