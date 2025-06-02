package org.example.proyectotapbd.BD;

import org.example.proyectotapbd.componentes.VentanaError;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManejadorErrores {

    public static void manejarExcepcion(SQLException e) {
        System.err.println("Error SQL: " + e.getMessage());
        String constraint = extraerNombreConstraint(e.getMessage());
        String descripcion = buscarDescripcionConstraint(constraint);
        new VentanaError(descripcion != null ? descripcion : "Error en la base de datos: " + e.getMessage());
    }

    public static String extraerNombreConstraint(String mensaje) {
        // Revisa si hay una palabra clave 'constraint' en el mensaje (insensible a mayúsculas)
        if (mensaje == null) return null;
        mensaje = mensaje.toLowerCase();
        if (mensaje.contains("constraint")) {
            int inicio = mensaje.indexOf("`");
            int fin = mensaje.indexOf("`", inicio + 1);
            if (inicio != -1 && fin != -1) {
                return mensaje.substring(inicio + 1, fin);
            }
        }
        return null;
    }

    public static String buscarDescripcionConstraint(String nombreConstraint) {
        if (nombreConstraint == null) return null;

        String query = "SELECT descripcion FROM ErroresConstraint WHERE nombreConstraint = ?";
        try (PreparedStatement stmt = org.example.proyectotapbd.modelos.Conexion.connection.prepareStatement(query)) {
            stmt.setString(1, nombreConstraint);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("descripcion");
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar ErroresConstraint: " + ex.getMessage());
        }
        return null;
    }
}
