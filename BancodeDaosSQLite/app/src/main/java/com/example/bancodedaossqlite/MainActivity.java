package com.example.bancodedaossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            // cria banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            //cria tabela
            bancoDados.execSQL("create table if not exists PESSOAS(nome VARCHAR, idade INT(3))");
            bancoDados.execSQL("insert into PESSOAS(nome, idade) values ('Gabriel', 17)");
            bancoDados.execSQL("insert into PESSOAS(nome, idade) values ('Goku', 35)");

            //recuperar pessoas
            Cursor cursor = bancoDados.rawQuery("select nome, idade from PESSOAS",null);
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null){
                Log.i("RESULTADO - NOME -> ", cursor.getString(indiceNome));
                Log.i("RESULTADO - IDADE -> ", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}