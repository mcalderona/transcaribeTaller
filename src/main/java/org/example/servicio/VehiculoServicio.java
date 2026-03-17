package org.example.servicio;

import org.example.dao.VehiculoDAO;
import org.example.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class VehiculoServicio {

    private List<Vehiculo> vehiculos = new ArrayList<>();
    private VehiculoDAO    dao;
    private RutaServicio   rutaServicio;

    public VehiculoServicio(RutaServicio rutaServicio) {
        this.dao          = new VehiculoDAO();
        this.rutaServicio = this.rutaServicio;
        vehiculos = dao.cargarTodos();

        enriquecerRutas();
    }

    private void enriquecerRutas() {
        for (Vehiculo v : vehiculos) {
            if (v.getRuta() != null && !v.getRuta().getCodigoRuta().isEmpty()) {
                Ruta rutaCompleta = rutaServicio.buscarPorCodigo(v.getRuta().getCodigoRuta());
                if (rutaCompleta != null) v.setRuta(rutaCompleta);
            }
        }
    }

    public boolean registrarVehiculo(Vehiculo v) {
        if (validarPlacaUnica(v.getPlaca())) {
            vehiculos.add(v);
            dao.guardar(v);
            return true;
        }
        return false;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }

    public List<Vehiculo> listarVehiculos()    { return vehiculos; }

    public boolean validarPlacaUnica(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return false;
        }
        return true;
    }

    public boolean validarCuposDisponibles(Vehiculo v) {
        return v.getCuposDisponibles() > 0;
    }

    public boolean ocuparCupo(Vehiculo v) {
        if (validarCuposDisponibles(v)) {
            v.ocuparCupo();
            return true;
        }
        return false;
    }

    public void mostrarVehiculo() {
        for (Vehiculo v : vehiculos) {
            System.out.println(v.imprimirDetalle());
            System.out.println("_________________________");
        }
    }
}