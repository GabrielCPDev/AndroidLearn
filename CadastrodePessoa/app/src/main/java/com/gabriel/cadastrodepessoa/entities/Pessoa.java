package com.gabriel.cadastrodepessoa.entities;

import java.io.Serializable;
import java.util.Objects;

public class Pessoa implements Serializable{

    private String nome;
    private Integer idade;
    private String enderedo;

    public Pessoa(){

    }

    public Pessoa(String nome, Integer idade, String enderedo) {
        this.nome = nome;
        this.idade = idade;
        this.enderedo = enderedo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEnderedo() {
        return enderedo;
    }

    public void setEnderedo(String enderedo) {
        this.enderedo = enderedo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return nome.equals(pessoa.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return nome;
    }
}
