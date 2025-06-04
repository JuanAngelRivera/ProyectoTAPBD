package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.ClienteDAO;
import org.example.proyectotapbd.componentes.ButtonCell;

import java.util.Optional;

public class ListaClientes extends Stage
{

    private ToolBar tlbMenu;
    private TableView<ClienteDAO> tbvClientes;
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
            btnAgregar = new Button("Agregar");
            btnAgregar.setOnAction(event -> new Cliente(tbvClientes, null));
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
        ClienteDAO clienteDAO = new ClienteDAO();
        TableColumn<ClienteDAO,String> tbcNomCte = new TableColumn<>("Nombre");
        tbcNomCte.setCellValueFactory(new PropertyValueFactory<>("nomCte"));
        TableColumn<ClienteDAO,String> tbcDireccion = new TableColumn<>("Dirección");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn<ClienteDAO,String> tbcTel = new TableColumn<>("Telefono");
        tbcTel.setCellValueFactory(new PropertyValueFactory<>("telCte"));
        TableColumn<ClienteDAO,String> tbcEmail = new TableColumn<>("Email");
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("emailCte"));

        TableColumn<ClienteDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory( col -> new ButtonCell<>("Editar", (tbvClientes, objC) -> {
            new Cliente(tbvClientes, objC);
            tbvClientes.setItems(objC.SELECT());
            tbvClientes.refresh();
        }));

        TableColumn<ClienteDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
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
        tbvClientes.setItems(clienteDAO.SELECT());
    }
}