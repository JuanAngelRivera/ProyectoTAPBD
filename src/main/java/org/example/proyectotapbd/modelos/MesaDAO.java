package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class MesaDAO extends DAO<MesaDAO> {
    private int idMesa;
    private int capacidad;
    private boolean ocupada;

    public int getIdMesa() { return idMesa; }
    public void setIdMesa(int idMesa) { this.idMesa = idMesa; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public boolean getOcupada() { return ocupada; }
    public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }

    public void INSERT() {
        String query = "INSERT INTO mesa(idMesa, capacidad, ocupada) VALUES(" + idMesa + ", " + capacidad + ", '" + (ocupada ? 1 : 0) + "');";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE() {
        String query = "UPDATE mesa SET capacidad = " + capacidad + ", ocupada = '" + (ocupada ? 1 : 0) +
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
                m.setCapacidad(rs.getInt("capacidad"));
                m.setOcupada(rs.getBoolean("ocupada"));
                lista.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String getSufijo() {
        return "Mesa";
    }

    @Override
    public Class<MesaDAO> getModelClass() {
        return MesaDAO.class;
    }
}
