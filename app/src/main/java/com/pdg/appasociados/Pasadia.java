package com.pdg.appasociados;

public class Pasadia {
    private String destino, archivo, fecha, descripcion;

    public Pasadia(){

    }


    public Pasadia(String destino, String archivo, String fecha, String descripcion) {
        this.destino = destino;
        this.archivo = archivo;
        this.fecha = fecha;
        this.descripcion = descripcion;

    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }


}
