package com.pdg.appasociados;

public class Panfleto {
    private String titular, archivo, cuerpo,id;

    public Panfleto(){

    }

    public Panfleto(String titular, String archivo, String cuerpo, String id) {
        this.titular = titular;
        this.archivo = archivo;
        this.cuerpo = cuerpo;
        this.id = id;

    }

    public String getTitular() { return titular; }

    public void setTitular(String titular) { this.titular = titular; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getCuerpo() { return cuerpo; }

    public void setCuerpo(String cuerpo) { this.cuerpo = cuerpo; }

    public String getArchivo() { return archivo; }

    public void setArchivo(String archivo) { this.archivo = archivo; }


}
