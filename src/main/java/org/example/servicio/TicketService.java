package org.example.servicio;

import org.example.dao.TicketDAO;
import org.example.modelo.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketService {

    private List<Ticket> tickets;
    private TicketDAO dao;
    private VehiculoServicio vehiculoService;
    private PersonaService personaService;

    public TicketService(VehiculoServicio vs, PersonaService ps) {
        this.vehiculoService = vs;
        this.personaService = ps;
        this.dao = new TicketDAO();
        this.tickets = dao.cargarTodos(ps.listarPasajeros(), vs.listarVehiculos());
    }

    public Ticket venderTicket(String cedula, String placa, String origen, String destino) {
        Pasajero pasajero = personaService.buscarPasajero(cedula);
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);

        if (pasajero == null || vehiculo == null) return null;
        if (!vehiculoService.validarCuposDisponibles(vehiculo)) return null;

        Ticket t = new Ticket(pasajero, vehiculo, LocalDate.now().toString(), origen, destino);
        vehiculoService.ocuparCupo(vehiculo);
        tickets.add(t);
        dao.guardar(t);
        return t;
    }

    public List<Ticket> listarTickets() {
        return tickets;
    }

    public double totalRecaudado() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.getValorFinal();
        }
        return total;
    }

    public Map<String, Integer> pasajerosPorTipo() {
        Map<String, Integer> mapa = new HashMap<>();
        for (Ticket t : tickets) {
            String tipo = t.getPasajero().getTipo();
            mapa.put(tipo, mapa.getOrDefault(tipo, 0) + 1);
        }
        return mapa;
    }

    public Vehiculo vehiculoConMasTickets() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Ticket t : tickets) {
            String placa = t.getVehiculo().getPlaca();
            conteo.put(placa, conteo.getOrDefault(placa, 0) + 1);
        }

        String placaMax = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                placaMax = entry.getKey();
            }
        }

        return vehiculoService.buscarPorPlaca(placaMax);
    }
}