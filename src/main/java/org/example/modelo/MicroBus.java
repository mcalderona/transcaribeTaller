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
    public void imprimirDetalle() {

        System.out.println("Tipo: MicroBus");
        System.out.println("Placa: " + placa);
        System.out.println("Ruta: " + ruta);
        System.out.println("Cupos disponibles: " + getCuposDisponibles());
        System.out.println("Tarifa: " + tarifaBase);
    }
}
