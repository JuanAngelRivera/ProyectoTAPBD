package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadoDAO extends DAO<EmpleadoDAO> {

    private int idEmp;
    private String puestoEmp;
    private String horario;
    private String telEmp;
    private String curp;
    private String rfc;
    private String fechIngreso;
    private String nomEmp;
    private String nss;
    private double sueldoEmp;

    public int getIdEmp() { return idEmp; }
    public void setIdEmp(int idEmp) { this.idEmp = idEmp; }

    public String getPuestoEmp() { return puestoEmp; }
    public void setPuestoEmp(String puestoEmp) { this.puestoEmp = puestoEmp; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getTelEmp() { return telEmp; }
    public void setTelEmp(String telEmp) { this.telEmp = telEmp; }

    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    public String getFechIngreso() { return fechIngreso; }
    public void setFechIngreso(String fechIngreso) { this.fechIngreso = fechIngreso; }

    public String getNomEmp() { return nomEmp; }
    public void setNomEmp(String nomEmp) { this.nomEmp = nomEmp; }

    public String getNss() { return nss; }
    public void setNss(String nss) { this.nss = nss; }

    public double getSueldoEmp() { return sueldoEmp; }
    public void setSueldoEmp(double sueldoEmp) { this.sueldoEmp = sueldoEmp; }

    @Override
    public void INSERT() {
        String query = "INSERT INTO empleado(puestoEmp, horario, telEmp, CURP, RFC, fechIngreso, nomEmp, NSS, SueldoEmp) VALUES ('" +
                puestoEmp + "', '" + horario + "', '" + telEmp + "', '" + curp + "', '" + rfc + "', '" +
                fechIngreso + "', '" + nomEmp + "', '" + nss + "', " + sueldoEmp + ");";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UPDATE() {
        String query = "UPDATE empleado SET puestoEmp = '" + puestoEmp + "', horario = '" + horario +
                "', telEmp = '" + telEmp + "', CURP = '" + curp + "', RFC = '" + rfc + "', fechIngreso = '" + fechIngreso +
                "', nomEmp = '" + nomEmp + "', NSS = '" + nss + "', SueldoEmp = " + sueldoEmp +
                " WHERE idEmp = " + idEmp + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DELETE() {
        String query = "DELETE FROM empleado WHERE idEmp = " + idEmp + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<EmpleadoDAO> SELECT() {
        ObservableList<EmpleadoDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM empleado;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                EmpleadoDAO emp = new EmpleadoDAO();
                emp.setIdEmp(rs.getInt("idEmp"));
                emp.setPuestoEmp(rs.getString("puestoEmp"));
                emp.setHorario(rs.getString("horario"));
                emp.setTelEmp(rs.getString("telEmp"));
                emp.setCurp(rs.getString("CURP"));
                emp.setRfc(rs.getString("RFC"));
                emp.setFechIngreso(rs.getString("fechIngreso"));
                emp.setNomEmp(rs.getString("nomEmp"));
                emp.setNss(rs.getString("NSS"));
                emp.setSueldoEmp(rs.getDouble("SueldoEmp"));
                lista.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
