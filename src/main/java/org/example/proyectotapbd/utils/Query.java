package org.example.proyectotapbd.utils;

import org.example.proyectotapbd.utils.modelos.ClienteDAO;
import org.example.proyectotapbd.utils.modelos.Conexion;
import org.example.proyectotapbd.utils.modelos.EmpleadoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public  class Query {
    public static ClienteDAO sesionCliente(String correo) {
        String query = "SELECT * FROM cliente WHERE emailCte = ?;";
        ClienteDAO objC = null;

        try (PreparedStatement ps = Conexion.connection.prepareStatement(query)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    objC = new ClienteDAO();
                    objC.setIdCte(rs.getInt("idCte"));
                    objC.setNomCte(rs.getString("nomCte"));
                    objC.setTelCte(rs.getString("telCte"));
                    objC.setDireccion(rs.getString("direccion"));
                    objC.setEmailCte(rs.getString("emailCte"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objC;
    }

    public static EmpleadoDAO sesionEmpleado(String RFC) {
        String query = "SELECT * FROM empleado WHERE RFC = ?;";
        EmpleadoDAO emp = null;

        try (PreparedStatement ps = Conexion.connection.prepareStatement(query)) {
            ps.setString(1, RFC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new EmpleadoDAO();
                    emp.setIdEmp(rs.getInt("idEmp"));
                    emp.setPuestoEmp(rs.getString("puestoEmp"));
                    emp.setTelEmp(rs.getString("telEmp"));
                    emp.setCurp(rs.getString("CURP"));
                    emp.setRfc(rs.getString("RFC"));
                    emp.setFechIngreso(rs.getString("fechIngreso"));
                    emp.setNomEmp(rs.getString("nomEmp"));
                    emp.setNss(rs.getString("NSS"));
                    emp.setSueldoEmp(rs.getDouble("SueldoEmp"));
                    emp.setIdTurno(rs.getInt("idTurno"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    public static List<EmpleadoDAO> getClavesEmpleados() {
        String query = "SELECT nomEmp, RFC FROM empleado;";
        List<EmpleadoDAO> claves = new ArrayList<>();  // Inicializada correctamente

        try (PreparedStatement ps = Conexion.connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmpleadoDAO emp = new EmpleadoDAO();
                emp.setNomEmp(rs.getString("nomEmp"));
                emp.setRfc(rs.getString("RFC"));
                claves.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return claves;
    }

}
