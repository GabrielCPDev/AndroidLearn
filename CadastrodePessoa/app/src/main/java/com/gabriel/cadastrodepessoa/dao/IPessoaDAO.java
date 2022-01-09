package com.gabriel.cadastrodepessoa.dao;

import com.gabriel.cadastrodepessoa.entities.Pessoa;

import java.util.List;

public interface IPessoaDAO {
    boolean salva(Pessoa pessoa);
    boolean atualizar(Pessoa pessoa);
    boolean deletar(Pessoa pessoa);
    List<Pessoa> listaPessoas();
}
