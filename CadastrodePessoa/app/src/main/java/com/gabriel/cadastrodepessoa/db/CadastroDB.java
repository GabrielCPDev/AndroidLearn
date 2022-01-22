package com.gabriel.cadastrodepessoa.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gabriel.cadastrodepessoa.dao.PessoaDAO;
import com.gabriel.cadastrodepessoa.entities.Pessoa;

@Database(entities = {Pessoa.class}, version = 1, exportSchema = false)
public abstract class CadastroDB extends RoomDatabase {

    public abstract PessoaDAO getPessoaDAO ();

}
