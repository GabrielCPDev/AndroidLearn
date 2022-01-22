package com.gabriel.cadastrodepessoa.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;
@Entity(tableName = "PESSOAS")
public class Pessoa implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "NOME")
    private String nome;
    @ColumnInfo(name = "IDADE")
    private Integer idade;
    @ColumnInfo(name = "ENDERECO")
    private String enderedo;

    public Pessoa(){
    }

    @Ignore
    public Pessoa(Long id,String nome, Integer idade, String enderedo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.enderedo = enderedo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
