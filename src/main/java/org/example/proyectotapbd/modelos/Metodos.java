package org.example.proyectotapbd.modelos;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Metodos {
    static public void configVista(Stage stage, VBox vbox) {
        Button volver = new Button("Volver");
        volver.setOnAction(e -> stage.close());
        ToolBar toolBar = new ToolBar(volver);
        vbox.getChildren().add(0, toolBar);
        vbox.setPadding(new Insets(10));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }
}
