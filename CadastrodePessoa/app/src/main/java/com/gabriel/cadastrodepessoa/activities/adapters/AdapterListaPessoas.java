package com.gabriel.cadastrodepessoa.activities.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.entities.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaPessoas extends BaseAdapter {

    private final List<Pessoa> pessoas;
    private final Activity activity;
    private TextView campoNome, campoIdade, campoEndereco;

    public AdapterListaPessoas(List<Pessoa> pessoas, Activity act) {
        this.pessoas = pessoas;
        this.activity = act;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.activity_lista_pessoa, parent, false);
        Pessoa p = pessoas.get(position);
        
        campoNome = view.findViewById(R.id.textNome);
        campoIdade = view.findViewById(R.id.textIdade);
        campoEndereco = view.findViewById(R.id.textEndereco);

        campoNome.setText(p.getNome());
        campoEndereco.setText(p.getEnderedo());
        campoIdade.setText(p.getIdade());
        return view;
    }

}
