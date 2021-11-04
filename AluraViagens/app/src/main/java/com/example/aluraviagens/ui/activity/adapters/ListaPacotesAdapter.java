package com.example.aluraviagens.ui.activity.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluraviagens.ui.models.Pacote;
import com.example.aluraviagens.utils.DiasUtil;
import com.example.aluraviagens.utils.MoedaUtil;
import com.example.aluraviagens.utils.ResourcesUtil;

import java.math.BigDecimal;
import java.util.List;

import br.com.alura.aluraviagens.R;

public class ListaPacotesAdapter extends BaseAdapter {

    private final List<Pacote> pacotes;
    private final Context context;

    public ListaPacotesAdapter(List<Pacote> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Pacote getItem(int posicao) {
        return pacotes.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup parent) {

        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_pacote, parent, false);
        Pacote pacote = pacotes.get(posicao);
        mostraLocal(viewCriada, pacote);
        mostraImagem(viewCriada, pacote);
        mostraDias(viewCriada, pacote);
        mostraTexto(viewCriada, pacote);
        return viewCriada;
    }

    private void mostraTexto(View viewCriada, Pacote pacote) {
        BigDecimal precoDoPacote = pacote.getPreco();
        String moedaBrasileira = MoedaUtil.formataMoedaParaBrasileiro(precoDoPacote);
        TextView preco = viewCriada.findViewById(R.id.item_pacote_preco);
        preco.setText(moedaBrasileira);
    }

    private void mostraDias(View viewCriada, Pacote pacote) {
        TextView dias = viewCriada.findViewById(R.id.item_pacote_dias);
        String diasEmTexto = DiasUtil.formataEmTexto
                (pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void mostraImagem(View viewCriada, Pacote pacote) {
        ImageView imagem = viewCriada.findViewById(R.id.item_pacote_imagem);
        Drawable drawableImagemPacote = ResourcesUtil.devolveDrawable(context, pacote.getImagem());
        imagem.setImageDrawable(drawableImagemPacote);
    }


    private void mostraLocal(View viewCriada, Pacote pacote) {
        TextView local = viewCriada.findViewById(R.id.item_pacote_local);
        local.setText(pacote.getLocal());
    }

}