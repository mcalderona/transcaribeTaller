package org.example.modelo;

public class PasajeroRegular extends Pasajero{

    public PasajeroRegular() {
        super();
    }

    public PasajeroRegular(String nombre, int edad, String Tipo) {
        super(nombre, edad, Tipo);
    }

    @Override
    public double CalcularDescuento() {
        return 0;
    }

}
