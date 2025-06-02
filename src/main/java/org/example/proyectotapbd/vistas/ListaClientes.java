package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.proyectotapbd.modelos.ClientesDAO;
import org.example.proyectotapbd.componentes.ButtonCell;

import java.util.Optional;

public class ListaClientes extends Stage
{

    private ToolBar tlbMenu;
    private TableView<ClientesDAO> tbvClientes;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaClientes(){
        CrearUI();
        this.setTitle("Listado de Clientes :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {
        try
        {
            tbvClientes = new TableView<>();
            btnAgregar = new Button();
            btnAgregar.setOnAction(event -> new Cliente(tbvClientes, null));
            ImageView imv = new ImageView(new Image("file:modulo/src/com/restaurante/imagenes/add_icon.png"));
            imv.setFitWidth(30);
            imv.setFitHeight(30);
            tlbMenu = new ToolBar(btnAgregar);
            CreateTable();
            vBox = new VBox(tlbMenu, tbvClientes);
            escena = new Scene(vBox, 800, 600);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void CreateTable()
    {
        ClientesDAO clientesDAO = new ClientesDAO();
        TableColumn<ClientesDAO,String> tbcNomCte = new TableColumn<>("Nombre");
        tbcNomCte.setCellValueFactory(new PropertyValueFactory<>("nomCte"));
        TableColumn<ClientesDAO,String> tbcDireccion = new TableColumn<>("Dirección");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn<ClientesDAO,String> tbcTel = new TableColumn<>("Telefono");
        tbcTel.setCellValueFactory(new PropertyValueFactory<>("telCte"));
        TableColumn<ClientesDAO,String> tbcEmail = new TableColumn<>("Email");
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("emailCte"));

        TableColumn<ClientesDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory( col -> new ButtonCell<>("Editar", (tbvClientes, objC) -> {
            new Cliente(tbvClientes, objC);
            tbvClientes.setItems(objC.SELECT());
            tbvClientes.refresh();
        }));

        TableColumn<ClientesDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>("Eliminar", (tbvClientes, objC) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setContentText("Deseas eliminar el registro seleccionado?");
            Optional<ButtonType> opcion =alert.showAndWait();
            if(opcion.get() == ButtonType.OK)
                objC.DELETE();
            tbvClientes.setItems(objC.SELECT());
            tbvClientes.refresh();
        }));


        tbvClientes.getColumns().addAll(tbcNomCte,tbcDireccion,tbcTel,tbcEmail,tbcEditar,tbcEliminar);
        tbvClientes.setItems(clientesDAO.SELECT());
    }
}