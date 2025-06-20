package org.example.proyectotapbd.modelos;

import javafx.collections.ObservableList;
import org.example.proyectotapbd.BD.ManejadorErrores;
import java.sql.SQLException;

public abstract class DAO <T>
{
    public abstract String getSufijo();
    public abstract void INSERT();
    public abstract void UPDATE();
    public abstract void DELETE();
    public abstract ObservableList<T> SELECT();
    public abstract Class<T> getModelClass();

    protected void manejarSQLException(SQLException e) {
        ManejadorErrores.manejarExcepcion(e);
    }
}
