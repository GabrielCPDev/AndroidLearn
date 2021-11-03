package br.com.alura.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluraviagens.utils.DiasUtil;
import com.example.aluraviagens.utils.MoedaUtil;
import com.example.aluraviagens.utils.ResourcesUtil;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do pacote";
    private ImageView imagem;
    private TextView local;
    private TextView dias;
    private TextView data;
    private TextView preco;
    private Button botao;

    private void inicializaCampos() {
        imagem = findViewById(R.id.resumo_pacote_imagem);
        local = findViewById(R.id.resumo_pacote_local);
        dias = findViewById(R.id.resumo_pacote_dias);
        data = findViewById(R.id.resumo_pacote_data);
        preco = findViewById(R.id.resumo_pacote_preco);
        botao = findViewById(R.id.resumo_pacote_botao_realiza_pagamento);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setTitle(TITULO_APPBAR);

        Pacote pacote = new Pacote("Brasília", "sao_paulo_sp", 10, new BigDecimal("243.99"));
        inicializaCampos();


        Calendar dataIda = Calendar.getInstance();
        Calendar dataVolta = Calendar.getInstance();
        dataVolta.add(Calendar.DATE, pacote.getDias());
        String dataFormatadaDaViagem = DataUtil.periodoEmTexto(dataIda, dataVolta);

        fazBidingDePacote(pacote, dataFormatadaDaViagem);


    }


    private void fazBidingDePacote(Pacote pacote, String data) {
        local.setText(pacote.getLocal());
        Drawable drawableDoPacote = ResourcesUtil.devolveDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawableDoPacote);
        String diasEmTexto = DiasUtil.formataEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
        String precoDoPacote = MoedaUtil.formataMoedaParaBrasileiro(pacote.getPreco());
        preco.setText(precoDoPacote);
        this.data.setText(data);
    }


}