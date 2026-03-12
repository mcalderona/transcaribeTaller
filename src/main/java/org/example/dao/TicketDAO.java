package org.example.dao;
import org.example.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private String ARCHIVOS = "tickets.txt";

    public void guardar(Ticket t){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVOS, true))) {
            writer.write( t.getPasajero().getCedula() + ";" + t.getVehiculo().getPlaca() + ";" + t.getFechaCompra() + ";" +
                            t.getOrigen() + ";" + t.getDestino() + ";" + t.getValorFinal());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar ticket: " + e.getMessage());
        }
    }

    public List<Ticket> cargarTodos(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Ticket> lista = new ArrayList<>();
        File archivo = new File(ARCHIVOS);
        if (!archivo.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] campos = linea.split(";");

                    Pasajero pasajero = buscarPasajero(pasajeros, campos[0]);
                    Vehiculo vehiculo = buscarVehiculo(vehiculos, campos[1]);

                    if (pasajero == null || vehiculo == null) continue;

                    Ticket t = new Ticket(pasajero, vehiculo, campos[2], campos[3], campos[4]);
                    t.setValorFinal(Double.parseDouble(campos[5]));
                    lista.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tickets: " + e.getMessage());
        }

        return lista;
    }

    private Pasajero buscarPasajero(List<Pasajero> pasajeros, String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) return p;
        }
        return null;
    }

    private Vehiculo buscarVehiculo(List<Vehiculo> vehiculos, String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa)) return v;
        }
        return null;
    }
}
