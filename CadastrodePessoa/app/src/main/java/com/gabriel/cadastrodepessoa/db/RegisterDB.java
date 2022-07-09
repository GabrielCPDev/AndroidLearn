package com.gabriel.cadastrodepessoa.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gabriel.cadastrodepessoa.dao.PersonDAO;
import com.gabriel.cadastrodepessoa.entities.Person;

@Database(entities = {Person.class}, version = 2, exportSchema = false)
public abstract class RegisterDB extends RoomDatabase {

    public abstract PersonDAO getPersonDAO();

}
