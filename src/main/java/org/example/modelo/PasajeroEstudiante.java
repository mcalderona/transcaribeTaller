package org.example.modelo;

public class PasajeroEstudiante extends Pasajero{

    public PasajeroEstudiante() {
        super();
    }

    public PasajeroEstudiante(String nombre, String Cedula, String Tipo) {
        super(nombre, Cedula, Tipo);
    }


    public double CalcularDescuento() {
        return 0.15;
    }
}
