package com.gabriel.cadastrodepessoa.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gabriel.cadastrodepessoa.entities.Person;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    void save(Person person);
    @Update
    int update(Person person);
    @Query(value = "SELECT * FROM PESSOAS")
    List<Person> findAll();
}
