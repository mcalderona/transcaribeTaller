package org.example.modelo;

public class PasajeroAdultoMayor extends Pasajero{

    public PasajeroAdultoMayor() {
        super();
    }

    public PasajeroAdultoMayor(String nombre, int edad, String Tipo) {
        super(nombre, edad, Tipo);
    }


    public double CalcularDescuento() {
        return 0.30;
    }
}
