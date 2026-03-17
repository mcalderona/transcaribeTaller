package org.example.modelo;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pasajero extends Persona {

    private String tipo;
    private LocalDate fechaNacimiento;  // NUEVO

    public Pasajero() {
        super();
    }

    public Pasajero(String nombre, String cedula, String tipo, LocalDate fechaNacimiento) {
        super(nombre, cedula);
        this.tipo = tipo;
        this.fechaNacimiento = fechaNacimiento;
    }


    public Pasajero(String nombre, String cedula, String tipo) {
        super(nombre, cedula);
        this.tipo = tipo;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }


    public int calcularEdad() {
        if (fechaNacimiento == null) return 0;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public double CalcularDescuento() {
        return 0;
    }

    public String imprimirDetalle() {
        return "=========PASAJERO==========" +
                super.imprimirDetalle() +
                "\nTipo: " + tipo +
                (fechaNacimiento != null ? "\nFecha de nacimiento: " + fechaNacimiento + " (edad: " + calcularEdad() + " años)" : "");
    }
}