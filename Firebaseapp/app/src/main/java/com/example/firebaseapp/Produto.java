package com.example.firebaseapp;

public class Produto {

    private String descricao;
    private String marca;
    private Double preco;

    public Produto(){

    }

    public Produto(String descricao, String marca, Double preco) {
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
