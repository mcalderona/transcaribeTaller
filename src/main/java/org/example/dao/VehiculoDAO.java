package org.example.dao;

import org.example.modelo.Bus;
import org.example.modelo.Buseta;
import org.example.modelo.MicroBus;
import org.example.modelo.Vehiculo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {
    private static final String BUSETA_FILE = "buseta.txt";
    private static final String MICROBUS_FILE = "microbus.txt";
    private static final String BUS_FILE = "bus.txt";

    public void guardar(Vehiculo v) {
        String archivo = obtenerArchivo(v.getTipo());
        // ✅ Crea el archivo si no existe antes de escribir
        crearArchivoSiNoExiste(archivo);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(v.getPlaca() + ";" + v.getRuta());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando vehículo: " + e.getMessage());
        }
    }

    public List<Vehiculo> cargarTodos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        cargarArchivo(BUS_FILE, "Bus", vehiculos);
        cargarArchivo(BUSETA_FILE, "Buseta", vehiculos);
        cargarArchivo(MICROBUS_FILE, "MicroBus", vehiculos);
        return vehiculos;
    }

    private void cargarArchivo(String archivo, String tipo, List<Vehiculo> lista) {
        File f = new File(archivo);
        // ✅ Si el archivo no existe, simplemente no hay nada que cargar
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // ✅ Ignora líneas vacías o mal formadas
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 2) continue;

                String placa = datos[0];
                String ruta = datos[1];
                Vehiculo v = null;

                switch (tipo) {
                    case "Bus":
                        v = new Bus(placa, ruta);
                        break;
                    case "Buseta":
                        v = new Buseta(placa, ruta);
                        break;
                    case "MicroBus":
                        v = new MicroBus(placa, ruta);
                        break;
                }
                if (v != null) {
                    lista.add(v);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo " + archivo + ": " + e.getMessage());
        }
    }

    public void actualizarVehiculo(Vehiculo v) {
        String archivo = obtenerArchivo(v.getTipo());
        List<Vehiculo> lista = cargarTodos();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Vehiculo v1 : lista) {
                // ✅ Corregido: usa v1 (el de la lista), no v
                bw.write(v1.getPlaca() + ";" + v1.getRuta());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error actualizando vehículo: " + e.getMessage());
        }
    }

    // ✅ Método nuevo: crea el archivo vacío si no existe
    private void crearArchivoSiNoExiste(String archivo) {
        File f = new File(archivo);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("No se pudo crear el archivo: " + archivo);
            }
        }
    }

    private String obtenerArchivo(String tipo) {
        switch (tipo) {
            case "Buseta": return BUSETA_FILE;
            case "MicroBus": return MICROBUS_FILE;
            case "Bus": return BUS_FILE;
            default: return "vehiculo.txt";
        }
    }
}