package org.example.proyectotapbd.vistas;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.example.proyectotapbd.modelos.DAO;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.lang.reflect.Method;

public class VistaGenerica<T> extends Stage {

    private TableView<T> tableView;
    private Scene escena;

    public VistaGenerica(Class<? extends DAO<T>> claseDAO) {
        try {
            DAO<T> dao = claseDAO.getDeclaredConstructor().newInstance();
            CrearUI(dao);
            this.setTitle("Listado de " + claseDAO.getSimpleName());
            this.setScene(escena);
            this.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CrearUI(DAO<T> dao) {
        tableView = new TableView<>();
        crearColumnasDinamicamente(dao.getModelClass());
        tableView.setItems(dao.SELECT());
        VBox vbox = new VBox(new ToolBar(), tableView);
        escena = new Scene(vbox, 800, 600);
    }

    private void crearColumnasDinamicamente(Class<T> modeloClass) {
        for (Method method : modeloClass.getMethods()) {
            if (method.getName().startsWith("get") &&
                    method.getParameterCount() == 0 &&
                    !method.getName().equals("getClass")) {

                String property = method.getName().substring(3);
                property = property.substring(0, 1).toLowerCase() + property.substring(1);

                TableColumn<T, String> column = new TableColumn<>(property);
                column.setCellValueFactory(new PropertyValueFactory<>(property));
                tableView.getColumns().add(column);
            }
        }
    }
}
