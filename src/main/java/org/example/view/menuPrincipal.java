package org.example.view;

import org.example.modelo.*;
import org.example.servicio.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class menuPrincipal {

    private RutaServicio     rutaServicio;
    private VehiculoServicio vehiculoServicio;
    private PersonaService   personaService;
    private TicketService    ticketService;
    private ReservaServicio  reservaServicio;

    public menuPrincipal() {
        this.rutaServicio     = new RutaServicio();
        this.vehiculoServicio = new VehiculoServicio(rutaServicio);
        this.personaService   = new PersonaService();
        this.ticketService    = new TicketService(vehiculoServicio, personaService);
        this.reservaServicio  = new ReservaServicio(vehiculoServicio, personaService, ticketService);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║     SISTEMA DE TRANSPORTE        ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Gestión de Rutas             ║");
            System.out.println("║  2. Gestión de Vehículos         ║");
            System.out.println("║  3. Gestión de Personas          ║");
            System.out.println("║  4. Gestión de Tickets           ║");
            System.out.println("║  5. Reportes                     ║");
            System.out.println("║  6. Gestión de Reservas          ║");
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
        Scanner scanner = new Scanner(System.in);
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
                    System.out.print("Código de ruta (ej. R01): "); String codigo  = scanner.nextLine();
                    System.out.print("Ciudad origen: ");             String origen  = scanner.nextLine();
                    System.out.print("Ciudad destino: ");            String destino = scanner.nextLine();
                    System.out.print("Distancia (km): ");            double km      = scanner.nextDouble();
                    System.out.print("Tiempo estimado (minutos): "); int minutos    = scanner.nextInt(); scanner.nextLine();
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
        Scanner scanner = new Scanner(System.in);
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
                    System.out.print("Ingrese tipo de vehículo: "); String tipo  = scanner.nextLine();
                    System.out.print("Ingrese la placa: ");         String placa = scanner.nextLine();
                    if (rutaServicio.listarRutas().isEmpty()) {
                        System.out.println("No hay rutas registradas. Registre una ruta primero.");
                        break;
                    }
                    System.out.println("Rutas disponibles:");
                    for (Ruta ruta : rutaServicio.listarRutas()) {
                        System.out.println("  " + ruta.imprimirDetalle());
                    }
                    System.out.print("Ingrese el código de ruta: ");
                    Ruta rutaSeleccionada = rutaServicio.buscarPorCodigo(scanner.nextLine());
                    if (rutaSeleccionada == null) {
                        System.out.println("Código de ruta no encontrado.");
                        break;
                    }
                    Vehiculo v;
                    switch (tipo) {
                        case "Bus":      v = new Bus(placa, rutaSeleccionada);      break;
                        case "Buseta":   v = new Buseta(placa, rutaSeleccionada);   break;
                        case "MicroBus": v = new MicroBus(placa, rutaSeleccionada); break;
                        default: System.out.println("Tipo de vehículo no válido."); v = null; break;
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
                    Vehiculo encontrado = vehiculoServicio.buscarPorPlaca(scanner.nextLine());
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
        Scanner scanner = new Scanner(System.in);
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
                    System.out.print("Nombre: ");             String nombreC = scanner.nextLine();
                    System.out.print("Cédula: ");             String cedulaC = scanner.nextLine();
                    System.out.print("Categoría licencia: "); String cat     = scanner.nextLine();
                    System.out.print("Número licencia: ");    String numLic  = scanner.nextLine();
                    if (personaService.registrarConductor(new Conductor(nombreC, cedulaC, cat, numLic))) {
                        System.out.println("Conductor registrado.");
                    } else {
                        System.out.println("Error: licencia inválida.");
                    }
                    break;
                case 2:
                    System.out.print("Nombre: "); String nombreP = scanner.nextLine();
                    System.out.print("Cédula: "); String cedulaP = scanner.nextLine();
                    LocalDate fechaNac = null;
                    while (fechaNac == null) {
                        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                        try { fechaNac = LocalDate.parse(scanner.nextLine()); }
                        catch (DateTimeParseException e) { System.out.println("Formato incorrecto. Use YYYY-MM-DD."); }
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
                        for (Conductor c : personaService.listarConductores()) {
                            System.out.println(c.imprimirDetalle());
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
        Scanner scanner = new Scanner(System.in);
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
                    System.out.print("Cédula del pasajero: "); String cedulaT  = scanner.nextLine();
                    System.out.print("Placa del vehículo: ");  String placaT   = scanner.nextLine();
                    System.out.print("Origen: ");              String origenT  = scanner.nextLine();
                    System.out.print("Destino: ");             String destinoT = scanner.nextLine();
                    Ticket ticket = ticketService.venderTicket(cedulaT, placaT, origenT, destinoT);
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
        Scanner scanner = new Scanner(System.in);
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
                    if (vMax != null) {
                        System.out.println(vMax.imprimirDetalle());
                    } else {
                        System.out.println("No hay tickets registrados aún.");
                    }
                    break;
                case 2: System.out.printf("Total recaudado: $%.2f%n", ticketService.totalRecaudado()); break;
                case 3: ticketService.pasajerosPorTipo(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // ── NUEVO ─────────────────────────────────────────────────────
    public void menuReservas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║       GESTIÓN DE RESERVAS        ║");
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
                case 1:
                    System.out.print("Cédula del pasajero: "); String cedula = scanner.nextLine();
                    System.out.print("Placa del vehículo: ");  String placa  = scanner.nextLine();
                    LocalDate fechaViaje = null;
                    while (fechaViaje == null) {
                        System.out.print("Fecha de viaje (YYYY-MM-DD): ");
                        try { fechaViaje = LocalDate.parse(scanner.nextLine()); }
                        catch (DateTimeParseException e) { System.out.println("Formato incorrecto. Use YYYY-MM-DD."); }
                    }
                    Reserva nueva = reservaServicio.crearReserva(cedula, placa, fechaViaje);
                    if (nueva != null) {
                        System.out.println("Reserva creada con éxito.");
                        System.out.println(nueva.imprimirDetalle());
                    } else {
                        System.out.println("No se pudo crear la reserva. Verifique datos, cupos o reserva duplicada.");
                    }
                    break;
                case 2:
                    System.out.print("Código de la reserva: ");
                    if (reservaServicio.cancelarReserva(scanner.nextLine())) {
                        System.out.println("Reserva cancelada. Cupo liberado.");
                    } else {
                        System.out.println("No se encontró una reserva activa con ese código.");
                    }
                    break;
                case 3:
                    List<Reserva> activas = reservaServicio.listarReservasActivas();
                    if (activas.isEmpty()) {
                        System.out.println("No hay reservas activas.");
                    } else {
                        for (Reserva res : activas) {
                            System.out.println(res.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Cédula del pasajero: ");
                    List<Reserva> historial = reservaServicio.historialPasajero(scanner.nextLine());
                    if (historial.isEmpty()) {
                        System.out.println("El pasajero no tiene reservas registradas.");
                    } else {
                        for (Reserva res : historial) {
                            System.out.println(res.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;
                case 5:
                    System.out.print("Código de la reserva: ");
                    Ticket t = reservaServicio.convertirATicket(scanner.nextLine());
                    if (t != null) {
                        System.out.println("Reserva convertida en ticket con éxito.");
                        System.out.println(t.imprimirDetalle());
                    } else {
                        System.out.println("No se pudo convertir. Verifique que la reserva exista y esté activa.");
                    }
                    break;
                case 6:
                    int canceladas = reservaServicio.verificarVencidas();
                    if (canceladas == 0) {
                        System.out.println("No se encontraron reservas vencidas.");
                    } else {
                        System.out.println("Reservas canceladas por vencimiento: " + canceladas);
                    }
                    break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}