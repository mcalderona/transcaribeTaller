package org.example.dao;

import org.example.modelo.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDAO {

    private static final String PASAJEROS_FILE = "pasajeros.txt";

    public void guardar(Pasajero p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PASAJEROS_FILE, true))) {
            bw.write(
                    p.getCedula() + ";" +
                            p.getNombre() + ";" +
                            p.getTipo() + ";" +
                            (p.getFechaNacimiento() != null ? p.getFechaNacimiento().toString() : "")
            );
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando pasajero");
        }
    }

    public List<Pasajero> cargarTodos() {
        List<Pasajero> pasajeros = new ArrayList<>();
        File archivo = new File(PASAJEROS_FILE);
        if (!archivo.exists()) return pasajeros;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";", -1);
                String cedula = datos[0];
                String nombre = datos[1];
                String tipo   = datos[2];
                // Campo fechaNacimiento es opcional (datos anteriores no lo tienen)
                LocalDate fecha = (datos.length > 3 && !datos[3].isEmpty())
                        ? LocalDate.parse(datos[3]) : null;

                Pasajero p = crearPasajero(nombre, cedula, tipo, fecha);
                if (p != null) pasajeros.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo pasajeros");
        }
        return pasajeros;
    }

    private Pasajero crearPasajero(String nombre, String cedula, String tipo, LocalDate fecha) {
        switch (tipo) {
            case "Estudiante":
                return new PasajeroEstudiante(nombre, cedula, tipo, fecha);
            case "Adulto Mayor":
                return new PasajeroAdultoMayor(nombre, cedula, tipo, fecha);
            default:
                return new PasajeroRegular(nombre, cedula, "Regular", fecha);
        }
    }
}