package org.example.modelo;

import java.time.LocalDate;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular() { super(); }

    public PasajeroRegular(String nombre, String cedula, String tipo) {
        super(nombre, cedula, tipo);
    }

    public PasajeroRegular(String nombre, String cedula, String tipo, LocalDate fechaNacimiento) {
        super(nombre, cedula, tipo, fechaNacimiento);
    }

    @Override
    public double CalcularDescuento() { return 0; }
}