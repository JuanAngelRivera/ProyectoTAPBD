package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class IngredientesDAO extends DAO<IngredientesDAO> {
    private int idIns;
    private int idProd;
    private int cantidad;

    // Getters y setters
    public int getIdIns() {
        return idIns;
    }

    public void setIdIns(int idIns) {
        this.idIns = idIns;
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

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    @Override
    public void INSERT() {
        String query = "INSERT INTO ingredientes(idIns, idProd, cantidad) VALUES(" + idIns + ", " + idProd + ", " + cantidad + ");";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UPDATE() {
        // Por ser tabla n-n con solo IDs como PK compuesta, normalmente no se hace update,
        // pero si se requiere, aquí va la lógica (usualmente se elimina y se inserta).
        System.out.println("No se soporta UPDATE en tabla n-n ingredientes.");
    }

    @Override
    public void DELETE() {
        String query = "DELETE FROM ingredientes WHERE idIns = " + idIns + " AND idProd = " + idProd + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<IngredientesDAO> SELECT() {
        ObservableList<IngredientesDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM ingredientes;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                IngredientesDAO obj = new IngredientesDAO();
                obj.setIdIns(rs.getInt("idIns"));
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
    public String getSufijo() {
        return "Ins Prod";
    }

    @Override
    public Class<IngredientesDAO> getModelClass() {
        return IngredientesDAO.class;
    }
}
