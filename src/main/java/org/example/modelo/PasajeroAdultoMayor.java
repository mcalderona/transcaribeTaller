package org.example.modelo;

public class PasajeroAdultoMayor extends Pasajero{

    public PasajeroAdultoMayor() {
        super();
    }

    public PasajeroAdultoMayor(String nombre, String Cedula, String Tipo) {
        super(nombre, Cedula, Tipo);
    }


    public double CalcularDescuento() {
        return 0.30;
    }
}
