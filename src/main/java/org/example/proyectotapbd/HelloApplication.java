package org.example.proyectotapbd;

import javafx.geometry.Insets;
import javafx.scene.control.ToolBar;
import javafx.stage.StageStyle;
import org.example.proyectotapbd.vistas.*;
import org.example.proyectotapbd.modelos.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application
{
    Scene scene;
    VBox root;
    private void create_ui()
    {
        Button salir = new Button("Salir");
        salir.setOnAction(e -> System.exit(0));
        Button admin = new Button("Admin");
        admin.setOnAction(e -> new Login(true));
        Button empleado = new Button("Empleado");
        empleado.setOnAction(e -> new Login(false));
        Button user = new Button("User");
        user.setOnAction(e -> new Bienvenida());
        ToolBar toolbar = new ToolBar(salir, admin, empleado, user);


        root = new VBox(toolbar);
        root.setPadding(new Insets(10));
        scene = new Scene(root, 800, 600);
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        Conexion.create_connection();
        create_ui();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setMaximized(true);
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}