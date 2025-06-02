package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.CategoriasDAO;

public class Categoria extends Stage
{
    private Button btnGuardar;
    private TextField txtNomCat, txtDescCat;
    private VBox vbox;
    private Scene escena;
    private CategoriasDAO categoriasDAO;
    private TableView <CategoriasDAO> tbvCategorias;
    public void crearUI()
    {
        txtNomCat = new TextField();
        txtDescCat = new TextField();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            categoriasDAO.setNomCat(txtNomCat.getText());
            categoriasDAO.setDescCat(txtDescCat.getText());

            if (categoriasDAO.getIdCat() > 0)
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

    public Categoria (TableView <CategoriasDAO> tbvCategorias, CategoriasDAO categoriasDAO)
    {
        this.tbvCategorias = tbvCategorias;
        crearUI();
        if (categoriasDAO == null)
            new CategoriasDAO();
        else
        {
            this.categoriasDAO = categoriasDAO;
            txtNomCat.setText(this.categoriasDAO.getNomCat());
            txtDescCat.setText(this.categoriasDAO.getDescCat());
        }

        this.setTitle("Categoria");
        this.setScene(escena);
        this.show();
    }
}
