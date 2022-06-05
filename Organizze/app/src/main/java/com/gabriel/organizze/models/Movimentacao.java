package com.gabriel.organizze.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.gabriel.organizze.configs.FirebaseConfig;
import com.gabriel.organizze.helper.Base64Custom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Movimentacao {

    private final String node = "movimentacao";
    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private Double valor;

    public Movimentacao(){

    }

    public Movimentacao(String data, String categoria, String descricao, String tipo, Double valor) {
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void salvar() {
        DatabaseReference firebaseDatabase = FirebaseConfig.getFirebaseDatabase();
        FirebaseAuth firebaseAuth = FirebaseConfig.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(firebaseAuth.getCurrentUser().getEmail());

        String x = dataToChild();
        firebaseDatabase.child(node)
                .child(idUsuario)
                .child(x)
                .push()
                .setValue(this);
    }

    private String dataToChild() {
        List<String> strings = Arrays.asList(data.split("/"));
        return strings.get(1) + strings.get(2);
    }
}
