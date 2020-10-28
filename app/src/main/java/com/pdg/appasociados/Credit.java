package com.pdg.appasociados;

public class Credit {
    String nombre;
    String TANM;
    String TAAnual;

    public Credit(){

    }

    public Credit(String nombre, String TANM, String TAAnual) {
        this.nombre = nombre;
        this.TANM = TANM;
        this.TAAnual = TAAnual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTANM() {
        return TANM;
    }

    public void setTANM(String TANM) {
        this.TANM = TANM;
    }

    public String getTAAnual() {
        return TAAnual;
    }

    public void setTAAnual(String TAAnual) {
        this.TAAnual = TAAnual;
    }
}

