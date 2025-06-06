package org.example.proyectotapbd.utils;

import org.example.proyectotapbd.modelos.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static List<EmpleadoDAO> getEmpleados() {
        String query = "SELECT * FROM empleado;";
        List<EmpleadoDAO> claves = new ArrayList<>();  // Inicializada correctamente

        try (PreparedStatement ps = Conexion.connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmpleadoDAO emp = new EmpleadoDAO();
                emp.setCurp(rs.getString("CURP"));
                emp.setRfc(rs.getString("RFC"));
                emp.setFechIngreso(rs.getString("fechIngreso"));
                emp.setNss(rs.getString("NSS"));
                emp.setNomEmp(rs.getString("nomEmp"));
                emp.setPuestoEmp(rs.getString("puestoEmp"));
                emp.setIdTurno(rs.getInt("idTurno"));
                emp.setSueldoEmp(rs.getDouble("SueldoEmp"));
                emp.setIdEmp(rs.getInt("idEmp"));
                emp.setTelEmp(rs.getString("telEmp"));
                claves.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return claves;
    }

    public static TurnoDAO obtenerTurno(int idTurno){
        TurnoDAO turnoDAO = null;
        String query = "SELECT * FROM Turno WHERE idTurno = ?";
        try(PreparedStatement ps = Conexion.connection.prepareStatement(query)){
            ps.setInt(1, idTurno);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next())
                {
                    turnoDAO = new TurnoDAO();
                    turnoDAO.setIdTurno(rs.getInt("idTurno"));
                    turnoDAO.setHoraInicio(rs.getString("horaInicio"));
                    turnoDAO.setHoraFin(rs.getString("horaFin"));
                    turnoDAO.setDescTurno(rs.getString("descTurno"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return turnoDAO;
    }

    public static List<MesaDAO> obtenerMesas()
    {
        String query = "SELECT * FROM mesa;";
        List<MesaDAO> claves = new ArrayList<>();  // Inicializada correctamente

        try (PreparedStatement ps = Conexion.connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MesaDAO mesa = new MesaDAO();
                mesa.setIdMesa(rs.getInt("idMesa"));
                mesa.setCapacidad(rs.getInt("capacidad"));
                mesa.setOcupada(rs.getBoolean("ocupada"));
                claves.add(mesa);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return claves;
    }

    public static List<ClienteDAO> obtenerClientes()
    {
        String query = "SELECT * FROM cliente;";
        List<ClienteDAO> claves = new ArrayList<>();  // Inicializada correctamente

        try (PreparedStatement ps = Conexion.connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClienteDAO cliente = new ClienteDAO();
                cliente.setIdCte(rs.getInt("idCte"));
                cliente.setNomCte(rs.getString("nomCte"));
                cliente.setTelCte(rs.getString("telCte"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEmailCte(rs.getString("emailCte"));
                claves.add(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return claves;
    }

    public static List<CategoriaDAO> obtenerCategorias(){
        String query = "SELECT * FROM categoria;";
        List<CategoriaDAO> categorias = new ArrayList<>();
        try (PreparedStatement ps = Conexion.connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoriaDAO cat = new CategoriaDAO();
                cat.setIdCategoria(rs.getInt("idCat"));
                cat.setNombreCategoria(rs.getString("nomCat"));
                cat.setDescripcion(rs.getString("descCat"));
                categorias.add(cat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public static List<ProductoDAO> obtenerProductosPorCategoria(int idCat)
    {
        String query = "SELECT * FROM producto WHERE idCat = ?";
        List<ProductoDAO> productos = new ArrayList<>();
        try(PreparedStatement ps = Conexion.connection.prepareStatement(query))
        {
            ps.setInt(1, idCat);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next())
                {
                    ProductoDAO prod = new ProductoDAO();
                    prod.setIdProd(rs.getInt("idProd"));
                    prod.setNomProd(rs.getString("nomProd"));
                    prod.setPrecio(rs.getDouble("precio"));
                    prod.setCosto(rs.getDouble("costo"));
                    prod.setIdCat(rs.getInt("idCat"));
                    prod.setImagen(rs.getString("imagen"));
                    productos.add(prod);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productos;
    }

    public static List<InsumoDAO> obtenerIngredientes(int idProd)
    {
        String query = "SELECT * FROM Ingredientes WHERE idProd = ?";
        List<InsumoDAO> insumos = new ArrayList<>();
        try(PreparedStatement ps = Conexion.connection.prepareStatement(query)){
            ps.setInt(1, idProd);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next())
                {
                    InsumoDAO in = obtenerInsumoPorIngredientes(rs.getInt("idIns"));
                    System.out.println(in.getNomIns());
                   insumos.add(in);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insumos;
    }

    public static InsumoDAO obtenerInsumoPorIngredientes(int idIns){
        String query = "SELECT * FROM Insumo WHERE idIns = ?";
        InsumoDAO insumo = null;
        try(PreparedStatement ps = Conexion.connection.prepareStatement(query)){
            ps.setInt(1, idIns);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next())
                {
                    insumo = new InsumoDAO();
                    insumo.setIdIns(idIns);
                    insumo.setNomIns(rs.getString("nomIns"));
                    insumo.setDescIns(rs.getString("descIns"));
                    insumo.setIdProv(rs.getInt("idProv"));
                    insumo.setCostoIns(rs.getDouble("costoIns"));
                }
            }
        }catch (Exception e){e.printStackTrace();}
        return insumo;
    }

    public static int obtenerCantidadIngrediente(int idProd, int idIns){
        String query = "SELECT cantidad FROM Ingredientes WHERE idProd = ? && idIns = ?";
        try(PreparedStatement ps = Conexion.connection.prepareStatement(query)){
            ps.setInt(1, idProd);
            ps.setInt(2, idIns);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("cantidad");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int obtenerIdOrden(int idEmp, int idCte, int idMesa, String fecha, double total)
    {
        String query = "SELECT idOrd FROM orden WHERE idEmp = ? AND idCte = ? AND idMesa = ? and fecha = ? and total = ?";
        try(PreparedStatement ps = Conexion.connection.prepareStatement(query)){
            ps.setInt(1, idEmp);
            ps.setInt(2, idCte);
            ps.setInt(3, idMesa);
            ps.setString(4, fecha);
            ps.setDouble(5, total);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next())
                {
                    return rs.getInt("idOrd");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
