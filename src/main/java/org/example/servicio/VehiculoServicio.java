package org.example.servicio;

import org.example.dao.VehiculoDAO;
import org.example.modelo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class VehiculoServicio {
    private List<Vehiculo> vehiculos = new ArrayList<>();
    private VehiculoDAO dao = new VehiculoDAO();

    public boolean registrarVehiculo(Vehiculo v) {

        if (validarPlacaUnica(v.getPlaca())){
            vehiculos.add(v);
            dao.guardarVehiculo(v);
            return true;
        }
        return false;
    }

    public Vehiculo buscarPorPlaca(String placa){
        for (Vehiculo v : vehiculos){
            if (v.getPlaca().equalsIgnoreCase(placa)){
                return v;
            }
        }
        return null;
    }

    public List<Vehiculo> listarVehiculos(){
        return vehiculos;
    }

    public boolean validarPlacaUnica(String placa){
        for (Vehiculo v : vehiculos){
            if (v.getPlaca().equalsIgnoreCase(placa)){
                return false;
            }
        }
        return true;

    }
    public boolean validarCuposDisponibles(Vehiculo v){
        if (v.getCuposDisponibles() > 0){
            return true;
        }
        return false;
    }
        public boolean ocuparCupo(Vehiculo v){
        if(validarCuposDisponibles(v)){
            v.ocuparCupo();
            return true;
        }
        return false;
        }
        public void mostarVehiculo(){
        for (Vehiculo v : vehiculos){
            v.imprimirDetalle();
            System.out.println("_________________________");
        }
        }

}
