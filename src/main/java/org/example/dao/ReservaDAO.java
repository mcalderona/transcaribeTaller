package org.example.dao;

import org.example.modelo.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private static final String RESERVAS_FILE = "reservas.txt";

    public void guardar(Reserva r) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESERVAS_FILE, true))) {

            bw.write(
                    r.getCodigoReserva() + ";" +
                            r.getPasajero().getCedula() + ";" +
                            r.getVehiculo().getPlaca() + ";" +
                            r.getFechaCreacion().toString() + ";" +
                            r.getFechaViaje().toString() + ";" +
                            r.getEstado()
            );

            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error guardando reserva");
        }
    }

    public void actualizarEstado(String codigo, String nuevoEstado) {

        File archivo = new File(RESERVAS_FILE);
        if (!archivo.exists()) return;

        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos[0].equals(codigo)) {
                    datos[5] = nuevoEstado;
                    linea    = String.join(";", datos);
                }

                lineas.add(linea);
            }

        } catch (IOException e) {
            System.out.println("Error leyendo reservas");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {

            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error actualizando reserva");
        }
    }

    public List<Reserva> cargarTodos(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {

        List<Reserva> reservas = new ArrayList<>();

        File archivo = new File(RESERVAS_FILE);
        if (!archivo.exists()) return reservas;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");

                String        codigo        = datos[0];
                String        cedula        = datos[1];
                String        placa         = datos[2];
                LocalDateTime fechaCreacion = LocalDateTime.parse(datos[3]);
                LocalDate     fechaViaje    = LocalDate.parse(datos[4]);
                Estado        estado        = Estado.valueOf(datos[5]);

                Pasajero pasajero = buscarPasajero(pasajeros, cedula);
                Vehiculo vehiculo = buscarVehiculo(vehiculos, placa);

                if (pasajero != null && vehiculo != null) {

                    Reserva r = new Reserva(codigo, pasajero, vehiculo, fechaCreacion, fechaViaje);
                    r.setEstado(estado);

                    reservas.add(r);
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo reservas");
        }

        return reservas;
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
