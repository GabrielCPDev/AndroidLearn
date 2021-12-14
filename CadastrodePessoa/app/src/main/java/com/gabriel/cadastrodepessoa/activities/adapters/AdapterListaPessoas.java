package com.gabriel.cadastrodepessoa.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.activities.adapters.listeners.OnItemClickListener;
import com.gabriel.cadastrodepessoa.entities.Pessoa;

import java.util.List;

public class AdapterListaPessoas extends RecyclerView.Adapter<AdapterListaPessoas.PessoaViewHolder> {

    private final List<Pessoa> pessoas;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public AdapterListaPessoas(List<Pessoa> pessoas, Context ctx) {
        this.pessoas = pessoas;
        this.context = ctx;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.activity_lista_pessoa, parent, false);
        return new PessoaViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder holder, int position) {
        Pessoa pessoa = pessoas.get(position);
        holder.vincula(pessoa);
    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }

    class PessoaViewHolder extends RecyclerView.ViewHolder{

        private Pessoa pessoa;
        private TextView campoNome, campoIdade, campoEndereco;


        public PessoaViewHolder(@NonNull View itemView) {
            super(itemView);
            inicializaCampos(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClik(pessoa,getAdapterPosition());
                }
            });

        }

        private void inicializaCampos(@NonNull View itemView) {
            campoNome = itemView.findViewById(R.id.textNome);
            campoIdade = itemView.findViewById(R.id.textIdade);
            campoEndereco = itemView.findViewById(R.id.textEndereco);
        }
        public void vincula(Pessoa p){
            this.pessoa = p;
            preencheCampo(p);
        }
        public void preencheCampo(Pessoa p){
            campoNome.setText(p.getNome());
            campoIdade.setText(p.getIdade());
            campoEndereco.setText(p.getEnderedo());
        }
    }
    public void adiciona(Pessoa p){
        pessoas.add(p);
        notifyDataSetChanged();
    }

}
