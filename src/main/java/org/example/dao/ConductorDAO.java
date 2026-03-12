package org.example.dao;
import org.example.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAO {

    private String ARCHIVOS = "conductores.txt";

    public void guardar(Conductor c) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVOS, true))) {
            writer.write(c.getCedula() + ";" + c.getNombre() + ";" + c.getNumerolicencia() + ";" + c.getCategorialicencia());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar conductor: " + e.getMessage());
        }
    }

    public List<Conductor> cargarTodos() {
        List<Conductor> lista = new ArrayList<>();
        File archivo = new File(ARCHIVOS);
        if (!archivo.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] campos = linea.split(";");
                    Conductor c = new Conductor(campos[1], campos[0], campos[3], campos[2]);
                    lista.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conductores: " + e.getMessage());
        }

        return lista;
    }
}