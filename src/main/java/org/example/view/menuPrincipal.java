package org.example.view;

import org.example.modelo.*;
import org.example.servicio.PersonaService;
import org.example.servicio.TicketService;
import org.example.servicio.VehiculoServicio;

import java.util.List;
import java.util.Scanner;

public class menuPrincipal {

    private VehiculoServicio vehiculoService;
    private PersonaService personaService;
    private TicketService ticketService;

    public menuPrincipal() {
        this.vehiculoService = new VehiculoServicio();
        this.personaService = new PersonaService();
        this.ticketService = new TicketService(vehiculoService, personaService);
    }

    public void menu() {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║     SISTEMA DE TRANSPORTE        ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Gestión de Vehículos         ║");
            System.out.println("║  2. Gestión de Personas          ║");
            System.out.println("║  3. Gestión de Tickets           ║");
            System.out.println("║  4. Reportes                     ║");
            System.out.println("║  0. Salir                        ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: menuVehiculos(); break;
                case 2: menuPersonas(); break;
                case 3: menuTickets(); break;
                case 4: menuReportes(); break;
                case 0: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ─────────────────────────────────────────────
    //  MENÚ VEHÍCULOS
    // ─────────────────────────────────────────────
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

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║      REGISTRAR VEHÍCULO      ║");
                    System.out.println("╚══════════════════════════════╝");

                    System.out.println("Tipos disponibles: Bus, Buseta, MicroBus");
                    System.out.print("Ingrese tipo de vehículo: ");
                    String tipo = scanner.nextLine();

                    System.out.print("Ingrese la placa: ");
                    String placa = scanner.nextLine();

                    System.out.print("Ingrese la ruta: ");
                    String ruta = scanner.nextLine();

                    Vehiculo v;
                    switch (tipo) {
                        case "Bus":
                            v = new Bus(placa, ruta);
                            break;
                        case "Buseta":
                            v = new Buseta(placa, ruta);
                            break;
                        case "MicroBus":
                            v = new MicroBus(placa, ruta);
                            break;
                        default:
                            System.out.println("Tipo de vehículo no válido.");
                            v = null;
                            break;
                    }

                    if (v != null) {
                        boolean registrado = vehiculoService.registrarVehiculo(v);
                        if (registrado) {
                            System.out.println("Vehículo registrado con éxito.");
                        } else {
                            System.out.println("Error: ya existe un vehículo con esa placa.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║        LISTA DE VEHÍCULOS    ║");
                    System.out.println("╚══════════════════════════════╝");

                    if (vehiculoService.listarVehiculos().isEmpty()) {
                        System.out.println("No hay vehículos registrados.");
                    } else {
                        vehiculoService.mostarVehiculo();
                    }
                    break;

                case 3:

                    System.out.print("Ingrese la placa: ");
                    String placaBuscar = scanner.nextLine();

                    Vehiculo encontrado = vehiculoService.buscarPorPlaca(placaBuscar);
                    if (encontrado != null) {
                        System.out.println(encontrado.imprimirDetalle());
                    } else {
                        System.out.println("No se encontró ningún vehículo con esa placa.");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ─────────────────────────────────────────────
    //  MENÚ PERSONAS
    // ─────────────────────────────────────────────
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

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║     REGISTRAR CONDUCTOR      ║");
                    System.out.println("╚══════════════════════════════╝");

                    System.out.print("Ingrese el nombre: ");
                    String nombreC = scanner.nextLine();

                    System.out.print("Ingrese la cédula: ");
                    String cedulaC = scanner.nextLine();

                    System.out.print("Ingrese categoría de licencia: ");
                    String categoriaLicencia = scanner.nextLine();

                    System.out.print("Ingrese número de licencia: ");
                    String numeroLicencia = scanner.nextLine();

                    Conductor conductor = new Conductor(nombreC, cedulaC, categoriaLicencia, numeroLicencia);

                    boolean conductorRegistrado = personaService.registrarConductor(conductor);
                    if (conductorRegistrado) {
                        System.out.println("Conductor registrado con éxito.");
                    } else {
                        System.out.println("Error: número de licencia inválido.");
                    }
                    break;

                case 2:
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║     REGISTRAR PASAJERO       ║");
                    System.out.println("╚══════════════════════════════╝");

                    System.out.print("Ingrese el nombre: ");
                    String nombreP = scanner.nextLine();

                    System.out.print("Ingrese la cédula: ");
                    String cedulaP = scanner.nextLine();

                    System.out.println("Tipos: Regular, Estudiante, Adulto Mayor");
                    System.out.print("Ingrese categoría: ");
                    String categoria = scanner.nextLine();

                    Pasajero pasajero;
                    switch (categoria) {
                        case "Estudiante":
                            pasajero = new PasajeroEstudiante(nombreP, cedulaP, categoria);
                            break;
                        case "Adulto Mayor":
                            pasajero = new PasajeroAdultoMayor(nombreP, cedulaP, categoria);
                            break;
                        default:
                            pasajero = new PasajeroRegular(nombreP, cedulaP, "Regular");
                            break;
                    }

                    personaService.registrarPasajero(pasajero);
                    System.out.println("Pasajero registrado con éxito.");
                    break;

                case 3:
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║      LISTA DE CONDUCTORES    ║");
                    System.out.println("╚══════════════════════════════╝");

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
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║      LISTA DE PASAJEROS      ║");
                    System.out.println("╚══════════════════════════════╝");

                    if (personaService.listarPasajeros().isEmpty()) {
                        System.out.println("No hay pasajeros registrados.");
                    } else {
                        for (Pasajero p : personaService.listarPasajeros()) {
                            System.out.println(p.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ─────────────────────────────────────────────
    //  MENÚ TICKETS
    // ─────────────────────────────────────────────
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

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║         VENDER TICKET        ║");
                    System.out.println("╚══════════════════════════════╝");

                    System.out.print("Ingrese la cédula del pasajero: ");
                    String cedulaT = scanner.nextLine();

                    System.out.print("Ingrese la placa del vehículo: ");
                    String placaT = scanner.nextLine();

                    System.out.print("Ingrese el origen: ");
                    String origen = scanner.nextLine();

                    System.out.print("Ingrese el destino: ");
                    String destino = scanner.nextLine();

                    Ticket ticket = ticketService.venderTicket(cedulaT, placaT, origen, destino);

                    if (ticket != null) {
                        System.out.println("Ticket vendido con éxito.");
                        System.out.println(ticket.imprimirDetalle());
                    } else {
                        System.out.println("Error: pasajero o vehículo no encontrado, o sin cupos disponibles.");
                    }
                    break;

                case 2:
                    System.out.println("\n╔══════════════════════════════╗");
                    System.out.println("║        LISTA DE TICKETS      ║");
                    System.out.println("╚══════════════════════════════╝");

                    if (ticketService.listarTickets().isEmpty()) {
                        System.out.println("No hay tickets registrados.");
                    } else {
                        for (Ticket t : ticketService.listarTickets()) {
                            System.out.println(t.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ─────────────────────────────────────────────
    //  MENÚ REPORTES
    // ─────────────────────────────────────────────
    public void menuReportes() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            do {
                System.out.println("\n╔══════════════════════════════════╗");
                System.out.println("║            REPORTES              ║");
                System.out.println("╠══════════════════════════════════╣");
                System.out.println("║  1. Vehículo con más tickets     ║");
                System.out.println("║  2. Total recaudado              ║");
                System.out.println("║  3. Pasajeros por tipo           ║");
                System.out.println("║  4. Tickets por fecha            ║");
                System.out.println("║  5. Tickets por tipo de vehículo ║");
                System.out.println("║  6. Tickets por tipo de pasajero ║");
                System.out.println("║  7. Resumen del día              ║");
                System.out.println("║  8. Volver                       ║");
                System.out.println("╚══════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    Vehiculo vMax = ticketService.vehiculoConMasTickets();
                    if (vMax != null) {
                        System.out.println("\n── Vehículo con más tickets ──");
                        System.out.println (vMax.imprimirDetalle()) ;
                    } else {
                        System.out.println("No hay tickets registrados aún.");
                    }
                    break;

                case 2:
                    double total = ticketService.totalRecaudado();
                    System.out.println("\n── Total recaudado ──");
                    System.out.printf("$%.2f%n", total);
                    break;

                case 3:
                    System.out.println("\n── Pasajeros por tipo ──");
                    ticketService.pasajerosPorTipo();
                    break;

                    case 4:

                    System.out.print("Ingrese la fecha (dia-Mes-año): ");
                    String fecha = scanner.nextLine();
                    System.out.println("\n╔══════════════════════════════════╗");
                    System.out.println("║       TICKETS POR FECHA          ║");
                    System.out.println("╚══════════════════════════════════╝");

                     List<Ticket> porFecha = ticketService.ticketsPorFecha(fecha);
                     if (porFecha.isEmpty()) {
                         System.out.println("No hay tickets para la fecha: " + fecha);
                     } else {
                         for (Ticket t : porFecha) {
                             System.out.println(t.imprimirDetalle());
                             System.out.println("_________________________");
                         }
                         System.out.println("Total tickets: " + porFecha.size());
                     }
                    System.out.println("Pendiente: requiere ticketsPorFecha() en TicketService.");
                    break;

                case 5:
                    System.out.println("Tipos disponibles: Bus, Buseta, MicroBus");
                    System.out.print("Ingrese tipo de vehículo: ");
                    String tipoVehiculo = scanner.nextLine();
                    System.out.println("\n╔══════════════════════════════════╗");
                    System.out.println("║   TICKETS POR TIPO DE VEHÍCULO   ║");
                    System.out.println("╚══════════════════════════════════╝");

                     List<Ticket> porVehiculo = ticketService.ticketsPorTipoVehiculo(tipoVehiculo);
                     if (porVehiculo.isEmpty()) {
                         System.out.println("No hay tickets para el tipo: " + tipoVehiculo);
                     }
                     else {
                         for (Ticket t : porVehiculo) {
                             System.out.println(t.imprimirDetalle());
                             System.out.println("_________________________");
                         }
                         System.out.println("Total tickets: " + porVehiculo.size());
                     }
                    System.out.println("Pendiente: requiere ticketsPorTipoVehiculo() en TicketService.");
                    break;

                case 6:
                    System.out.println("Tipos disponibles: Regular, Estudiante, Adulto Mayor");
                    System.out.print("Ingrese tipo de pasajero: ");
                    String tipoPasajero = scanner.nextLine();
                    System.out.println("\n╔══════════════════════════════════╗");
                    System.out.println("║   TICKETS POR TIPO DE PASAJERO   ║");
                    System.out.println("╚══════════════════════════════════╝");

                    List<Ticket> porPasajero = ticketService.ticketsPorTipoPasajero(tipoPasajero);
                    if (porPasajero.isEmpty()) {
                        System.out.println("No hay tickets para el tipo: " + tipoPasajero);
                    } else {
                        for (Ticket t : porPasajero) {
                            System.out.println(t.imprimirDetalle());
                            System.out.println("_________________________");
                        }
                        System.out.println("Total tickets: " + porPasajero.size());
                    }
                    System.out.println("Pendiente: requiere ticketsPorTipoPasajero() en TicketService.");
                    break;

                case 7:
                    String hoy = LocalDate.now().toString();
                    System.out.println("\n╔══════════════════════════════════╗");
                    System.out.println("║         RESUMEN DEL DÍA          ║");
                    System.out.println("╠══════════════════════════════════╣");
                    System.out.println("║  Fecha: " + hoy + "             ║");
                    System.out.println("╚══════════════════════════════════╝");

                     List<Ticket> ticketsHoy = ticketService.ticketsPorFecha(hoy);
                     double recaudadoHoy = 0;
                     for (Ticket t : ticketsHoy) { recaudadoHoy += t.getValorFinal(); }
                     System.out.println("Tickets vendidos hoy : " + ticketsHoy.size());
                     System.out.printf("Total recaudado hoy  : $%.2f%n", recaudadoHoy);
                    System.out.println("Pendiente: requiere ticketsPorFecha() en TicketService.");
                    break;

                case 7:
                    String day = LocalDate.now().toString();
                    System.out.println("\n╔══════════════════════════════════╗");
                    System.out.println("║         RESUMEN DEL DÍA          ║");
                    System.out.println("╠══════════════════════════════════╣");
                    System.out.println("║  Fecha: " + day + "             ║");
                    System.out.println("╚══════════════════════════════════╝");

                    List<Ticket> ticketsHoy = ticketService.ticketsPorFecha(hoy);
                    double recaudadoHoy = 0;
                    for (Ticket t : ticketsHoy) {
                        recaudadoHoy += t.getValorFinal(); }
                    System.out.println("Tickets vendidos hoy : " + ticketsHoy.size());
                    System.out.printf("Total recaudado hoy  : $%.2f%n", recaudadoHoy);
                    System.out.println("Pendiente: requiere ticketsPorFecha() en TicketService.");
                    break;

                case 8:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            }

        } while (opcion != 0);
    }
}

