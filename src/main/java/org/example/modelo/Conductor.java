package org.example.modelo;

public class Conductor extends Persona {

    private String categoriaLicencia;
    private String numeroLicencia;

    public Conductor() {
        super();
    }

    public Conductor(String nombre, String cedula, String categoriaLicencia, String numeroLicencia) {
        super(nombre, cedula);
        this.categoriaLicencia = categoriaLicencia;
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public boolean tieneLicencia() {
        return true;
    }

    @Override
    public String imprimirDetalle() {
        return "=============== CONDUCTOR ===============" +
                "\n" + super.imprimirDetalle() +
                "\nNúmero de Licencia: " + numeroLicencia +
                "\nCategoría de Licencia: " + categoriaLicencia;
    }
}
