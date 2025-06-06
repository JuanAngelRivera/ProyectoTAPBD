package org.example.proyectotapbd.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.example.proyectotapbd.modelos.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControladorProductos extends HBox {
    public ControladorProductos() {
        Button btnMenos = new Button("-");
        Button btnMas  = new Button("+");
        Label cantidad = new Label("0");
        btnMenos.setOnAction(e -> {
            if(Integer.parseInt(cantidad.getText()) > 0){
                System.out.println("DEBIO REDUCIRSE");
                int cantidadActual = Integer.parseInt(cantidad.getText());
                cantidad.setText(String.valueOf(cantidadActual - 1));
            }
        });
        btnMas.setOnAction(e -> {
            System.out.println("DEBIO AUMENTAR");
            int cantidadActual = Integer.parseInt(cantidad.getText());
            cantidad.setText(String.valueOf(cantidadActual + 1));
        });
        this.getChildren().addAll( btnMenos, cantidad, btnMas);
        this.setSpacing(5);
        setAlignment(Pos.CENTER);
    }


}
