package org.example.modelo;

public class Conductor extends Persona{
    private String Categorialicencia;
    private String Numerolicencia;

    public Conductor() {
        super();
    }

    public Conductor(String nombre, int edad, String Cagetorialicencia, String Numerolicencia) {
        super(nombre, edad);
        this.Categorialicencia = Categorialicencia;
        this.Numerolicencia =  Numerolicencia;
    }

    public String getCategorialicencia() {
        return Categorialicencia;
    }public void setCategorialicencia(String categorialicencia) {
        this.Categorialicencia = categorialicencia;
    }

    public String getNumerolicencia() {
        return Numerolicencia;
    }public void setNumerolicencia(String numerolicencia) {
        this.Numerolicencia = numerolicencia;
    }

    public boolean TieneLicencia(){
        return true;
    }


    public String imprimirDetalle(){
        return super.imprimirDetalle() +
                "\nNúmero de Licencia: " + Numerolicencia +
                "\nCategoría de Licencia: " + Categorialicencia;
    }

}
