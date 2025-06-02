package org.example.proyectotapbd.modelos;

import java.sql.Statement;

public class MesasDAO
{
    private int idMesa;
    private int capacidad;

    public int getIdMesa(){return idMesa;}

    public void setIdMesa(int idMesa) {this.idMesa = idMesa;}

    public int getCapacidad() {return capacidad;}

    public void setCapacidad(int capacidad) {this.capacidad = capacidad;}

    public void INSERT()
    {
        String query = "INSERT INTO mesas(idMesa) VALUES(" + capacidad + ");";
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
        String query = "INSERT INTO mesas(capacidad) VALUES(" + capacidad + ");";
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
    public void DELETE(){

    }

}
