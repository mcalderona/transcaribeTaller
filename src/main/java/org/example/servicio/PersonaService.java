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

        conductorDAO = new ConductorDAO();
        pasajeroDAO = new PasajeroDAO();

        conductores = conductorDAO.cargarTodos();
        pasajeros = pasajeroDAO.cargarTodos();
    }

    // Registrar conductor
    public boolean registrarConductor(Conductor c){

        if(validarLicencia(c)){
            conductores.add(c);
            conductorDAO.guardar(c);
            return true;
        }

        return false;
    }

    // Registrar pasajero
    public boolean registrarPasajero(Pasajero p){

        pasajeros.add(p);
        pasajeroDAO.guardar(p);
        return true;

    }

    // Buscar pasajero
    public Pasajero buscarPasajero(String cedula){

        for(Pasajero p : pasajeros){
            if(p.getCedula().equalsIgnoreCase(cedula)){
                return p;
            }
        }

        return null;
    }

    // Buscar conductor
    public Conductor buscarConductor(String cedula){

        for(Conductor c : conductores){
            if(c.getCedula().equalsIgnoreCase(cedula)){
                return c;
            }
        }

        return null;
    }

    // Asignar conductor a vehiculo
    public boolean asignarConductor(Conductor c, Vehiculo v){

        if(validarLicencia(c)){
            v.setConductor(c);
            return true;
        }

        return false;
    }

    // Listar pasajeros
    public List<Pasajero> listarPasajeros(){
        return pasajeros;
    }

    // Listar conductores
    public List<Conductor> listarConductores(){
        return conductores;
    }

    // Validar licencia
    private boolean validarLicencia(Conductor c){

        if(c.getNumeroLicencia() != null && !c.getNumeroLicencia().isEmpty()){
            return true;
        }

        return false;
    }
}