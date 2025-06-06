package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class TurnoDAO extends DAO<TurnoDAO> {
    private int idTurno;
    private String horaInicio;
    private String horaFin;
    private String descTurno;

    // Getters y setters
    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getDescTurno() {
        return descTurno;
    }

    public void setDescTurno(String descTurno) {
        this.descTurno = descTurno;
    }

    @Override
    public void INSERT() {
        String query = "INSERT INTO turno(horaInicio, horaFin, descripcion) VALUES('" + horaInicio + "', '" + horaFin + "', '" + descTurno + "');";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UPDATE() {
        String query = "UPDATE turno SET horaInicio = '" + horaInicio + "', horaFin = '" + horaFin + "', descripcion = '" + descTurno + "' WHERE idTurno = " + idTurno + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DELETE() {
        String query = "DELETE FROM turno WHERE idTurno = " + idTurno + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<TurnoDAO> SELECT() {
        ObservableList<TurnoDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM turno;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TurnoDAO t = new TurnoDAO();
                t.setIdTurno(rs.getInt("idTurno"));
                t.setHoraInicio(rs.getString("horaInicio"));
                t.setHoraFin(rs.getString("horaFin"));
                t.setDescTurno(rs.getString("descTurno"));
                lista.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String getSufijo() {
        return "Turno";
    }

    @Override
    public Class<TurnoDAO> getModelClass() {
        return TurnoDAO.class;
    }
}
