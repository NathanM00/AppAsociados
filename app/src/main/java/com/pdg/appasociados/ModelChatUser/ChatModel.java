package com.pdg.appasociados.ModelChatUser;

public class ChatModel {
    private String envia;
    private String recibe;
    private String mensaje;
    private String visto;
    private String hora;
    private String nombre;

    public  ChatModel(){

    }

    public ChatModel(String envia, String nombre, String recibe, String mensaje, String visto, String hora) {
        this.envia = envia;
        this.recibe = recibe;
        this.mensaje = mensaje;
        this.visto = visto;
        this.hora = hora;
        this.nombre = nombre;

    }

    public String getEnvia() {
        return envia;
    }

    public void setEnvia(String envia) {
        this.envia = envia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRecibe() {
        return recibe;
    }

    public void setRecibe(String recibe) {
        this.recibe = recibe;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getVisto() {
        return visto;
    }

    public void setVisto(String visto) {
        this.visto = visto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
