package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class MesaDAO extends DAO<MesaDAO> {
    private int idMesa;
    private int numMesa;
    private int capacidad;
    private String ubicacion;

    public int getIdMesa() { return idMesa; }
    public void setIdMesa(int idMesa) { this.idMesa = idMesa; }

    public int getNumMesa() { return numMesa; }
    public void setNumMesa(int numMesa) { this.numMesa = numMesa; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public void INSERT() {
        String query = "INSERT INTO mesa(numMesa, capacidad, ubicacion) VALUES(" + numMesa + ", " + capacidad + ", '" + ubicacion + "');";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE() {
        String query = "UPDATE mesa SET numMesa = " + numMesa + ", capacidad = " + capacidad + ", ubicacion = '" + ubicacion +
                "' WHERE idMesa = " + idMesa + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DELETE() {
        String query = "DELETE FROM mesa WHERE idMesa = " + idMesa + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<MesaDAO> SELECT() {
        ObservableList<MesaDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM mesa;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MesaDAO m = new MesaDAO();
                m.setIdMesa(rs.getInt("idMesa"));
                m.setNumMesa(rs.getInt("numMesa"));
                m.setCapacidad(rs.getInt("capacidad"));
                m.setUbicacion(rs.getString("ubicacion"));
                lista.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Class<MesaDAO> getModelClass() {
        return MesaDAO.class;
    }
}
