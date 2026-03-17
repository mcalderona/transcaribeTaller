package org.example.modelo;

public class Buseta extends Vehiculo {

    private static final int    CAPACIDAD = 19;
    private static final double TARIFA    = 8000;

    public Buseta(String placa, Ruta ruta) {
        super(placa, ruta, CAPACIDAD, TARIFA);
    }

    public Buseta(String placa, String codigoRuta) {
        super(placa, codigoRuta, CAPACIDAD, TARIFA);
    }

    @Override
    public String getTipo() { return "Buseta"; }

    @Override
    public String imprimirDetalle() {
        String infoRuta = ruta != null
                ? ruta.getCiudadOrigen() + " → " + ruta.getCiudadDestino() + " [" + ruta.getCodigoRuta() + "]"
                : "Sin ruta asignada";
        return "=========BUSETA==========" +
                "\nPlaca: " + placa +
                "\nRuta: " + infoRuta +
                "\nCupos disponibles: " + getCuposDisponibles() +
                "\nTarifa: $" + tarifaBase;
    }
}