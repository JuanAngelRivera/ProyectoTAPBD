package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.CategoriaDAO;
import org.example.proyectotapbd.componentes.ButtonCell;

import java.util.Optional;

public class ListaCategorias extends Stage
{

    private ToolBar tlbMenu;
    private TableView<CategoriaDAO> tbvCategorias;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;

    public ListaCategorias()
    {
        CrearUI();
        this.setTitle("Listado de Categorias");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {
        tbvCategorias = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(event -> new Categoria(tbvCategorias, null));
        ImageView imv = new ImageView(new Image("file:modulo/src/com/restaurante/imagenes/add_icon.png"));
        imv.setFitWidth(30);
        imv.setFitHeight(30);
        tlbMenu = new ToolBar(btnAgregar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvCategorias);
        escena = new Scene(vBox, 800, 600);
    }

    private void CreateTable()
    {
        CategoriaDAO objC = new CategoriaDAO();

        TableColumn<CategoriaDAO,String> tbcNomCat = new TableColumn<>("Nombre");
        tbcNomCat.setCellValueFactory(new PropertyValueFactory<>("nomCat"));

        TableColumn<CategoriaDAO,String> tbcDesc = new TableColumn<>("Descripción");
        tbcDesc.setCellValueFactory(new PropertyValueFactory<>("descCat"));

        TableColumn<CategoriaDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory( col -> new ButtonCell<>("Editar", (tbvCategorias, obj) -> {
            new Categoria(tbvCategorias, obj);
            tbvCategorias.setItems(obj.SELECT());
            tbvCategorias.refresh();
        }));

        TableColumn<CategoriaDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>("Eliminar", (tbvCategorias, obj) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setContentText("Deseas eliminar el registro seleccionado?");
            Optional<ButtonType> opcion =alert.showAndWait();
            if(opcion.get() == ButtonType.OK)
                objC.DELETE();
            tbvCategorias.setItems(objC.SELECT());
            tbvCategorias.refresh();
        }));

        tbvCategorias.getColumns().addAll(tbcNomCat,tbcDesc);//,tbcEditar,tbcEliminar);
        tbvCategorias.setItems(objC.SELECT());
    }
}