package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrdenDAO extends DAO<OrdenDAO> {
    private int idOrd;
    private double total;
    private String fecha;
    private int idEmp;
    private int idCte;
    private int idMesa;


    public int getIdOrd() { return idOrd; }
    public void setIdOrd(int idOrd) { this.idOrd = idOrd; }

    public int getIdMesa() { return idMesa; }
    public void setIdMesa(int idMesa) { this.idMesa = idMesa; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdCte() {
        return idCte;
    }

    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }

    public void INSERT() {
        String query = "INSERT INTO orden(idOrd, total, fecha, idEmp, idCte, idMesa) VALUES(" + idOrd + ", '" + total + "', '" + fecha + "','" + idEmp + "','" + idCte + "','" + idMesa + "')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE() {
        String query = "UPDATE orden SET idOrd = " + idOrd + ", total = '" + total + "', fecha = '" + fecha + "', idEmp = " + idEmp + "', idCte = '" + idCte + "', idMesa = " + idMesa +
                "' WHERE idOrden = " + idOrd + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DELETE() {
        String query = "DELETE FROM orden WHERE idOrden = " + idOrd + ";";
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
                o.setIdOrd(rs.getInt("idOrden"));
                o.setTotal(rs.getDouble("total"));
                o.setFecha(rs.getString("fecha"));
                o.setIdEmp(rs.getInt("idEmp"));
                o.setIdCte(rs.getInt("idCte"));
                o.setIdMesa(rs.getInt("idMesa"));
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
