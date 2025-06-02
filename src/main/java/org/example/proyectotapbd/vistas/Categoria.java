package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.CategoriaDAO;

public class Categoria extends Stage
{
    private Button btnGuardar;
    private TextField txtNomCat, txtDescCat;
    private VBox vbox;
    private Scene escena;
    private CategoriaDAO categoriasDAO;
    private TableView <CategoriaDAO> tbvCategorias;
    public void crearUI()
    {
        txtNomCat = new TextField();
        txtDescCat = new TextField();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            categoriasDAO.setNombreCategoria(txtNomCat.getText());
            categoriasDAO.setDescripcion(txtDescCat.getText());

            if (categoriasDAO.getIdCategoria() > 0)
                categoriasDAO.UPDATE();
            else
                categoriasDAO.INSERT();

            tbvCategorias.setItems(categoriasDAO.SELECT());
            tbvCategorias.refresh();
            this.close();
        });
        vbox = new VBox(txtNomCat, txtDescCat, btnGuardar);
        escena = new Scene(vbox, 120, 150);
    }

    public Categoria (TableView <CategoriaDAO> tbvCategorias, CategoriaDAO categoriasDAO)
    {
        this.tbvCategorias = tbvCategorias;
        crearUI();
        if (categoriasDAO == null)
            new CategoriaDAO();
        else
        {
            this.categoriasDAO = categoriasDAO;
            txtNomCat.setText(this.categoriasDAO.getNombreCategoria());
            txtDescCat.setText(this.categoriasDAO.getDescripcion());
        }

        this.setTitle("Categoria");
        this.setScene(escena);
        this.show();
    }
}
