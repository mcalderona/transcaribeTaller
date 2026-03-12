package org.example.modelo;

public abstract class Pasajero extends Persona{
    private String Tipo;

    public Pasajero() {
        super();
    }

    public Pasajero(String nombre, int edad, String Tipo) {
        super(nombre, edad);
        this.Tipo = Tipo;
    }

    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public double CalcularDescuento(){
        return 0;
    }

    public String imprimirDetalle(){
        return super.imprimirDetalle() +
                "\nTipo: " + Tipo;
    }
}
