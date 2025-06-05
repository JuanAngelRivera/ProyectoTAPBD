package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.utils.modelos.Metodos;

public class VistaEmpleado extends Stage {
    public VistaEmpleado() {
        Scene scene = new Scene(new Button("INTERFAZ EMPLEADO"));
        this.setScene(scene);
        Metodos.configVista(this, new VBox());
    }
}
