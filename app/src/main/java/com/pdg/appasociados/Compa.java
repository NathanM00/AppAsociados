package com.pdg.appasociados;

public class Compa {

    public String nombre, correo, contra, parentezco;

    public Compa () {

    }

    public Compa (String nombre, String correo, String contra, String parentezco) {
        this.nombre = nombre;
        this.contra = contra;
        this.correo = correo;
        this.parentezco = parentezco;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }
}
