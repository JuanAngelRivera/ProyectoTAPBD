package org.example.proyectotapbd.vistas;

import javafx.scene.control.ToolBar;
import org.example.proyectotapbd.utils.Claves;
import org.example.proyectotapbd.utils.modelos.*;
import org.example.proyectotapbd.utils.modelos.ClienteDAO;
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
        Button btnReservacion = new Button("Reservacion");
        Button btnPago = new Button("Pago");
        btnPago.setOnAction(actionEvent -> new VistaGenerica<>(PagoDAO.class));
        Button btnTurno = new Button("Turno");
        btnTurno.setOnAction(actionEvent -> new VistaGenerica<>(TurnoDAO.class));
        Button btnIngredientes = new Button("Ingredientes");
        btnIngredientes.setOnAction(actionEvent -> new VistaGenerica<>(IngredientesDAO.class));
        Button btnContiene = new Button("Contiene");
        btnContiene.setOnAction(actionEvent -> new VistaGenerica<>(ContieneDAO.class));
        Button btnSeHace = new Button("Hace");
        btnSeHace.setOnAction(actionEvent -> new VistaGenerica<>(SeHaceDAO.class));

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
        grid.add(btnIngredientes, 2, 1);
        grid.add(btnContiene, 2, 2);
        grid.add(btnSeHace, 2, 3);

        VBox vbox = new VBox(grid);
        vbox.setAlignment(Pos.CENTER);
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