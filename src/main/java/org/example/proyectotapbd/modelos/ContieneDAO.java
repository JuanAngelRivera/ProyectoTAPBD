package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ContieneDAO extends DAO<ContieneDAO> {
    private int idOrd;     // id de la Orden
    private int idProd;    // id del Producto
    private int cantidad;  // cantidad del producto en la orden

    // Getters y setters
    public int getIdOrd() {
        return idOrd;
    }

    public void setIdOrd(int idOrd) {
        this.idOrd = idOrd;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String getSufijo() {
        return "Ord Prod";
    }

    @Override
    public void INSERT() {
        String query = "INSERT INTO contiene(idOrd, idProd, cantidad) VALUES(" +
                idOrd + ", " + idProd + ", " + cantidad + ");";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UPDATE() {
        // Al igual que en otras tablas n-n, no es común hacer UPDATE sobre claves primarias,
        // pero sí sobre atributos como cantidad
        String query = "UPDATE contiene SET cantidad = " + cantidad +
                " WHERE idOrd = " + idOrd + " AND idProd = " + idProd + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DELETE() {
        String query = "DELETE FROM contiene WHERE idOrd = " + idOrd + " AND idProd = " + idProd + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<ContieneDAO> SELECT() {
        ObservableList<ContieneDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM contiene;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ContieneDAO obj = new ContieneDAO();
                obj.setIdOrd(rs.getInt("idOrd"));
                obj.setIdProd(rs.getInt("idProd"));
                obj.setCantidad(rs.getInt("cantidad"));
                lista.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Class<ContieneDAO> getModelClass() {
        return ContieneDAO.class;
    }
}
