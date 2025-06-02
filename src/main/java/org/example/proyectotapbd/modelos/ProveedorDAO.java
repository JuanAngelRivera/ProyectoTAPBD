// DAOs para todas las entidades de la base de datos "restaurante"
// Todos heredan de DAO<T> y siguen el formato del ClienteDAO

package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

// 1. ProveedorDAO
public class ProveedorDAO extends DAO<ProveedorDAO> {
    private int idProv;
    private String nomProv, emailProv, telProv, direcProv, descProv;

    public int getIdProv() { return idProv; }
    public void setIdProv(int idProv) { this.idProv = idProv; }
    public String getNomProv() { return nomProv; }
    public void setNomProv(String nomProv) { this.nomProv = nomProv; }
    public String getEmailProv() { return emailProv; }
    public void setEmailProv(String emailProv) { this.emailProv = emailProv; }
    public String getTelProv() { return telProv; }
    public void setTelProv(String telProv) { this.telProv = telProv; }
    public String getDirecProv() { return direcProv; }
    public void setDirecProv(String direcProv) { this.direcProv = direcProv; }
    public String getDescProv() { return descProv; }
    public void setDescProv(String descProv) { this.descProv = descProv; }

    public void INSERT() {
        String query = "INSERT INTO proveedor(nomProv, emailProv, telProv, direcProv, descProv) VALUES ('" +
                nomProv + "', '" + emailProv + "', '" + telProv + "', '" + direcProv + "', '" + descProv + "');";
        try { Statement stmt = Conexion.connection.createStatement(); stmt.executeUpdate(query); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void UPDATE() {
        String query = "UPDATE proveedor SET nomProv = '" + nomProv + "', emailProv = '" + emailProv +
                "', telProv = '" + telProv + "', direcProv = '" + direcProv + "', descProv = '" + descProv +
                "' WHERE idProv = " + idProv + ";";
        try { Statement stmt = Conexion.connection.createStatement(); stmt.executeUpdate(query); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void DELETE() {
        String query = "DELETE FROM proveedor WHERE idProv = " + idProv + ";";
        try { Statement stmt = Conexion.connection.createStatement(); stmt.executeUpdate(query); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public ObservableList<ProveedorDAO> SELECT() {
        ObservableList<ProveedorDAO> lista = FXCollections.observableArrayList();
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM proveedor;");
            while (rs.next()) {
                ProveedorDAO p = new ProveedorDAO();
                p.setIdProv(rs.getInt("idProv"));
                p.setNomProv(rs.getString("nomProv"));
                p.setEmailProv(rs.getString("emailProv"));
                p.setTelProv(rs.getString("telProv"));
                p.setDirecProv(rs.getString("direcProv"));
                p.setDescProv(rs.getString("descProv"));
                lista.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}

// Puedo continuar con InsumoDAO, ProductoDAO, MesaDAO, OrdenDAO, etc.
// ¿Te gustaría que los incluya todos aquí o los generamos por partes según los vayas necesitando?
