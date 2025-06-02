package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReservacionDAO extends DAO<ReservacionDAO> {
    private int idRes;
    private int idMesa;
    private int idCte;
    private String fechaRes;
    private String horaRes;

    public int getIdRes() { return idRes; }
    public void setIdRes(int idRes) { this.idRes = idRes; }

    public int getIdMesa() { return idMesa; }
    public void setIdMesa(int idMesa) { this.idMesa = idMesa; }

    public int getIdCte() { return idCte; }
    public void setIdCte(int idCte) { this.idCte = idCte; }

    public String getFechaRes() { return fechaRes; }
    public void setFechaRes(String fechaRes) { this.fechaRes = fechaRes; }

    public String getHoraRes() { return horaRes; }
    public void setHoraRes(String horaRes) { this.horaRes = horaRes; }

    public void INSERT() {
        String query = "INSERT INTO reservacion(idMesa, idCte, fechaRes, horaRes) VALUES(" +
                idMesa + ", " + idCte + ", '" + fechaRes + "', '" + horaRes + "');";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE() {
        String query = "UPDATE reservacion SET idMesa = " + idMesa + ", idCte = " + idCte +
                ", fechaRes = '" + fechaRes + "', horaRes = '" + horaRes +
                "' WHERE idRes = " + idRes + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DELETE() {
        String query = "DELETE FROM reservacion WHERE idRes = " + idRes + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ReservacionDAO> SELECT() {
        ObservableList<ReservacionDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM reservacion;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ReservacionDAO r = new ReservacionDAO();
                r.setIdRes(rs.getInt("idRes"));
                r.setIdMesa(rs.getInt("idMesa"));
                r.setIdCte(rs.getInt("idCte"));
                r.setFechaRes(rs.getString("fechaRes"));
                r.setHoraRes(rs.getString("horaRes"));
                lista.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
