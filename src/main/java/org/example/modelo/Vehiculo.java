package org.example.modelo;

public abstract class Vehiculo implements Imprimible {

    protected String placa;
    protected String ruta;
    protected int capacidadMaxima;
    protected int pasajerosActuales;
    protected boolean disponible;
    protected double tarifaBase;

    public Vehiculo(String placa, String ruta, int capacidadMaxima, double tarifaBase) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
        this.tarifaBase = tarifaBase;
        this.pasajerosActuales = 0;
        this.disponible = true;
    }

    public int getCuposDisponibles() {//Calcula cuántos cupos quedan disponibles en el vehículo
        return capacidadMaxima - pasajerosActuales;
    }

    public boolean ocuparCupo() {//permite subir un pasajero al vehículo, pero solo si aún hay espacio
        if (pasajerosActuales < capacidadMaxima) {
            pasajerosActuales++;
            return true;
        }
        return false;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public abstract String getTipo();

}

