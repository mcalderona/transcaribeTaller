package org.example.view;

import java.util.Scanner;

public class menuPrincipal {

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
                case 0: System.out.println(" Saliendo del sistema");
                break;
                default: System.out.println("Opcion no valida");
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

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    registrarVehiculo();
                    break;

                case 2:
                    System.out.println("Listar vehículos (pendiente conectar con service)");
                    break;

                case 3:
                    System.out.print("Ingrese la placa: ");
                    String placa = scanner.nextLine();
                    System.out.println("Buscar vehículo (pendiente conectar con service)");
                    break;

                case 0:
                    break;

                default:
                    System.out.println(" Opción no válida.");
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

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    registrarConductor();
                    break;

                case 2:
                    registrarPasajero();
                    break;

                case 3:
                    System.out.println("Listar conductores (pendiente service)");
                    break;

                case 4:
                    System.out.println("Listar pasajeros (pendiente service)");
                    break;

                case 0:
                    break;

                default:
                    System.out.println(" Opción no válida.");
            }

        } while (opcion != 0);
    }
    





}
