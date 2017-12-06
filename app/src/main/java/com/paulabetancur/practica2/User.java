package com.paulabetancur.practica2;

/**
 * Created by Daniela on 6/12/2017.
 */

public class User {

    private String name, sureName, usuario, email, password;


    public User(String name, String sureName,String usuario, String email, String password){
        this.name = name;
        this.sureName= sureName;
        this.usuario = usuario;
        this.email = email;

        this.password = password;


    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.name = sureName;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.name = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
