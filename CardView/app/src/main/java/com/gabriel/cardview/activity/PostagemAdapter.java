package com.gabriel.cardview.activity;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.cardview.R;
import com.gabriel.cardview.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class PostagemAdapter  extends RecyclerView.Adapter<PostagemAdapter.MyViewHolder> {
    private List<Postagem> postagens = new ArrayList<>();

    public PostagemAdapter(List<Postagem> postagens) {
        this.postagens = postagens;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.postagem_detalhe, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagem p = postagens.get(position);
        holder.nome.setText(p.getNome());
        holder.postagem.setText(p.getPostagem());
        holder.imagem.setImageResource(p.getImagem());

    }

    @Override
    public int getItemCount() {
        return postagens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nome;
        private TextView postagem;
        private ImageView imagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textNome);
            postagem = itemView.findViewById(R.id.textData);
            imagem = itemView.findViewById(R.id.imagePostagem);
        }
    }
}
