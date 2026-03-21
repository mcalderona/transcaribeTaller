package org.example.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva {


    public enum Estado {
        ACTIVA,
        CONVERTIDA,
        CANCELADA
    }

    private String        codigoReserva;
    private Pasajero      pasajero;
    private Vehiculo      vehiculo;
    private LocalDateTime fechaCreacion;
    private LocalDate     fechaViaje;
    private Estado        estado;


    public Reserva(String codigoReserva, Pasajero pasajero, Vehiculo vehiculo,
                   LocalDateTime fechaCreacion, LocalDate fechaViaje) {
        this.codigoReserva = codigoReserva;
        this.pasajero      = pasajero;
        this.vehiculo      = vehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje    = fechaViaje;
        this.estado        = Estado.ACTIVA;
    }



    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }



    public void convertir() {
        this.estado = Estado.CONVERTIDA;
    }

    public void cancelar() {
        this.estado = Estado.CANCELADA;
    }

    public boolean estaActiva() {
        return this.estado == Estado.ACTIVA;
    }


    public boolean estaVencida() {
        if (estado != Estado.ACTIVA) return false;
        return fechaCreacion.plusHours(24).isBefore(LocalDateTime.now());
    }



    public String imprimirDetalle() {
        return "\n=========== RESERVA ===========" +
                "\nCódigo      : " + codigoReserva +
                "\nPasajero    : " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")" +
                "\nVehículo    : " + vehiculo.getPlaca() + " [" + vehiculo.getTipo() + "]" +
                "\nCreada      : " + fechaCreacion +
                "\nFecha viaje : " + fechaViaje +
                "\nEstado      : " + estado;
    }
}