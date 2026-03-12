package org.example.dao;

import org.example.modelo.Vehiculo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VehiculoDAO {
    //constantes que guardan los nombres de los archivos donde se almacenan los datos
    private static final String BUSETA_FILE = "buseta.txt";
    private static final String MICROBUES_FILE = "microbus.txt";
    private static final String BUS_FILE = "bus.txt";

    public void GuardarVehiculo(Vehiculo v) {
        String Archivo =ObtenerArchivo(v.getTipo());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Archivo, true))) {
            bw.write(v.placa + "," + v.ruta);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
