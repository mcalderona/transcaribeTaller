package org.example.dao;

import org.example.modelo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    private static final String BUSETA_FILE   = "buseta.txt";
    private static final String MICROBUS_FILE = "microbus.txt";
    private static final String BUS_FILE      = "bus.txt";

    public void guardar(Vehiculo v) {
        String archivo = obtenerArchivo(v.getTipo());
        crearArchivoSiNoExiste(archivo);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            String codigoRuta = v.getRuta() != null ? v.getRuta().getCodigoRuta() : "";
            bw.write(v.getPlaca() + ";" + codigoRuta);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando vehículo: " + e.getMessage());
        }
    }

    public List<Vehiculo> cargarTodos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        cargarArchivo(BUS_FILE,      "Bus",      vehiculos);
        cargarArchivo(BUSETA_FILE,   "Buseta",   vehiculos);
        cargarArchivo(MICROBUS_FILE, "MicroBus", vehiculos);
        return vehiculos;
    }

    private void cargarArchivo(String archivo, String tipo, List<Vehiculo> lista) {
        File f = new File(archivo);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 2) continue;

                String placa       = datos[0];
                String codigoRuta  = datos[1];
                Vehiculo v = null;

                switch (tipo) {
                    case "Bus":      v = new Bus(placa, codigoRuta);      break;
                    case "Buseta":   v = new Buseta(placa, codigoRuta);   break;
                    case "MicroBus": v = new MicroBus(placa, codigoRuta); break;
                }
                if (v != null) lista.add(v);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo " + archivo + ": " + e.getMessage());
        }
    }

    private void crearArchivoSiNoExiste(String archivo) {
        File f = new File(archivo);
        if (!f.exists()) {
            try { f.createNewFile(); }
            catch (IOException e) { System.out.println("No se pudo crear: " + archivo); }
        }
    }

    private String obtenerArchivo(String tipo) {
        switch (tipo) {
            case "Buseta":   return BUSETA_FILE;
            case "MicroBus": return MICROBUS_FILE;
            case "Bus":      return BUS_FILE;
            default:         return "vehiculo.txt";
        }
    }
}