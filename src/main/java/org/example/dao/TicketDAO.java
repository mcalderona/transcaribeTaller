package org.example.dao;

import org.example.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private static final String TICKETS_FILE = "tickets.txt";

    public void guardar(Ticket t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TICKETS_FILE, true))) {

            bw.write(
                    t.getPasajero().getCedula() + ";" +
                            t.getVehiculo().getPlaca() + ";" +
                            t.getFechaCompra() + ";" +
                            t.getOrigen() + ";" +
                            t.getDestino() + ";" +
                            t.getValorFinal()
            );

            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error guardando ticket");
        }
    }

    public List<Ticket> cargarTodos(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {

        List<Ticket> tickets = new ArrayList<>();

        File archivo = new File(TICKETS_FILE);
        if (!archivo.exists()) return tickets;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                String cedula = datos[0];
                String placa = datos[1];
                String fecha = datos[2];
                String origen = datos[3];
                String destino = datos[4];
                double valor = Double.parseDouble(datos[5]);

                Pasajero pasajero = buscarPasajero(pasajeros, cedula);
                Vehiculo vehiculo = buscarVehiculo(vehiculos, placa);

                if (pasajero != null && vehiculo != null) {

                    Ticket t = new Ticket(pasajero, vehiculo, fecha, origen, destino);
                    t.setValorFinal(valor);

                    tickets.add(t);
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo tickets");
        }

        return tickets;
    }

    private Pasajero buscarPasajero(List<Pasajero> pasajeros, String cedula) {

        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }

        return null;
    }

    private Vehiculo buscarVehiculo(List<Vehiculo> vehiculos, String placa) {

        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }

        return null;
    }
}