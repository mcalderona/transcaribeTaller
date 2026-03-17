package org.example.servicio;

import org.example.dao.RutaDAO;
import org.example.modelo.Ruta;

import java.util.List;

public class RutaServicio {

    private List<Ruta> rutas;
    private RutaDAO dao;

    public RutaServicio() {
        dao = new RutaDAO();
        rutas = dao.cargarTodos();
    }

    public boolean registrarRuta(Ruta r) {
        // Evitar código duplicado
        for (Ruta existente : rutas) {
            if (existente.getCodigoRuta().equalsIgnoreCase(r.getCodigoRuta())) {
                return false;
            }
        }
        rutas.add(r);
        dao.guardar(r);
        return true;
    }

    public Ruta buscarPorCodigo(String codigo) {
        for (Ruta r : rutas) {
            if (r.getCodigoRuta().equalsIgnoreCase(codigo)) return r;
        }
        return null;
    }

    public List<Ruta> listarRutas() {
        return rutas;
    }
}
