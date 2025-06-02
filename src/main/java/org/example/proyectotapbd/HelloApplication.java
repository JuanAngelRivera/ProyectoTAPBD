package org.example.proyectotapbd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.Conexion;
import java.io.IOException;

public class HelloApplication extends Application
{
    Scene scene;

    private void create_ui()
    {
        scene = new Scene(new VBox(), 800, 600);
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        Conexion.create_connection();
        create_ui();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}