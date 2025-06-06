package org.example.proyectotapbd.vistas;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.componentes.ButtonCell;
import org.example.proyectotapbd.modelos.DAO;
import org.example.proyectotapbd.modelos.Metodos;
import org.example.proyectotapbd.modelos.ProductoDAO;

import java.lang.reflect.Field;

public class VistaGenerica<T extends DAO<T>> extends Stage {

    private TableView<T> tableView;
    private VBox vbox, root;
    private Scene escena;
    private Button btnAgregar;
    private T dao;

    public VistaGenerica(Class<T> claseDAO) {
        try {
            dao = claseDAO.getDeclaredConstructor().newInstance();
            crearUI(claseDAO);
            this.setTitle("Listado de " + claseDAO.getSimpleName().replace("DAO", ""));
            this.setScene(escena);
            Metodos.configVista(this, root);
            ToolBar tlb = (ToolBar) root.getChildren().get(0);
            tlb.getItems().add( btnAgregar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearUI(Class<T> claseDAO) {
        tableView = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> {
            if(claseDAO.getSimpleName().equals("ProductoDAO")) {
                new VistaFormularioProducto();
            }
            else {
                try {
                    new VistaFormularioGenerica<>(tableView, claseDAO.getDeclaredConstructor().newInstance());
                    tableView.setItems(dao.SELECT());
                    tableView.refresh();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Crear columnas dinámicas desde los campos
        Field[] fields = claseDAO.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            TableColumn<T, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(data -> {
                try {
                    Object value = field.get(data.getValue());
                    return new ReadOnlyStringWrapper(value != null ? value.toString() : "");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return new ReadOnlyStringWrapper("");
                }
            });
            tableView.getColumns().add(column);
        }

        // Botón Editar
        TableColumn<T, String> colEditar = new TableColumn<>("Editar");
        colEditar.setCellFactory(col -> new ButtonCell<>("Editar", (tbv, obj) -> {
            try {
                new VistaFormularioGenerica<>(tbv, obj);
                tableView.setItems(dao.SELECT());
                tableView.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        tableView.getColumns().add(colEditar);

        // Botón Eliminar
        TableColumn<T, String> colEliminar = new TableColumn<>("Eliminar");
        colEliminar.setCellFactory(col -> new ButtonCell<>("Eliminar", (tbv, obj) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Deseas eliminar el registro seleccionado?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    obj.DELETE();
                    tableView.setItems(dao.SELECT());
                    tableView.refresh();
                }
            });
        }));
        tableView.getColumns().add(colEliminar);
        tableView.setItems(dao.SELECT());
        vbox = new VBox(tableView);
        root = new VBox(vbox);
        escena = new Scene(root);
    }
}
