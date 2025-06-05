package org.example.proyectotapbd.utils.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrdenDAO extends DAO<OrdenDAO> {
    private int idOrden;
    private int idMesa;
    private String fecha;
    private String estado;

    public int getIdOrden() { return idOrden; }
    public void setIdOrden(int idOrden) { this.idOrden = idOrden; }

    public int getIdMesa() { return idMesa; }
    public void setIdMesa(int idMesa) { this.idMesa = idMesa; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public void INSERT() {
        String query = "INSERT INTO orden(idMesa, fecha, estado) VALUES(" + idMesa + ", '" + fecha + "', '" + estado + "');";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE() {
        String query = "UPDATE orden SET idMesa = " + idMesa + ", fecha = '" + fecha + "', estado = '" + estado +
                "' WHERE idOrden = " + idOrden + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DELETE() {
        String query = "DELETE FROM orden WHERE idOrden = " + idOrden + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<OrdenDAO> SELECT() {
        ObservableList<OrdenDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM orden;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                OrdenDAO o = new OrdenDAO();
                o.setIdOrden(rs.getInt("idOrden"));
                o.setIdMesa(rs.getInt("idMesa"));
                o.setFecha(rs.getString("fecha"));
                o.setEstado(rs.getString("estado"));
                lista.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String getSufijo() {
        return "Orden Mesa";
    }

    @Override
    public Class<OrdenDAO> getModelClass() {
        return OrdenDAO.class;
    }
}
