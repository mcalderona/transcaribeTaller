package org.example.modelo;

public abstract class Persona {
    private String nombre;
    private String Cedula;

    public Persona() {
    }

    public Persona(String nombre, String Cedula) {
        this.nombre = nombre;
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return nombre;
    }public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return Cedula;
    }public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }


    public String imprimirDetalle(){
        return "\nNombre: " + nombre +
                "\nCedula: " + Cedula;
    }
}
