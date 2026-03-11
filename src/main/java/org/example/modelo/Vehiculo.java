package org.example.modelo;

public abstract class Vehiculo implements Imprimible {
    protected String Placa;
    protected String Ruta;
    protected int CapacidadMaxima;
    protected int PasajerosActuales;

    public Vehiculo(String placa, String ruta, int capacidadMaxima) {
        this.Placa = placa;
        this.Ruta = ruta;
        this.CapacidadMaxima = capacidadMaxima;
        this.PasajerosActuales = 0;
    }

    public String getPlaca() {
        return Placa;
    }

    public String getRuta() {
        return Ruta;
    }

    public int getCapacidadMaxima() {
        return CapacidadMaxima;
    }

    public int getPasajerosActuales() {
        return PasajerosActuales;
    }
    public int  getCuposDisponibles(){ //metodo que calcula cuántos cupos quedan disponibles en el vehículo
        return CapacidadMaxima - PasajerosActuales;
    }

     public void SubirPasajeros(){  //metodo que permite subir un pasajero al vehículo, pero solo si aún hay espacio
        if (PasajerosActuales < CapacidadMaxima ){
            PasajerosActuales++;
        }
     }
}
