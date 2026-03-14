package org.example.dao;

import org.example.modelo.Bus;
import org.example.modelo.Buseta;
import org.example.modelo.MicroBus;
import org.example.modelo.Vehiculo;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {
    //constantes que guardan los nombres de los archivos donde se almacenan los datos
    private static final String BUSETA_FILE = "buseta.txt";
    private static final String MICROBUS_FILE = "microbus.txt";
    private static final String BUS_FILE = "bus.txt";

    public void guardar(Vehiculo v) {
        String Archivo = obtenerArchivo(v.getTipo());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Archivo, true))) {
            bw.write(v.getPlaca() + ";" + v.getRuta());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
     //metodo cargarTodo
    public List<Vehiculo> cargarTodos(){
        List<Vehiculo> vehiculos = new ArrayList<>();
        cargarArchivo(BUS_FILE, "Bus" , vehiculos);
        cargarArchivo(BUSETA_FILE, "Buseta" , vehiculos);
        cargarArchivo(MICROBUS_FILE, "MicroBus" , vehiculos);
        return vehiculos;

    }
    //Leer archivo
    private void cargarArchivo(String archivo, String tipo, List<Vehiculo> lista){
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] datos = linea.split(";");
                String placa = datos[0];
                String ruta = datos[1];
                Vehiculo v =null;
                        switch (tipo){
                            case "Bus":
                                v = new Bus(placa,ruta);
                                break;
                            case "Buseta":
                                v = new Buseta(placa,ruta);
                                break;
                            case "Microbus":
                                v = new MicroBus(placa,ruta);
                                break;
                }
                if (v != null){
                    lista.add(v);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void actualizarVehiculo(Vehiculo v){
        String archivo = obtenerArchivo(v.getTipo());
        List<Vehiculo> lista = cargarTodos();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
            for (Vehiculo v1  : lista){
                bw.write(v.getPlaca() + ";" + v.getRuta());
                bw.newLine();
            }
        }catch (IOException e){
            System.out.println("Error actualizando vehiculo");
        }
    }
    private String obtenerArchivo(String tipo) {
        switch (tipo) {
            case "Buseta":
                return BUSETA_FILE;
            case "MicroBus":
                return MICROBUS_FILE;
            case "Bus":
                return BUS_FILE;
                default:
                    return "vehiculo.txt";
        }
    }
}
