package org.example.modelo;

public class PasajeroEstudiante extends Pasajero{

    public PasajeroEstudiante() {
        super();
    }

    public PasajeroEstudiante(String nombre, int edad, String Tipo) {
        super(nombre, edad, Tipo);
    }


    public double CalcularDescuento() {
        return 0.15;
    }
}
