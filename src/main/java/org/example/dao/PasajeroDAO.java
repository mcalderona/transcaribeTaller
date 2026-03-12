package org.example.dao;
import org.example.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDAO {

    private String ARCHIVOS = "pasajeros.txt";

    public void guardar(Pasajero p) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVOS, true))) {
            writer.write(p.getCedula() + ";" + p.getNombre() + ";" + p.getTipo());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar pasajero: " + e.getMessage());
        }
    }

    public List<Pasajero> cargarTodos() {
        List<Pasajero> lista = new ArrayList<>();
        File archivo = new File(ARCHIVOS);
        if (!archivo.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] campos = linea.split(";");
                    Pasajero p = crearPasajero(campos[0], campos[1], campos[2]);
                    if (p != null) lista.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pasajeros: " + e.getMessage());
        }

        return lista;
    }

    private Pasajero crearPasajero(String cedula, String nombre, String tipo) {
        switch (tipo.toLowerCase()) {
            case "estudiante":
                return new PasajeroEstudiante(nombre, cedula, tipo);
            case "adulto mayor":
                return new PasajeroAdultoMayor(nombre, cedula, tipo);
            default:
                return new PasajeroRegular(nombre, cedula, tipo);
        }
    }
}