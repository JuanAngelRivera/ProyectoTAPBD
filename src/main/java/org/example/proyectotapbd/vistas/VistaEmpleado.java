package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.EmpleadoDAO;
import org.example.proyectotapbd.modelos.Metodos;

public class VistaEmpleado extends Stage {
    VBox root, vbox;
    Scene scene;
    void createUI(EmpleadoDAO empleado)
    {
        vbox = new VBox(new Button("INTERFAZ EMPLEADO: " + empleado.getNomEmp()));
        root = new VBox(vbox);
        scene = new Scene(root);
    }
    public VistaEmpleado(EmpleadoDAO empleado) {
        createUI(empleado);
        this.setScene(scene);
        Metodos.configVista(this, root);
    }
}
