package com.gabriel.cadastrodepessoa.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_PESSOAS";
    public static String TABELA_PESSOAS = "pessoas";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_PESSOAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " nome TEXT,"
                + " idade INTEGER, "
                + " endereco TEXT"
                +");";
        try {
            db.execSQL(sql);
            Log.i("INFO DB", "SUCESSO AO CRIAR A TABELA");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela : " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
