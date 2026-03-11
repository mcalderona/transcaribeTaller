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
                case 1: menuVehiculos(); break; // agregar estos metodos
                case 2: menuPersonas(); break;
                case 3: menuTickets(); break;
                case 4: menuReportes(); break;
                case 0: System.out.println(" Saliendo del sistema");
                break;
                default: System.out.println("Opcion no valida");
            }

        } while (opcion != 0);
    }

    



}
