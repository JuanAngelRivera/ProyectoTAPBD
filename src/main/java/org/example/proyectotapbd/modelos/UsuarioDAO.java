package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO extends DAO<UsuarioDAO> {
    private int idUsuario;
    private int idEmp;
    private String username;
    private String passwordHash;
    private String rol;

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public void INSERT() {
        String query = "INSERT INTO usuario(idEmp, username, passwordHash, rol) VALUES(" + idEmp + ", '" + username + "', '" + passwordHash + "', '" + rol + "');";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UPDATE() {
        String query = "UPDATE usuario SET idEmp = " + idEmp + ", username = '" + username + "', passwordHash = '" + passwordHash + "', rol = '" + rol + "' WHERE idUsuario = " + idUsuario + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DELETE() {
        String query = "DELETE FROM usuario WHERE idUsuario = " + idUsuario + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<UsuarioDAO> SELECT() {
        ObservableList<UsuarioDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM usuario;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                UsuarioDAO u = new UsuarioDAO();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setIdEmp(rs.getInt("idEmp"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("passwordHash"));
                u.setRol(rs.getString("rol"));
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Class<UsuarioDAO> getModelClass() {
        return UsuarioDAO.class;
    }
}
