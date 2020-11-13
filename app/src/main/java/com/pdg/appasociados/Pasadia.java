package com.pdg.appasociados;

public class Pasadia {
    private String destino, archivo;

    public Pasadia(){

    }


    public Pasadia(String destino, String archivo) {
        this.destino = destino;
        this.archivo = archivo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }


}
