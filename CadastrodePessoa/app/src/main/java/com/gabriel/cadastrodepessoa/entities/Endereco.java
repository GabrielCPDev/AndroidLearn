package com.gabriel.cadastrodepessoa.entities;

import java.io.Serializable;
import java.util.Objects;

public class Endereco implements Serializable {

    private String cidade;
    private String bairro;
    private String rua;
    private Integer numero;

    public Endereco(){

    }

    public Endereco(String cidade, String bairro, String rua, Integer numero) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(cidade, endereco.cidade) && Objects.equals(bairro, endereco.bairro) && Objects.equals(rua, endereco.rua) && Objects.equals(numero, endereco.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidade, bairro, rua, numero);
    }
}
