package com.pdg.appasociados.ModelChatUser;

public class ChatUser {
    private String id;
    private String nombre;
    private String estado;
    private String fecha;
    private String hora;
    private int mensajes;

    public ChatUser(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public ChatUser(){

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
}
