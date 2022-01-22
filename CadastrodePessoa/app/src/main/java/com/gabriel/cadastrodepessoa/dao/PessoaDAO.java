package com.gabriel.cadastrodepessoa.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gabriel.cadastrodepessoa.entities.Pessoa;

import java.util.List;

@Dao
public interface PessoaDAO {

    @Insert
    void salva(Pessoa pessoa);
    @Query(value = "SELECT * FROM PESSOAS")
    List<Pessoa> buscaTodos();
}
