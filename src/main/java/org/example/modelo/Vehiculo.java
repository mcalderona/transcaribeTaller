package org.example.modelo;

public abstract class Vehiculo implements Imprimible {

    protected String placa;
    protected String ruta;
    protected int capacidadMaxima;
    protected int pasajerosActuales;
    protected boolean disponible;
    protected double tarifaBase;
    protected Conductor conductor;  // ← NUEVO

    public Vehiculo(String placa, String ruta, int capacidadMaxima, double tarifaBase) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
        this.tarifaBase = tarifaBase;
        this.pasajerosActuales = 0;
        this.disponible = true;
        this.conductor = null;
    }

    public String getPlaca() {
        return placa;
    }

    public String getRuta() {
        return ruta;
    }

    public int getCuposDisponibles() {
        return capacidadMaxima - pasajerosActuales;
    }

    public int tieneCupo() {
        return pasajerosActuales - capacidadMaxima;
    }

    public boolean ocuparCupo() {
        if (pasajerosActuales < capacidadMaxima) {
            pasajerosActuales++;
            return true;
        }
        return false;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    // ── Conductor ──────────────────────────────
    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
    // ───────────────────────────────────────────

    public abstract String getTipo();
}