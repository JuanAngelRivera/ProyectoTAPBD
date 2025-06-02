package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriasDAO extends DAO <CategoriasDAO>
{
    private int idCat;
    private String nomCat;
    private String descCat;

    public int getIdCat()
    {
        return idCat;
    }

    public void setIdCat(int idCat)
    {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat)
    {
        this.nomCat = nomCat;
    }

    public String getDescCat()
    {
        return descCat;
    }

    public void setDescCat(String descCat)
    {
        this.descCat = descCat;
    }

    public void INSERT()
    {
        String query = "INSERT INTO categorias(nomCat, descCat) VALUES( '" + nomCat + "', '" + descCat + "');";
        try
        {
            Statement statement = Conexion.connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void UPDATE()
    {
        String query = "UPDATE categorias SET nomCat = '" + nomCat + "',descCat = '" + descCat + "' WHERE idCte = " + idCat + ";";
        try
        {
            Statement statement = Conexion.connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void DELETE()
    {
        String query = "DELETE FROM categorias WHERE idCat = " + idCat + ";";
        try
        {
            Statement statement = Conexion.connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ObservableList<CategoriasDAO> SELECT()
    {
        String query = "SELECT * FROM categorias;";
        ObservableList<CategoriasDAO> listaC = FXCollections.observableArrayList();
        CategoriasDAO objC;
        try
        {
            Statement statement = Conexion.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                objC = new CategoriasDAO();
                objC.setIdCat(resultSet.getInt("idCat"));
                objC.setNomCat(resultSet.getString("nomCat"));
                objC.setDescCat(resultSet.getString("descCat"));
                listaC.add(objC);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listaC;
    }
}
