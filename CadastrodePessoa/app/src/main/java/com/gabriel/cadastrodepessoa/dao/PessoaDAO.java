package com.gabriel.cadastrodepessoa.dao;

import com.gabriel.cadastrodepessoa.entities.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private final static List<Pessoa> pessoas = new ArrayList<>();

    public void salva(Pessoa pessoa) {
        pessoas.add(pessoa);

    }

    public List<Pessoa> listaPessoas() {
        return new ArrayList<>(pessoas);
    }
}
