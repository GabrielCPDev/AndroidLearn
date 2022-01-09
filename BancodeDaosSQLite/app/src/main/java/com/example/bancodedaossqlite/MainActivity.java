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
//            bancoDados.execSQL("create table if not exists PESSOAS(nome VARCHAR, idade INT(3))");
//            bancoDados.execSQL("insert into PESSOAS(nome, idade) values ('Gabriel', 17)");
//            bancoDados.execSQL("insert into PESSOAS(nome, idade) values ('Goku', 35)");
//            bancoDados.execSQL("insert into PESSOAS(nome, idade) values ('Vegeta', 40)");
//            bancoDados.execSQL("insert into PESSOAS(nome, idade) values ('Kuririn', 20)");

            //recuperar pessoas
            String consulta = "select nome, idade from PESSOAS";
            Cursor cursor = bancoDados.rawQuery(consulta,null);
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            String nome = cursor.getString(indiceNome);
            String idade = cursor.getString(indiceIdade);

            //atualizar dados
            bancoDados.execSQL("update pessoas set idade = 19 where nome = 'kuririn' ");

            cursor.moveToFirst();
            while (cursor != null){
                Log.i("RESULTADO - NOME -> ", nome + " idade: " + idade);
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}