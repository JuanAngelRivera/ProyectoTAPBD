package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriaDAO extends DAO<CategoriaDAO> {
    private int idCategoria;
    private String nombreCategoria;
    private String descripcion;

    // Getters y setters
    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public void INSERT() {
        String query = "INSERT INTO categoria(nombreCategoria, descripcion) VALUES('" + nombreCategoria + "', '" + descripcion + "');";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UPDATE() {
        String query = "UPDATE categoria SET nombreCategoria = '" + nombreCategoria + "', descripcion = '" + descripcion +
                "' WHERE idCategoria = " + idCategoria + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DELETE() {
        String query = "DELETE FROM categoria WHERE idCategoria = " + idCategoria + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<CategoriaDAO> SELECT() {
        ObservableList<CategoriaDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM categoria;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                CategoriaDAO c = new CategoriaDAO();
                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setNombreCategoria(rs.getString("nombreCategoria"));
                c.setDescripcion(rs.getString("descripcion"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Class<CategoriaDAO> getModelClass() {
        return CategoriaDAO.class;
    }
}
