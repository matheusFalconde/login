package com.example.victor.persistenciafirebase;

import android.content.Context;

import com.example.victor.persistenciafirebase.util.LibraryClass;
import com.firebase.client.Firebase;

/**
 * Created by Victor on 22/05/2016.
 */
public class User {

    private static final String TOKEN = "com.victor.TOKEN";

    private String Id;
    private String Nome;
    private String Sobrenome;
    private String Email;
    private String Senha;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public void SaveToken(Context context,String Token)
    {
        LibraryClass.SaveSP(context,TOKEN,Token);
    }

    public String getToken(Context context)
    {
        String token = LibraryClass.GetSP(context,TOKEN);
        return  token;
    }

    public void SaveDB()
    {
        Firebase firebase =LibraryClass.getFirebase();
        firebase = firebase.child("users").child(getId());

        setSenha(null);
        setId(null);

        firebase.setValue(this);

    }


}
