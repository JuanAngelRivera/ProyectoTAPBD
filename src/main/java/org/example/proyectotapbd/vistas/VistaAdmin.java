package org.example.proyectotapbd.vistas;

import javafx.geometry.Insets;
import javafx.scene.control.ToolBar;
import org.example.proyectotapbd.utils.Claves;
import org.example.proyectotapbd.modelos.*;
import org.example.proyectotapbd.modelos.ClienteDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaAdmin extends Stage
{
    Scene scene;
    VBox root;
    private void create_ui()
    {
        Button btnEmpleado = new Button("Empleado");
        btnEmpleado.setOnAction(actionEvent -> new VistaGenerica<>(EmpleadoDAO.class));
        Button btnInsumo = new Button("Insumo");
        btnInsumo.setOnAction(actionEvent -> new VistaGenerica<>(InsumoDAO.class));
        Button btnProvedor = new Button("Provedor");
        btnProvedor.setOnAction(actionEvent -> new VistaGenerica<>(ProveedorDAO.class));
        Button btnProducto = new Button("Producto");
        btnProducto.setOnAction(actionEvent -> new VistaGenerica<>(ProductoDAO.class));
        Button btnCategoria = new Button("Categoria");
        btnCategoria.setOnAction(actionEvent -> new VistaGenerica<>(CategoriaDAO.class));
        Button btnOrden = new Button("Orden");
        btnOrden.setOnAction(actionEvent -> new VistaGenerica<>(OrdenDAO.class));
        Button btnCliente = new Button("Cliente");
        btnCliente.setOnAction(actionEvent -> new VistaGenerica<>(ClienteDAO.class));
        Button btnMesa = new Button("Mesa");
        btnMesa.setOnAction(actionEvent -> new VistaGenerica<>(MesaDAO.class));
        Button btnReservacion = new Button("Reservacion");
        btnReservacion.setOnAction(actionEvent -> new VistaGenerica<>(ReservacionDAO.class));
        Button btnTurno = new Button("Turno");
        btnTurno.setOnAction(actionEvent -> new VistaGenerica<>(TurnoDAO.class));
        Button reporte = new Button("Reporte");
        reporte.setOnAction(actionEvent -> {});

        GridPane grid = new GridPane();
        grid.add(btnEmpleado, 0, 0);
        grid.add(btnInsumo, 1, 0);
        grid.add(btnProvedor, 2, 0);
        grid.add(btnProducto, 3, 0);
        grid.add(btnCategoria, 4, 0);
        grid.add(btnOrden, 0, 1);
        grid.add(btnCliente, 1, 1);
        grid.add(btnMesa, 2, 1);
        grid.add(btnReservacion, 3, 1);
        grid.add(btnTurno, 4, 1);
        grid.add(reporte, 4, 2);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(10));
        VBox vbox = new VBox(grid);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        root = new VBox(vbox);
        scene = new Scene(root, 800, 600);
    }

    public VistaAdmin()
    {
        create_ui();
        setScene(scene);
        Metodos.configVista(this, root);
        Button cerrarSesion = new Button("cerrar sesion");
        cerrarSesion.setOnAction(e -> {
            Claves.accesoAdmin = false; this.close();});
        ToolBar tlb = (ToolBar)root.getChildren().get(0);
        tlb.getItems().add(cerrarSesion);
    }
}