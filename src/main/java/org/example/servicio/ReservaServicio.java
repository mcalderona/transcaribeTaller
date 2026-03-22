package org.example.servicio;

import org.example.dao.ReservaDAO;
import org.example.modelo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaServicio {

    private List<Reserva>    reservas;
    private ReservaDAO       dao;
    private VehiculoServicio vehiculoServicio;
    private PersonaService   personaService;
    private TicketService    ticketService;

    private int contadorCodigo;

    public ReservaServicio(VehiculoServicio vehiculoServicio,
                           PersonaService   personaService,
                           TicketService    ticketService) {
        this.vehiculoServicio = vehiculoServicio;
        this.personaService   = personaService;
        this.ticketService    = ticketService;
        this.dao              = new ReservaDAO();
        this.reservas         = dao.cargarTodos(personaService.listarPasajeros(),
                vehiculoServicio.listarVehiculos());
        this.contadorCodigo   = reservas.size() + 1;
        verificarVencidas();
    }

    public Reserva crearReserva(String cedula, String placa, LocalDate fechaViaje) {

        Pasajero pasajero = personaService.buscarPasajero(cedula);
        Vehiculo vehiculo = vehiculoServicio.buscarPorPlaca(placa);

        if (pasajero == null) {
            System.out.println("Error: no existe un pasajero con cédula " + cedula + ".");
            return null;
        }
        if (vehiculo == null) {
            System.out.println("Error: no existe un vehículo con placa " + placa + ".");
            return null;
        }

        if (fechaViaje.isBefore(LocalDate.now())) {
            System.out.println("Error: la fecha de viaje no puede ser anterior a hoy.");
            return null;
        }

        int reservasActivas = contarReservasActivas(vehiculo);
        int cuposOcupados   = (vehiculo.getCapacidadMaxima() - vehiculo.getCuposDisponibles())
                + reservasActivas;

        if (cuposOcupados >= vehiculo.getCapacidadMaxima()) {
            System.out.println("Error: el vehículo " + placa + " no tiene cupos disponibles.");
            return null;
        }

        if (tieneReservaActiva(cedula, placa, fechaViaje)) {
            System.out.println("Error: el pasajero ya tiene una reserva activa para ese " +
                    "vehículo en la fecha " + fechaViaje + ".");
            return null;
        }

        String  codigo  = generarCodigo();
        Reserva reserva = new Reserva(codigo, pasajero, vehiculo, LocalDateTime.now(), fechaViaje);

        reservas.add(reserva);
        dao.guardar(reserva);

        System.out.println("Reserva creada exitosamente. Código: " + codigo);
        return reserva;
    }

    public boolean cancelarReserva(String codigoReserva) {

        Reserva reserva = buscarPorCodigo(codigoReserva);

        if (reserva == null) {
            System.out.println("Error: no existe una reserva con código " + codigoReserva + ".");
            return false;
        }
        if (!reserva.estaActiva()) {
            System.out.println("La reserva " + codigoReserva +
                    " ya está " + reserva.getEstado() + " y no puede cancelarse.");
            return false;
        }

        reserva.cancelar();
        dao.actualizarEstado(codigoReserva, reserva.getEstado().toString());

        System.out.println("Reserva " + codigoReserva + " cancelada. Cupo liberado.");
        return true;
    }

    public Ticket convertirATicket(String codigoReserva) {

        Reserva reserva = buscarPorCodigo(codigoReserva);

        if (reserva == null) {
            System.out.println("Error: no existe una reserva con código " + codigoReserva + ".");
            return null;
        }
        if (!reserva.estaActiva()) {
            System.out.println("Error: la reserva " + codigoReserva +
                    " no está activa (estado: " + reserva.getEstado() + ").");
            return null;
        }

        Ruta ruta = reserva.getVehiculo().getRuta();
        String origen  = ruta != null ? ruta.getCiudadOrigen()  : "";
        String destino = ruta != null ? ruta.getCiudadDestino() : "";

        Ticket ticket = ticketService.venderTicket(
                reserva.getPasajero().getCedula(),
                reserva.getVehiculo().getPlaca(),
                origen,
                destino);

        if (ticket == null) {
            System.out.println("Error al generar el ticket para la reserva " + codigoReserva + ".");
            return null;
        }

        reserva.convertir();
        dao.actualizarEstado(codigoReserva, reserva.getEstado().toString());

        System.out.println("Reserva " + codigoReserva + " convertida en ticket exitosamente.");
        return ticket;
    }

    public int verificarVencidas() {

        int canceladas = 0;

        for (Reserva r : reservas) {
            if (r.estaActiva() && r.estaVencida()) {
                r.cancelar();
                dao.actualizarEstado(r.getCodigoReserva(), r.getEstado().toString());
                canceladas++;
                System.out.println("Reserva " + r.getCodigoReserva() +
                        " vencida y cancelada automáticamente.");
            }
        }

        System.out.println("Verificación completada. Reservas canceladas: " + canceladas);
        return canceladas;
    }

    public List<Reserva> listarReservasActivas() {

        List<Reserva> activas = new ArrayList<>();

        for (Reserva r : reservas) {
            if (r.estaActiva()) activas.add(r);
        }

        return activas;
    }

    public List<Reserva> historialPasajero(String cedula) {

        List<Reserva> historial = new ArrayList<>();

        for (Reserva r : reservas) {
            if (r.getPasajero().getCedula().equalsIgnoreCase(cedula)) {
                historial.add(r);
            }
        }

        return historial;
    }

    public Reserva buscarPorCodigo(String codigo) {

        for (Reserva r : reservas) {
            if (r.getCodigoReserva().equalsIgnoreCase(codigo)) return r;
        }

        return null;
    }

    private int contarReservasActivas(Vehiculo vehiculo) {

        int contador = 0;

        for (Reserva r : reservas) {
            if (r.estaActiva() &&
                    r.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca())) {
                contador++;
            }
        }

        return contador;
    }

    private boolean tieneReservaActiva(String cedula, String placa, LocalDate fechaViaje) {

        for (Reserva r : reservas) {
            if (r.estaActiva() &&
                    r.getPasajero().getCedula().equalsIgnoreCase(cedula) &&
                    r.getVehiculo().getPlaca().equalsIgnoreCase(placa) &&
                    r.getFechaViaje().equals(fechaViaje)) {
                return true;
            }
        }

        return false;
    }

    private String generarCodigo() {
        return String.format("RES-%03d", contadorCodigo++);
    }
}