package org.example.modelo;

public abstract class Vehiculo implements Imprimible {

    protected String placa;
    protected Ruta ruta;                // CAMBIADO: era String ruta
    protected int capacidadMaxima;
    protected int pasajerosActuales;
    protected boolean disponible;
    protected double tarifaBase;
    protected Conductor conductor;

    public Vehiculo(String placa, Ruta ruta, int capacidadMaxima, double tarifaBase) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
        this.tarifaBase = tarifaBase;
        this.pasajerosActuales = 0;
        this.disponible = true;
        this.conductor = null;
    }


    public Vehiculo(String placa, String rutaCodigo, int capacidadMaxima, double tarifaBase) {
        this.placa = placa;

        this.ruta = new Ruta(rutaCodigo, "", "", 0, 0);
        this.capacidadMaxima = capacidadMaxima;
        this.tarifaBase = tarifaBase;
        this.pasajerosActuales = 0;
        this.disponible = true;
        this.conductor = null;
    }

    public String getPlaca() { return placa; }

    public Ruta getRuta() { return ruta; }
    public void setRuta(Ruta ruta) { this.ruta = ruta; }

  
    public String getRutaString() {
        return ruta != null ? ruta.getCodigoRuta() : "";
    }

    public int getCuposDisponibles() { return capacidadMaxima - pasajerosActuales; }

    public int tieneCupo() { return pasajerosActuales - capacidadMaxima; }

    public boolean ocuparCupo() {
        if (pasajerosActuales < capacidadMaxima) {
            pasajerosActuales++;
            return true;
        }
        return false;
    }

    public double getTarifaBase() { return tarifaBase; }

    public Conductor getConductor() { return conductor; }
    public void setConductor(Conductor conductor) { this.conductor = conductor; }

    public abstract String getTipo();
}