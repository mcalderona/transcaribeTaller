package org.example.servicio;

import org.example.dao.VehiculoDAO;
import org.example.modelo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class VehiculoServicio {
    private List<Vehiculo> vehiculos = new ArrayList<>();
    private VehiculoDAO dao = new VehiculoDAO();

    public boolean RegistrarVehiculo(Vehiculo v) {

        if (ValidarPlacaUnica(v.getPlaca()){
            vehiculos.add(v);
            dao.GuardarVehiculo(v);
            return true;
        }
        return false;
    }




}
