package org.example.dao;

import org.example.modelo.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private static final String RESERVAS_FILE = "reservas.txt";

    public void guardar(Reserva r) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESERVAS_FILE, true))) {

            bw.write(
                    r.getCodigoReserva() + ";" +
                            r.getPasajero().getCedula() + ";" +
                            r.getVehiculo().getPlaca() + ";" +
                            r.getFechaCreacion().toString() + ";" +
                            r.getFechaViaje().toString() + ";" +
                            r.getEstado()
            );

            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error guardando reserva");
        }
    }
}
