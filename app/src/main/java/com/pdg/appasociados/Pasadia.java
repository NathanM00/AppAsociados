package com.pdg.appasociados;

public class Pasadia {
    private String destino, archivo, fecha, descripcion, id;
    Float calificacion;
    Integer numCali;

    public Pasadia(){

    }


    public Pasadia(String destino, String archivo, String fecha, String descripcion, String id,  Float calificacion, Integer numCali) {
        this.destino = destino;
        this.archivo = archivo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.id = id;
        this.calificacion = calificacion;
        this.numCali = numCali;

    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getNumCali() {
        return numCali;
    }

    public void setNumCali(Integer numCali) {
        this.numCali = numCali;
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
