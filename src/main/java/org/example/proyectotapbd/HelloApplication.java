package org.example.proyectotapbd;

import org.example.proyectotapbd.vistas.*;
import org.example.proyectotapbd.modelos.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application
{
    Scene scene;

    private void create_ui()
    {
        Button btnEmpleado = new Button("Empleado");
        //btnEmpleado.setOnAction(actionEvent -> Empleado());
        Button btnInsumo = new Button("Insumo");
        btnInsumo.setOnAction(actionEvent -> {});
        Button btnProvedor = new Button("Provedor");
        btnProvedor.setOnAction(actionEvent -> {});
        Button btnProducto = new Button("Producto");
        btnProducto.setOnAction(actionEvent -> {});
        Button btnCategoria = new Button("Categoria");
        btnCategoria.setOnAction(actionEvent -> {});
        Button btnOrden = new Button("Orden");
        btnOrden.setOnAction(actionEvent -> {});
        Button btnCliente = new Button("Cliente");
        btnCliente.setOnAction(actionEvent -> new ListaClientes());
        Button btnMesa = new Button("Mesa");
        btnMesa.setOnAction(actionEvent -> {});
        Button btnReservacion = new Button("Reservacion");
        btnReservacion.setOnAction(actionEvent -> {});
        Button btnPago = new Button("Pago");
        btnPago.setOnAction(actionEvent -> {});
        Button btnTurno = new Button("Turno");
        btnTurno.setOnAction(actionEvent -> {});
        Button btnUsuario = new Button("Usuario");
        btnUsuario.setOnAction(actionEvent -> {});
        Button btnIngredientes = new Button("Ingredientes");
        btnIngredientes.setOnAction(actionEvent -> {});
        Button btnContiene = new Button("Contiene");
        btnContiene.setOnAction(actionEvent -> {});
        Button btnHace = new Button("Hace");
        btnHace.setOnAction(actionEvent -> {});

        GridPane grid = new GridPane();
        grid.add(btnEmpleado, 0, 0);
        grid.add(btnInsumo, 0, 1);
        grid.add(btnProvedor, 0, 2);
        grid.add(btnProducto, 0, 3);
        grid.add(btnCategoria, 0, 4);
        grid.add(btnOrden, 1, 0);
        grid.add(btnCliente, 1, 1);
        grid.add(btnMesa, 1, 2);
        grid.add(btnReservacion, 1, 3);
        grid.add(btnPago, 1, 4);
        grid.add(btnTurno, 2, 0);
        grid.add(btnUsuario, 2, 1);
        grid.add(btnIngredientes, 2, 2);
        grid.add(btnContiene, 2, 3);
        grid.add(btnHace, 2, 4);

        VBox vbox = new VBox(grid);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 800, 600);
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