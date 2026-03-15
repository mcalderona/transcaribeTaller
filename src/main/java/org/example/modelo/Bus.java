package org.example.modelo;

public class Bus extends Vehiculo {


    private static final int   CAPACIDAD = 45;
    private static final double  TARIFA = 15000;

    public Bus(String placa, String ruta) {
        super(placa,ruta,CAPACIDAD,TARIFA);
    }

    @Override
    public String getTipo() {
        return "Bus";
    }

    @Override
    public String imprimirDetalle() {
        return
                    "=========BUS==========" +
                    "\nPlaca: " + placa +
                    "\nRuta: " + ruta +
                    "\nCupos disponibles: " + getCuposDisponibles() +
                    "\nTarifa: " + tarifaBase;
    }
}
