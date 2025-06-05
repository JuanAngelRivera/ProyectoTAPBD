package org.example.proyectotapbd.utils.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsumoDAO extends DAO<InsumoDAO> {
    private int idIns;
    private String nomIns;
    private String descIns;
    private double costoIns;
    private int idProv;

    public int getIdIns() { return idIns; }
    public void setIdIns(int idIns) { this.idIns = idIns; }

    public String getNomIns() { return nomIns; }
    public void setNomIns(String nomIns) { this.nomIns = nomIns; }

    public String getDescIns() { return descIns; }
    public void setDescIns(String descIns) { this.descIns = descIns; }

    public double getCostoIns() { return costoIns; }
    public void setCostoIns(double costoIns) { this.costoIns = costoIns; }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public void INSERT() {
        String query = "INSERT INTO insumo(nomIns, descIns, costoIns, idProv) VALUES('" + nomIns + "', '" + descIns + "', '" + costoIns + "', idProv" + ");";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE() {
        String query = "UPDATE insumo SET nomIns = '" + nomIns + "', descIns = '" + descIns + "', costoIns = '" + costoIns + "', idProv = " + idProv +
                " WHERE idIns = " + idIns + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DELETE() {
        String query = "DELETE FROM insumo WHERE idIns = " + idIns + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<InsumoDAO> SELECT() {
        ObservableList<InsumoDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM insumo;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                InsumoDAO i = new InsumoDAO();
                i.setIdIns(rs.getInt("idIns"));
                i.setNomIns(rs.getString("nomIns"));
                i.setDescIns(rs.getString("descIns"));
                i.setCostoIns(rs.getDouble("costoIns"));
                i.setIdProv(rs.getInt("idProv"));
                lista.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String getSufijo() {
        return "Ins";
    }

    @Override
    public Class<InsumoDAO> getModelClass() {
        return InsumoDAO.class;
    }
}
