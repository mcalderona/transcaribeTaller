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

    
}
