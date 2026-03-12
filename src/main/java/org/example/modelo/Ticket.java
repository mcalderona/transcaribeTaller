package org.example.modelo;

public class Ticket implements Calculable, Imprimible {

    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private String fechaCompra;
    private String origen;
    private String destino;
    private double valorFinal;

    public Ticket() {
    }

    public Ticket(Pasajero pasajero, Vehiculo vehiculo, String fechaCompra, String origen, String destino) {
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = fechaCompra;
        this.origen = origen;
        this.destino = destino;
        this.valorFinal = calcularTotal();
    }

    public Pasajero getPasajero() {
        return pasajero;
    }public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero; }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo; }

    public String getFechaCompra() {
        return fechaCompra;
    }public void setFechaCompra(String FechaCompra) {
        this.fechaCompra = FechaCompra;}

    public String getOrigen() {
        return origen;
    }public void setOrigen(String origen) {
        this.origen = origen;}

    public String getDestino() {
        return destino;
    }public void setDestino(String Destino) {
        this.destino = Destino;}

    public double getValorFinal() {
        return valorFinal;
    }public void setValorFinal(double ValorFinal) {
        this.valorFinal = ValorFinal;}



    public double calcularTotal() {
        double tarifaBase = vehiculo.getTarifaBase();
        double descuento = pasajero.CalcularDescuento();
        return tarifaBase - (tarifaBase * descuento);
    }

    public String imprimirDetalle() {
        return  "\n===========TICKET==========="+
                "\nFecha de Compra: " + fechaCompra +
                "\nOrigen: " + origen +
                "\nDestino: " + destino +
                "\nPasajero: " + pasajero.getNombre() +
                "\nVehículo: " + vehiculo.getPlaca() +
                "\nValor Final: $" + valorFinal;
    }
}
