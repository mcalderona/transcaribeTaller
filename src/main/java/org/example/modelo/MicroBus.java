package org.example.modelo;

public class MicroBus extends Vehiculo {

    private static final int    CAPACIDAD = 25;
    private static final double TARIFA    = 10000;

    public MicroBus(String placa, Ruta ruta) {
        super(placa, ruta, CAPACIDAD, TARIFA);
    }

    public MicroBus(String placa, String codigoRuta) {
        super(placa, codigoRuta, CAPACIDAD, TARIFA);
    }

    @Override
    public String getTipo() { return "MicroBus"; }

    @Override
    public String imprimirDetalle() {
        String infoRuta = ruta != null
                ? ruta.getCiudadOrigen() + " → " + ruta.getCiudadDestino() + " [" + ruta.getCodigoRuta() + "]"
                : "Sin ruta asignada";
        return "=========MICROBUS==========" +
                "\nPlaca: " + placa +
                "\nRuta: " + infoRuta +
                "\nCupos disponibles: " + getCuposDisponibles() +
                "\nTarifa: $" + tarifaBase;
    }
}