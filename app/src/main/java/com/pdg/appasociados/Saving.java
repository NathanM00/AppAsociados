package com.pdg.appasociados;

public class Saving {
    String nombre;
    String CMDA;
    String CMDC;
    String descripcion;
    String R30;
    String R365;

    public Saving(){

    }

    public Saving(String nombre, String CMDA, String CMDC, String descripcion, String R30, String R365) {
        this.nombre = nombre;
        this.CMDA = CMDA;
        this.CMDC = CMDC;
        this.R365 = R365;
        this.R30 = R30;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCMDA() {
        return CMDA;
    }

    public void setCMDA(String CMDA) {
        this.CMDA = CMDA;
    }

    public String getCMDC() {
        return CMDC;
    }

    public void setCMDC(String CMDC) {
        this.CMDC = CMDC;
    }

    public String getR30() {
        return R30;
    }

    public void setR30(String R30) {
        this.R30 = R30;
    }

    public String getR365() {
        return R365;
    }

    public void setR365(String R365) {
        this.R365 = R365;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

