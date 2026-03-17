package org.example.modelo;

public class Bus extends Vehiculo {

    private static final int    CAPACIDAD = 45;
    private static final double TARIFA    = 15000;

    public Bus(String placa, Ruta ruta) {
        super(placa, ruta, CAPACIDAD, TARIFA);
    }


    public Bus(String placa, String codigoRuta) {
        super(placa, codigoRuta, CAPACIDAD, TARIFA);
    }

    @Override
    public String getTipo() { return "Bus"; }

    @Override
    public String imprimirDetalle() {
        String infoRuta = ruta != null
                ? ruta.getCiudadOrigen() + " → " + ruta.getCiudadDestino() + " [" + ruta.getCodigoRuta() + "]"
                : "Sin ruta asignada";
        return "=========BUS==========" +
                "\nPlaca: " + placa +
                "\nRuta: " + infoRuta +
                "\nCupos disponibles: " + getCuposDisponibles() +
                "\nTarifa: $" + tarifaBase;
    }
}