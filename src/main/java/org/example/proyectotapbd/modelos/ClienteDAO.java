package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class ClienteDAO extends DAO<ClienteDAO> {
    private int idCte;
    private String nomCte;
    private String telCte;
    private String direccion;
    private String emailCte;

    public int getIdCte() { return idCte; }
    public void setIdCte(int idCte) { this.idCte = idCte; }
    public String getNomCte() { return nomCte; }
    public void setNomCte(String nomCte) { this.nomCte = nomCte; }
    public String getTelCte() { return telCte; }
    public void setTelCte(String telCte) { this.telCte = telCte; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEmailCte() { return emailCte; }
    public void setEmailCte(String emailCte) { this.emailCte = emailCte; }

    public void INSERT() {
        String query = "INSERT INTO cliente(nomCte, telCte, direccion, emailCte) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = Conexion.connection.prepareStatement(query)) {
            stmt.setString(1, nomCte);
            stmt.setString(2, telCte);
            stmt.setString(3, direccion);
            stmt.setString(4, emailCte);
            stmt.executeUpdate();

        } catch (SQLException e) {
            manejarSQLException(e);
        }
    }

    public void UPDATE() {
        String query = "UPDATE cliente SET nomCte = ?, telCte = ?, direccion = ?, emailCte = ? WHERE idCte = ?";
        try (PreparedStatement stmt = Conexion.connection.prepareStatement(query)) {
            stmt.setString(1, nomCte);
            stmt.setString(2, telCte);
            stmt.setString(3, direccion);
            stmt.setString(4, emailCte);
            stmt.setInt(5, idCte);
            stmt.executeUpdate();
        } catch (SQLException e) {
            manejarSQLException(e);
        }
    }


    public void DELETE() {
        String query = "DELETE FROM cliente WHERE idCte = ?";
        try (PreparedStatement stmt = Conexion.connection.prepareStatement(query)) {
            stmt.setInt(1, idCte);
            stmt.executeUpdate();
        } catch (SQLException e) {
            manejarSQLException(e);
        }
    }


    public ObservableList<ClienteDAO> SELECT() {
        String query = "SELECT * FROM cliente";
        ObservableList<ClienteDAO> listaC = FXCollections.observableArrayList();
        ClienteDAO objC;

        try (Statement stmt = Conexion.connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                objC = new ClienteDAO();
                objC.setIdCte(rs.getInt("idCte"));
                objC.setNomCte(rs.getString("nomCte"));
                objC.setTelCte(rs.getString("telCte"));
                objC.setDireccion(rs.getString("direccion"));
                objC.setEmailCte(rs.getString("emailCte"));
                listaC.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaC;
    }
}
