package org.example.view;

import org.example.modelo.*;
import org.example.servicio.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class menuPrincipal {

    private RutaServicio    rutaServicio;
    private VehiculoServicio vehiculoServicio;
    private PersonaService  personaService;
    private TicketService   ticketService;
    private ReservaServicio reservaServicio;
    private Scanner         scanner = new Scanner(System.in);

    public menuPrincipal() {
        this.rutaServicio    = new RutaServicio();
        this.vehiculoServicio = new VehiculoServicio(rutaServicio);
        this.personaService  = new PersonaService();
        this.ticketService   = new TicketService(vehiculoServicio, personaService);
        this.reservaServicio = new ReservaServicio(vehiculoServicio, personaService, ticketService);
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║     SISTEMA DE TRANSPORTE        ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Gestión de Rutas             ║");  // NUEVO
            System.out.println("║  2. Gestión de Vehículos         ║");
            System.out.println("║  3. Gestión de Personas          ║");
            System.out.println("║  4. Gestión de Tickets           ║");
            System.out.println("║  5. Reportes                     ║");
            System.out.println("║  6. Reservas                     ║");
            System.out.println("║  0. Salir                        ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: menuRutas();     break;
                case 2: menuVehiculos(); break;
                case 3: menuPersonas();  break;
                case 4: menuTickets();   break;
                case 5: menuReportes();  break;
                case 6: menuReservas();  break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    public void menuRutas() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║         GESTIÓN DE RUTAS         ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Registrar ruta               ║");
            System.out.println("║  2. Listar rutas                 ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Código de ruta (ej. R01): ");
                    String codigo = scanner.nextLine();
                    System.out.print("Ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ciudad destino: ");
                    String destino = scanner.nextLine();
                    System.out.print("Distancia (km): ");
                    double km = scanner.nextDouble();
                    System.out.print("Tiempo estimado (minutos): ");
                    int minutos = scanner.nextInt(); scanner.nextLine();

                    Ruta r = new Ruta(codigo, origen, destino, km, minutos);
                    if (rutaServicio.registrarRuta(r)) {
                        System.out.println("Ruta registrada con éxito.");
                    } else {
                        System.out.println("Error: ya existe una ruta con ese código.");
                    }
                    break;

                case 2:
                    if (rutaServicio.listarRutas().isEmpty()) {
                        System.out.println("No hay rutas registradas.");
                    } else {
                        for (Ruta ruta : rutaServicio.listarRutas()) {
                            System.out.println(ruta.imprimirDetalle());
                        }
                    }
                    break;

                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    public void menuVehiculos() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║       GESTIÓN DE VEHÍCULOS       ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Registrar vehículo           ║");
            System.out.println("║  2. Listar vehículos             ║");
            System.out.println("║  3. Buscar vehículo por placa    ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Tipos disponibles: Bus, Buseta, MicroBus");
                    System.out.print("Ingrese tipo de vehículo: ");
                    String tipo = scanner.nextLine();

                    System.out.print("Ingrese la placa: ");
                    String placa = scanner.nextLine();

                    if (rutaServicio.listarRutas().isEmpty()) {
                        System.out.println("No hay rutas registradas. Registre una ruta primero.");
                        break;
                    }
                    System.out.println("Rutas disponibles:");
                    for (Ruta ruta : rutaServicio.listarRutas()) {
                        System.out.println("  " + ruta.imprimirDetalle());
                    }
                    System.out.print("Ingrese el código de ruta: ");
                    String codigoRuta = scanner.nextLine();

                    Ruta rutaSeleccionada = rutaServicio.buscarPorCodigo(codigoRuta);
                    if (rutaSeleccionada == null) {
                        System.out.println("Código de ruta no encontrado.");
                        break;
                    }

                    Vehiculo v;
                    switch (tipo) {
                        case "Bus":      v = new Bus(placa, rutaSeleccionada);      break;
                        case "Buseta":   v = new Buseta(placa, rutaSeleccionada);   break;
                        case "MicroBus": v = new MicroBus(placa, rutaSeleccionada); break;
                        default:
                            System.out.println("Tipo de vehículo no válido.");
                            v = null; break;
                    }
                    if (v != null) {
                        if (vehiculoServicio.registrarVehiculo(v)) {
                            System.out.println("Vehículo registrado con éxito.");
                        } else {
                            System.out.println("Error: ya existe un vehículo con esa placa.");
                        }
                    }
                    break;

                case 2:
                    if (vehiculoServicio.listarVehiculos().isEmpty()) {
                        System.out.println("No hay vehículos registrados.");
                    } else {
                        vehiculoServicio.mostrarVehiculo();
                    }
                    break;

                case 3:
                    System.out.print("Ingrese la placa: ");
                    String placaBuscar = scanner.nextLine();
                    Vehiculo encontrado = vehiculoServicio.buscarPorPlaca(placaBuscar);
                    if (encontrado != null) {
                        System.out.println(encontrado.imprimirDetalle());
                    } else {
                        System.out.println("No se encontró ningún vehículo con esa placa.");
                    }
                    break;

                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    public void menuPersonas() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║        GESTIÓN DE PERSONAS       ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Registrar conductor          ║");
            System.out.println("║  2. Registrar pasajero           ║");
            System.out.println("║  3. Listar conductores           ║");
            System.out.println("║  4. Listar pasajeros             ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");           String nombreC = scanner.nextLine();
                    System.out.print("Cédula: ");           String cedulaC = scanner.nextLine();
                    System.out.print("Categoría licencia: "); String cat = scanner.nextLine();
                    System.out.print("Número licencia: ");   String numLic = scanner.nextLine();
                    Conductor c = new Conductor(nombreC, cedulaC, cat, numLic);
                    if (personaService.registrarConductor(c)) {
                        System.out.println("Conductor registrado.");
                    } else {
                        System.out.println("Error: licencia inválida.");
                    }
                    break;

                case 2:
                    System.out.print("Nombre: ");  String nombreP = scanner.nextLine();
                    System.out.print("Cédula: ");  String cedulaP = scanner.nextLine();

                    LocalDate fechaNac = null;
                    while (fechaNac == null) {
                        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                        String fechaStr = scanner.nextLine();
                        try {
                            fechaNac = LocalDate.parse(fechaStr);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato incorrecto. Use YYYY-MM-DD.");
                        }
                    }

                    System.out.print("¿Es estudiante? (s/n): ");
                    boolean esEstudiante = scanner.nextLine().trim().equalsIgnoreCase("s");

                    personaService.registrarPasajero(nombreP, cedulaP, fechaNac, esEstudiante);
                    Pasajero creado = personaService.buscarPasajero(cedulaP);
                    System.out.println("Pasajero registrado como: " + (creado != null ? creado.getTipo() : "?"));
                    break;

                case 3:
                    if (personaService.listarConductores().isEmpty()) {
                        System.out.println("No hay conductores registrados.");
                    } else {
                        for (Conductor cond : personaService.listarConductores()) {
                            System.out.println(cond.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;

                case 4:
                    if (personaService.listarPasajeros().isEmpty()) {
                        System.out.println("No hay pasajeros registrados.");
                    } else {
                        for (Pasajero p : personaService.listarPasajeros()) {
                            System.out.println(p.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;

                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    public void menuTickets() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║         GESTIÓN DE TICKETS       ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Vender ticket                ║");
            System.out.println("║  2. Listar tickets               ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Cédula del pasajero: ");  String cedulaT = scanner.nextLine();
                    System.out.print("Placa del vehículo: ");   String placaT  = scanner.nextLine();
                    System.out.print("Origen: ");               String origen  = scanner.nextLine();
                    System.out.print("Destino: ");              String destino = scanner.nextLine();
                    Ticket ticket = ticketService.venderTicket(cedulaT, placaT, origen, destino);
                    if (ticket != null) {
                        System.out.println("Ticket vendido con éxito.");
                        System.out.println(ticket.imprimirDetalle());
                    } else {
                        System.out.println("Error: pasajero o vehículo no encontrado, o sin cupos.");
                    }
                    break;

                case 2:
                    if (ticketService.listarTickets().isEmpty()) {
                        System.out.println("No hay tickets registrados.");
                    } else {
                        for (Ticket t : ticketService.listarTickets()) {
                            System.out.println(t.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;

                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    public void menuReportes() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║            REPORTES              ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Vehículo con más tickets     ║");
            System.out.println("║  2. Total recaudado              ║");
            System.out.println("║  3. Pasajeros por tipo           ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1:
                    Vehiculo vMax = ticketService.vehiculoConMasTickets();
                    if (vMax != null) System.out.println(vMax.imprimirDetalle());
                    else System.out.println("No hay tickets registrados aún.");
                    break;
                case 2:
                    System.out.printf("Total recaudado: $%.2f%n", ticketService.totalRecaudado());
                    break;
                case 3:
                    ticketService.pasajerosPorTipo();
                    break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    public void menuReservas() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║         GESTIÓN DE RESERVAS      ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Crear reserva                ║");
            System.out.println("║  2. Cancelar reserva             ║");
            System.out.println("║  3. Listar reservas activas      ║");
            System.out.println("║  4. Historial de un pasajero     ║");
            System.out.println("║  5. Convertir reserva en ticket  ║");
            System.out.println("║  6. Verificar reservas vencidas  ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {

                case 1: // ── Crear reserva ──────────────────────────────────
                    System.out.print("Cédula del pasajero: ");
                    String cedula = scanner.nextLine();
                    System.out.print("Placa del vehículo: ");
                    String placa = scanner.nextLine();

                    LocalDate fechaViaje = null;
                    while (fechaViaje == null) {
                        System.out.print("Fecha de viaje (YYYY-MM-DD): ");
                        try {
                            fechaViaje = LocalDate.parse(scanner.nextLine());
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato incorrecto. Use YYYY-MM-DD.");
                        }
                    }

                    Reserva nueva = reservaServicio.crearReserva(cedula, placa, fechaViaje);
                    if (nueva != null) {
                        System.out.println("Reserva creada exitosamente.");
                        System.out.println(nueva.imprimirDetalle());
                    }
                    break;

                case 2: // ── Cancelar reserva ───────────────────────────────
                    System.out.print("Código de la reserva: ");
                    String codigoCancelar = scanner.nextLine();
                    if (reservaServicio.cancelarReserva(codigoCancelar)) {
                        System.out.println("Reserva cancelada correctamente.");
                    }
                    break;

                case 3: // ── Listar activas ──────────────────────────────────
                    List<Reserva> activas = reservaServicio.listarReservasActivas();
                    if (activas.isEmpty()) {
                        System.out.println("No hay reservas activas.");
                    } else {
                        for (Reserva r : activas) {
                            System.out.println(r.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;

                case 4: // ── Historial pasajero ──────────────────────────────
                    System.out.print("Cédula del pasajero: ");
                    String cedulaHist = scanner.nextLine();
                    List<Reserva> historial = reservaServicio.historialPasajero(cedulaHist);
                    if (historial.isEmpty()) {
                        System.out.println("El pasajero no tiene reservas registradas.");
                    } else {
                        for (Reserva r : historial) {
                            System.out.println(r.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;

                case 5: // ── Convertir en ticket ─────────────────────────────
                    System.out.print("Código de la reserva: ");
                    String codigoConv = scanner.nextLine();
                    Ticket ticket = reservaServicio.convertirATicket(codigoConv);
                    if (ticket != null) {
                        System.out.println("Ticket generado exitosamente.");
                        System.out.println(ticket.imprimirDetalle());
                    }
                    break;

                case 6: // ── Verificar vencidas ──────────────────────────────
                    reservaServicio.verificarVencidas();
                    break;

                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}