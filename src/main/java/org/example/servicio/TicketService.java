package org.example.servicio;

import org.example.dao.TicketDAO;
import org.example.modelo.*;
import org.example.servicio.VehiculoServicio;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketService {

    private List<Ticket> tickets;
    private TicketDAO dao;

    private VehiculoServicio vehiculoServicio;
    private PersonaService personaService;

    public TicketService(VehiculoServicio vs, PersonaService ps) {

        vehiculoServicio = vs;
        personaService = ps;

        dao = new TicketDAO();

        tickets = dao.cargarTodos(ps.listarPasajeros(), vs.listarVehiculos());
    }

    // vender ticket
    public Ticket venderTicket(String cedula, String placa, String origen, String destino){

        Pasajero pasajero = personaService.buscarPasajero(cedula);
        Vehiculo vehiculo = vehiculoServicio.buscarPorPlaca(placa);

        if(pasajero == null || vehiculo == null){
            return null;
        }

        if(!vehiculoServicio.validarCuposDisponibles(vehiculo )){
            return null;
        }

        Ticket t = new Ticket(
                pasajero,
                vehiculo,
                LocalDate.now().toString(),
                origen,
                destino
        );

        vehiculoServicio.ocuparCupo(vehiculo);

        tickets.add(t);
        dao.guardar(t);

        return t;
    }

    // listar tickets
    public List<Ticket> listarTickets(){
        return tickets;
    }

    // total recaudado
    public double totalRecaudado(){

        double total = 0;

        for(Ticket t : tickets){
            total += t.getValorFinal();
        }

        return total;
    }

    //Lista de Pasajeros por tipo
    public void pasajerosPorTipo(){

        int estudiantes = 0;
        int adultosMayor = 0;
        int regulares = 0;

        for (Ticket t : tickets){

            String tipo = t.getPasajero().getTipo();

            if (tipo.equalsIgnoreCase("Estudiante")){
                estudiantes++;
            }
            else if (tipo.equalsIgnoreCase("Adulto Mayor")){
                adultosMayor++;
            }
            else{
                regulares++;
            }
        }

        System.out.println("Estudiantes: " + estudiantes);
        System.out.println("Adultos Mayores: " + adultosMayor);
        System.out.println("Regulares: " + regulares);
    }

    //Vehiculo con mas tickets vendidos
    public Vehiculo vehiculoConMasTickets(){

        Vehiculo vehiculoMax = null;
        int maxTickets = 0;

        for (Vehiculo v : vehiculoServicio.listarVehiculos()){

            int contador = 0;

            for (Ticket t : tickets){

                if (t.getVehiculo().getPlaca().equalsIgnoreCase(v.getPlaca())){
                    contador++;
                }
            }

            if (contador > maxTickets){
                maxTickets = contador;
                vehiculoMax = v;
            }
        }

        return vehiculoMax;
    }
}