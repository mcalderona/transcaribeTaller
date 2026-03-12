package org.example.servicio;

import org.example.dao.*;
import org.example.modelo.*;

import java.util.List;

public class PersonaService {

    private List<Conductor> conductores;
    private List<Pasajero> pasajeros;
    private ConductorDAO conductorDAO;
    private PasajeroDAO pasajeroDAO;

    public PersonaService() {
        this.conductorDAO = new ConductorDAO();
        this.pasajeroDAO = new PasajeroDAO();
        this.conductores = conductorDAO.cargarTodos();
        this.pasajeros = pasajeroDAO.cargarTodos();
    }

    public boolean registrarConductor(Conductor c) {
        if (!validarLicencia(c)) return false;
        conductores.add(c);
        conductorDAO.guardar(c);
        return true;
    }

    public boolean registrarPasajero(Pasajero p) {
        pasajeros.add(p);
        pasajeroDAO.guardar(p);
        return true;
    }

    public boolean asignarConductor(Conductor c, Vehiculo v) {
        if (!validarLicencia(c)) return false;
        v.setConductor(c);
        return true;
    }

    public Pasajero buscarPasajero(String cedula) {
        return pasajeros.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst().orElse(null);
    }

    public Conductor buscarConductor(String cedula) {
        return conductores.stream()
                .filter(c -> c.getCedula().equals(cedula))
                .findFirst().orElse(null);
    }

    public List<Pasajero> listarPasajeros() {
        return pasajeros;
    }

    public List<Conductor> listarConductores() {
        return conductores;
    }

    private boolean validarLicencia(Conductor c) {
        return c.getNumerolicencia() != null && !c.getNumerolicencia().isEmpty();
    }
}