package org.example.modelo;

public class PasajeroRegular extends Pasajero{

    public PasajeroRegular() {
        super();
    }

    public PasajeroRegular(String nombre, String Cedula, String Tipo) {
        super(nombre, Cedula, Tipo);
    }


    public double CalcularDescuento() {
        return 0;
    }

}
