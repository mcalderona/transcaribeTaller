package org.example.servicio;

import org.example.dao.TicketDAO;
import org.example.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Ticket venderTicket(String cedula, String placa, String origen, String destino) {

        Pasajero pasajero = personaService.buscarPasajero(cedula);
        Vehiculo vehiculo = vehiculoServicio.buscarPorPlaca(placa);

        if (pasajero == null || vehiculo == null) return null;

        if (!vehiculoServicio.validarCuposDisponibles(vehiculo)) return null;

        LocalDate hoy = LocalDate.now();
        int ticketsHoy = contarTicketsHoy(cedula, hoy);

        if (ticketsHoy >= 3) {
            System.out.println("Venta rechazada: el pasajero ya tiene " + ticketsHoy + " tickets para hoy.");
            return null;
        }

        Ticket t = new Ticket(pasajero, vehiculo, hoy.toString(), origen, destino);

        if (esFestivo(hoy)) {
            t.setValorFinal(t.getValorFinal() * 1.20);
        }

        vehiculoServicio.ocuparCupo(vehiculo);
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

    public void pasajerosPorTipo() {
        int estudiantes = 0;
        int adultosMayor = 0;
        int regulares = 0;

        for (Ticket t : tickets) {
            String tipo = t.getPasajero().getTipo();
            if (tipo.equalsIgnoreCase("Estudiante")) {
                estudiantes++;
            } else if (tipo.equalsIgnoreCase("Adulto Mayor")) {
                adultosMayor++;
            } else {
                regulares++;
            }
        }

        System.out.println("Estudiantes  : " + estudiantes);
        System.out.println("Adultos Mayor: " + adultosMayor);
        System.out.println("Regulares    : " + regulares);
    }

    public Vehiculo vehiculoConMasTickets() {
        Vehiculo vehiculoMax = null;
        int maxTickets = 0;

        for (Vehiculo v : vehiculoServicio.listarVehiculos()) {
            int contador = 0;
            for (Ticket t : tickets) {
                if (t.getVehiculo().getPlaca().equalsIgnoreCase(v.getPlaca())) {
                    contador++;
                }
            }
            if (contador > maxTickets) {
                maxTickets = contador;
                vehiculoMax = v;
            }
        }

        return vehiculoMax;
    }

    private int contarTicketsHoy(String cedula, LocalDate hoy) {
        int contador = 0;
        for (Ticket t : tickets) {
            if (t.getPasajero().getCedula().equalsIgnoreCase(cedula) &&
                    t.getFechaCompra().equals(hoy.toString())) {
                contador++;
            }
        }
        return contador;
    }

    private boolean esFestivo(LocalDate fecha) {
        LocalDate[] festivos = {
                LocalDate.of(fecha.getYear(), 1, 1),
                LocalDate.of(fecha.getYear(), 3, 15),
                LocalDate.of(fecha.getYear(), 7, 20),
                LocalDate.of(fecha.getYear(), 8, 7),
                LocalDate.of(fecha.getYear(), 12, 8),
                LocalDate.of(fecha.getYear(), 12, 25)
        };
        for (LocalDate f : festivos) {
            if (f.equals(fecha)) return true;
        }
        return false;
    }

    public List<Ticket> ticketsPorFecha(String fecha) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getFechaCompra().equalsIgnoreCase(fecha)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Ticket> ticketsPorTipoVehiculo(String tipoVehiculo) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getVehiculo().getTipo().equalsIgnoreCase(tipoVehiculo)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Ticket> ticketsPorTipoPasajero(String tipoPasajero) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getPasajero().getTipo().equalsIgnoreCase(tipoPasajero)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}