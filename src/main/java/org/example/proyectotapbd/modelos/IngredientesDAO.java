package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class IngredientesDAO extends DAO<IngredientesDAO> {
    private int idIns;   // id del Insumo
    private int idProd;  // id del Producto

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

    @Override
    public void INSERT() {
        String query = "INSERT INTO ingredientes(idIns, idProd) VALUES(" + idIns + ", " + idProd + ");";
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
                lista.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    @Override
    public Class<IngredientesDAO> getModelClass() {
        return IngredientesDAO.class;
    }
}
