package com.pdg.appasociados;

public class Noticia {
    private String titular, archivo, cuerpo;

    public Noticia(){

    }

    public Noticia(String titular, String archivo, String cuerpo) {
        this.titular = titular;
        this.archivo = archivo;
        this.cuerpo = cuerpo;
    }

    public String getTitular() { return titular; }

    public void setTitular(String titular) { this.titular = titular; }

    public String getCuerpo() { return cuerpo; }

    public void setCuerpo(String cuerpo) { this.cuerpo = cuerpo; }

    public String getArchivo() { return archivo; }

    public void setArchivo(String archivo) { this.archivo = archivo; }


}
