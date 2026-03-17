package org.example.modelo;

import java.time.LocalDate;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor() { super(); }

    public PasajeroAdultoMayor(String nombre, String cedula, String tipo) {
        super(nombre, cedula, tipo);
    }


    public PasajeroAdultoMayor(String nombre, String cedula, String tipo, LocalDate fechaNacimiento) {
        super(nombre, cedula, tipo, fechaNacimiento);
    }

    @Override
    public double CalcularDescuento() { return 0.30; }
}