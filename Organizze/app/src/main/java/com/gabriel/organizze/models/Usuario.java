package com.gabriel.organizze.models;

import com.gabriel.organizze.configs.FirebaseConfig;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {

    private final String node = "usuarios";
    private String idUsuario;
    private String nome;
    private String email;
    private String senha;

    public Usuario(){

    }
    public Usuario(String idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Exclude
    public String getIdUsuario() { return idUsuario; }

    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void salvar (){
        DatabaseReference firebase = FirebaseConfig.getFirebaseDatabase();
        firebase.child(node).child(this.idUsuario).setValue(this);
    }
}
