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

    public boolean registrarConductor(Conductor c){

        if(validarLicencia(c)){
            conductores.add(c);
            conductorDAO.guardar(c);
            return true;
        }

        return false;
    }

    public boolean registrarPasajero(Pasajero p){

        pasajeros.add(p);
        pasajeroDAO.guardar(p);
        return true;

    }

    public Pasajero buscarPasajero(String cedula){

        for(Pasajero p : pasajeros){
            if(p.getCedula().equalsIgnoreCase(cedula)){
                return p;
            }
        }

        return null;
    }

    public Conductor buscarConductor(String cedula){

        for(Conductor c : conductores){
            if(c.getCedula().equalsIgnoreCase(cedula)){
                return c;
            }
        }

        return null;
    }

    public boolean asignarConductor(Conductor c, Vehiculo v){

        if(validarLicencia(c)){
            v.setConductor(c);
            return true;
        }

        return false;
    }

    public List<Pasajero> listarPasajeros(){
        return pasajeros;
    }

    public List<Conductor> listarConductores(){
        return conductores;
    }

    private boolean validarLicencia(Conductor c){

        if(c.getNumeroLicencia() != null && !c.getNumeroLicencia().isEmpty()){
            return true;
        }

        return false;
    }
}