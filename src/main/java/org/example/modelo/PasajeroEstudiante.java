package org.example.modelo;

import java.time.LocalDate;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante() { super(); }

    public PasajeroEstudiante(String nombre, String cedula, String tipo) {
        super(nombre, cedula, tipo);
    }

    public PasajeroEstudiante(String nombre, String cedula, String tipo, LocalDate fechaNacimiento) {
        super(nombre, cedula, tipo, fechaNacimiento);
    }

    @Override
    public double CalcularDescuento() { return 0.15; }
}