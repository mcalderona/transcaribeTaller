package org.example.dao;

import org.example.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAO {

    private static final String CONDUCTORES_FILE = "conductores.txt";

    // Guardar conductor
    public void guardar(Conductor c) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONDUCTORES_FILE, true))) {

            bw.write(
                    c.getCedula() + ";" +
                            c.getNombre() + ";" +
                            c.getNumeroLicencia()+ ";" +
                            c.getCategoriaLicencia()
            );

            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error guardando conductor");
        }
    }

    // Cargar todos los conductores
    public List<Conductor> cargarTodos() {

        List<Conductor> conductores = new ArrayList<>();

        File archivo = new File(CONDUCTORES_FILE);
        if (!archivo.exists()) return conductores;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                String cedula = datos[0];
                String nombre = datos[1];
                String numeroLicencia = datos[2];
                String categoria = datos[3];

                Conductor c = new Conductor(nombre, cedula, categoria, numeroLicencia);

                conductores.add(c);
            }

        } catch (IOException e) {
            System.out.println("Error leyendo conductores");
        }

        return conductores;
    }
}