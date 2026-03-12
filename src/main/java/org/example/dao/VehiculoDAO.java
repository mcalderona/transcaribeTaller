package org.example.dao;

import org.example.modelo.Vehiculo;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VehiculoDAO {
    //constantes que guardan los nombres de los archivos donde se almacenan los datos
    private static final String BUSETA_FILE = "buseta.txt";
    private static final String MICROBUS_FILE = "microbus.txt";
    private static final String BUS_FILE = "bus.txt";

    public void GuardarVehiculo(Vehiculo v) {
        String Archivo =ObtenerArchivo(v.getTipo());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Archivo, true))) {
            bw.write(v.getPlaca() + "," + v.getRuta());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String ObtenerArchivo(String tipo) {
        switch (tipo) {
            case "Buseta":
                return BUSETA_FILE;
            case "Microbus":
                return MICROBUS_FILE;
            case "Bus":
                return BUS_FILE;
                default:
                    return "vehiculo.txt";
        }
    }
}
