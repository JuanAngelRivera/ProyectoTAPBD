package org.example.proyectotapbd.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class PagoDAO extends DAO<PagoDAO> {
    private int idPago;
    private int idOrd;       // Id de la orden asociada
    private double monto;
    private String tipoPago;
    private String fechaPago;  // Lo guardamos como String para simplificar (puedes usar Date si prefieres)

    // Getters y setters
    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }

    public int getIdOrd() { return idOrd; }
    public void setIdOrd(int idOrd) { this.idOrd = idOrd; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getTipoPago() { return tipoPago; }
    public void setTipoPago(String tipoPago) { this.tipoPago = tipoPago; }

    public String getFechaPago() { return fechaPago; }
    public void setFechaPago(String fechaPago) { this.fechaPago = fechaPago; }

    @Override
    public void INSERT() {
        String query = "INSERT INTO pago(idOrd, monto, tipoPago, fechaPago) VALUES(" + idOrd + ", " + monto + ", '" + tipoPago + "', '" + fechaPago + "');";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UPDATE() {
        String query = "UPDATE pago SET idOrd = " + idOrd + ", monto = " + monto + ", tipoPago = '" + tipoPago + "', fechaPago = '" + fechaPago + "' WHERE idPago = " + idPago + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DELETE() {
        String query = "DELETE FROM pago WHERE idPago = " + idPago + ";";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<PagoDAO> SELECT() {
        ObservableList<PagoDAO> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM pago;";
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PagoDAO p = new PagoDAO();
                p.setIdPago(rs.getInt("idPago"));
                p.setIdOrd(rs.getInt("idOrd"));
                p.setMonto(rs.getDouble("monto"));
                p.setTipoPago(rs.getString("tipoPago"));
                p.setFechaPago(rs.getString("fechaPago"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Class<PagoDAO> getModelClass() {
        return PagoDAO.class;
    }
}
