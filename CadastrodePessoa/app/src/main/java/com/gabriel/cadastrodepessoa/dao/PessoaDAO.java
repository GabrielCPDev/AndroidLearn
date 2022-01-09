package com.gabriel.cadastrodepessoa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gabriel.cadastrodepessoa.entities.Pessoa;
import com.gabriel.cadastrodepessoa.helper.DBHelper;

import java.util.List;

public class PessoaDAO implements IPessoaDAO {

    private Context context;
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public PessoaDAO(Context context) {
        this.context = context;
        DBHelper db = new DBHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salva(Pessoa pessoa) {
        try {
            escreve.insert(DBHelper.TABELA_PESSOAS,null, getContentVelues(pessoa));
            Log.i("DB LOG", "Tarefa salva com sucesso! ");
        }catch (Exception e){
            Log.i("DB LOG", "Erro ao salvar tabela" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Pessoa pessoa) {
        return false;
    }

    @Override
    public boolean deletar(Pessoa pessoa) {
        return false;
    }

    @Override
    public List<Pessoa> listaPessoas() {
        return null;
    }

    private ContentValues getContentVelues(Pessoa p){
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("idade", p.getIdade());
        cv.put("endereco", p.getEnderedo());
        return cv;
    }
}
