package org.example.dao;

import org.example.modelo.Ruta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RutaDAO {

    private static final String RUTAS_FILE = "rutas.txt";

    public void guardar(Ruta r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTAS_FILE, true))) {
            bw.write(
                    r.getCodigoRuta() + ";" +
                            r.getCiudadOrigen() + ";" +
                            r.getCiudadDestino() + ";" +
                            r.getDistanciaKm() + ";" +
                            r.getTiempoEstimadoMinutos()
            );
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando ruta: " + e.getMessage());
        }
    }

    public List<Ruta> cargarTodos() {
        List<Ruta> rutas = new ArrayList<>();
        File archivo = new File(RUTAS_FILE);
        if (!archivo.exists()) return rutas;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] d = linea.split(";");
                if (d.length < 5) continue;
                rutas.add(new Ruta(d[0], d[1], d[2],
                        Double.parseDouble(d[3]),
                        Integer.parseInt(d[4])));
            }
        } catch (IOException e) {
            System.out.println("Error leyendo rutas: " + e.getMessage());
        }
        return rutas;
    }

    public Ruta buscarPorCodigo(String codigo) {
        for (Ruta r : cargarTodos()) {
            if (r.getCodigoRuta().equalsIgnoreCase(codigo)) return r;
        }
        return null;
    }
}
