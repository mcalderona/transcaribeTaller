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
    public void ImprimirDetalle() {

        System.out.println("Tipo: Bus");
        System.out.println("Placa: " + placa);
        System.out.println("Ruta: " + ruta);
        System.out.println("Cupos disponibles: " + getCuposDisponibles());
        System.out.println("Tarifa: " + tarifaBase);
    }
}
