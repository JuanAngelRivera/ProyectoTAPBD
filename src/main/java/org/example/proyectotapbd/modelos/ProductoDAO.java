package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductoDAO extends DAO<ProductoDAO> {
    private int idProd;
    private String nomProd;
    private double precio;
    private double costo;
    private int idCat;
    private String imagen;

    public int getIdProd() { return idProd; }
    public void setIdProd(int idProd) { this.idProd = idProd; }

    public String getNomProd() { return nomProd; }
    public void setNomProd(String nomProd) { this.nomProd = nomProd; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public void INSERT() {
        String query = "INSERT INTO producto(nomProd, precio, costo, idCat, imagen) VALUES('" + nomProd + "', " + precio + ", " + costo + ", " + idCat + ", '" + imagen + "')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE() {
        String query = "UPDATE producto SET nomProd = '" + nomProd + "', precio = " + precio + ", costo = " + costo + ", idCat = " + idCat + ", imagen = " + imagen +
                " WHERE idProd = " + idProd + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DELETE() {
        String query = "DELETE FROM producto WHERE idProd = " + idProd + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProductoDAO> SELECT() {
        ObservableList<ProductoDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM producto;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ProductoDAO p = new ProductoDAO();
                p.setIdProd(rs.getInt("idProd"));
                p.setNomProd(rs.getString("nomProd"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCosto(rs.getDouble("costo"));
                p.setIdCat(rs.getInt("idCat"));
                p.setImagen(rs.getString("imagen"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String getSufijo() {
        return "Prod";
    }

    @Override
    public Class<ProductoDAO> getModelClass() {
        return ProductoDAO.class;
    }
}
