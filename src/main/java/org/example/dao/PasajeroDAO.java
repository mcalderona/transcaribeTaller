package org.example.dao;

import org.example.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDAO {

    private static final String PASAJEROS_FILE = "pasajeros.txt";

    // Guardar pasajero
    public void guardar(Pasajero p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PASAJEROS_FILE, true))) {

            bw.write(p.getCedula() + ";" + p.getNombre() + ";" + p.getTipo());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error guardando pasajero");
        }
    }

    // Cargar todos los pasajeros
    public List<Pasajero> cargarTodos() {

        List<Pasajero> pasajeros = new ArrayList<>();

        File archivo = new File(PASAJEROS_FILE);
        if (!archivo.exists()) return pasajeros;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                String cedula = datos[0];
                String nombre = datos[1];
                String tipo = datos[2];

                Pasajero p = crearPasajero(nombre, cedula, tipo);

                if (p != null) {
                    pasajeros.add(p);
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo pasajeros");
        }

        return pasajeros;
    }

    // Crear pasajero según tipo
    private Pasajero crearPasajero(String nombre, String cedula, String tipo) {

        switch (tipo) {

            case "Estudiante":
                return new PasajeroEstudiante(nombre, cedula, tipo);

            case "Adulto Mayor":
                return new PasajeroAdultoMayor(nombre, cedula, tipo);

            default:
                return new PasajeroRegular(nombre, cedula, tipo);
        }
    }
}