package org.example.proyectotapbd.utils.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class SeHaceDAO extends DAO<SeHaceDAO> {

    private int idMesa;
    private int idReser;

    // Getters y Setters
    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdReser() {
        return idReser;
    }

    public void setIdReser(int idReser) {
        this.idReser = idReser;
    }

    @Override
    public void INSERT() {
        String query = "INSERT INTO sehace(idMesa, idReser) VALUES(" +
                idMesa + ", " + idReser + ");";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UPDATE() {
        // Generalmente las relaciones n-n no se actualizan, solo se eliminan o se insertan.
        // Puedes dejar este método vacío o usarlo si agregas campos adicionales.
    }

    @Override
    public void DELETE() {
        String query = "DELETE FROM sehace WHERE idMesa = " + idMesa +
                " AND idReser = " + idReser + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<SeHaceDAO> SELECT() {
        ObservableList<SeHaceDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM sehace;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                SeHaceDAO obj = new SeHaceDAO();
                obj.setIdMesa(rs.getInt("idMesa"));
                obj.setIdReser(rs.getInt("idReser"));
                lista.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String getSufijo() {
        return "Mesa Reser";
    }

    @Override
    public Class<SeHaceDAO> getModelClass() {
        return SeHaceDAO.class;
    }
}
