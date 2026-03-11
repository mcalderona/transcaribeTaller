package org.example.modelo;

public class Buseta extends Vehiculo {
    //variables constantes privadas que solo la puede usar esta clase
    private static final int CAPACIDAD = 19;
    private static final double TARIFA = 8000;

    //constructor de la clase buseta, esta llamando al constructor de la clase padre(vehiculo)
    public Buseta(String placa, String ruta) {
        super(placa, ruta, CAPACIDAD, TARIFA);// envia los valores al constructor de vehiculo
    }

    @Override
    public String getTipo() {
        return "Buseta";
    }



    @Override
    public void ImprimirDetalle() {

        System.out.println("Tipo: Buseta");
        System.out.println("Placa: " + placa);
        System.out.println("Ruta: " + ruta);
        System.out.println("Cupos disponibles: " + getCuposDisponibles());
        System.out.println("Tarifa: " + tarifaBase);

    }



}
