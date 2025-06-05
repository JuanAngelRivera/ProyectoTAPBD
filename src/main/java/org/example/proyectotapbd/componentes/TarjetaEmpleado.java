package org.example.proyectotapbd.componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.proyectotapbd.modelos.EmpleadoDAO;
import org.example.proyectotapbd.modelos.TurnoDAO;
import org.example.proyectotapbd.utils.Query;
import org.example.proyectotapbd.vistas.VistaEmpleado;

public class TarjetaEmpleado extends HBox {
    public TarjetaEmpleado(EmpleadoDAO empleadoDAO) {
        TurnoDAO turnoDAO = Query.obtenerTurno(empleadoDAO.getIdTurno());
        if (turnoDAO != null) {
            Label nombre = new Label(empleadoDAO.getNomEmp());
            Label puesto = new Label(empleadoDAO.getPuestoEmp());
            Label turno = new Label(turnoDAO.getDescripcion());
            Label inicio = new Label(turnoDAO.getHoraInicio());
            Label fin = new Label(turnoDAO.getHoraFin());
            VBox vbox = new VBox(10, nombre, puesto, turno, inicio, fin);
            vbox.setAlignment(Pos.CENTER);
            HBox hbox = new HBox(vbox);
            hbox.setAlignment(Pos.CENTER);
            hbox.setPadding(new Insets(10));
            hbox.setOnMouseClicked(event -> new VistaEmpleado(empleadoDAO));
            this.getChildren().add(hbox);
        }
    }
}
