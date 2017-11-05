package com.paulabetancur.practica2;

/**
 * Created by Paula on 18/10/2017.
 */

public class Discotecas {

    String URL, cover, direccion, musica, presupuesto, nombre;
    int telefono;
    private String uid;

    public Discotecas() {
    }

    public Discotecas(String url, String nombre, String cover, String direccion, String musica, String presupuesto, int telefono) {
        this.URL = url;
        this.cover = cover;
        this.direccion = direccion;
        this.musica = musica;
        this.presupuesto = presupuesto;
        this.telefono = telefono;
        this.nombre = nombre;

    }

    public String getName() {
        return URL;
    }

    public void setName(String url) {
        this.URL = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto ) {
        this.presupuesto =  presupuesto;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre ) {
        this.nombre =  nombre;
    }
}
