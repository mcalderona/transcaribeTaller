package org.example.modelo;

public class MicroBus extends Vehiculo{

    private static final int   CAPACIDAD = 25;
    private static final double  TARIFA = 10000;

    public MicroBus(String placa, String ruta) {
        super(placa, ruta, CAPACIDAD, TARIFA);
    }


    @Override
    public String getTipo() {
        return "MicroBus";
    }

    @Override
    public String imprimirDetalle() {
        return "=========MICROBUS==========" +
                "\nPlaca: " + placa +
                "\nRuta: " + ruta +
                "\nCupos disponibles: " + getCuposDisponibles() +
                "\nTarifa: " + tarifaBase;
    }
}
