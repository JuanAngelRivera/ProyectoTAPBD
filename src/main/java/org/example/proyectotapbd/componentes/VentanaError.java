package org.example.proyectotapbd.componentes;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaError extends Stage {
    Scene scene;
    void createUI(String descripcion){
        Text texto = new Text(descripcion);
        VBox vbox = new VBox();
        vbox.getChildren().add(texto);
        scene = new Scene(vbox);
    }

    public VentanaError(String descripcion){
        this.createUI(descripcion);
        setScene(scene);
    }
}
