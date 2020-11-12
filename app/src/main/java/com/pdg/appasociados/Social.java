package com.pdg.appasociados;

public class Social {
    String nombre;
    String descripcion;
    String convenios;

    public Social(){

    }

    public Social(String nombre, String descripcion, String convenios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.convenios = convenios;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getconvenios() {
        return convenios;
    }

    public void setconvenios(String convenios) {
        this.convenios = convenios;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

