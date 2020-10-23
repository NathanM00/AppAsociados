package com.pdg.appasociados;

public class Coment {
    String id;
    String nombre;
    String fecha;
    String comentario;

    public Coment(){

    }

    public Coment(String id, String nombre, String fecha, String comentario) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

