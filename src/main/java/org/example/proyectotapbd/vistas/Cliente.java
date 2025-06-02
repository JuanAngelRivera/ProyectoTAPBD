package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.ClientesDAO;

public class Cliente extends Stage
{
    private Button btnGuardar;
    private TextField txtNomCte, txtDireccion, txtTelCte, txtEmail;
    private VBox vbox;
    private Scene escena;
    private ClientesDAO clientesDAO;
    private TableView <ClientesDAO> tbvClientes;
    public void crearUI()
    {
        txtNomCte = new TextField();
        txtDireccion = new TextField();
        txtTelCte = new TextField();
        txtEmail = new TextField();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            clientesDAO.setNomCte(txtNomCte.getText());
            clientesDAO.setDireccion(txtDireccion.getText());
            clientesDAO.setTelCte(txtTelCte.getText());
            clientesDAO.setEmailCte(txtEmail.getText());

            if (clientesDAO.getIdCte() > 0)
                clientesDAO.UPDATE();
            else
                clientesDAO.INSERT();

            tbvClientes.setItems(clientesDAO.SELECT());
            tbvClientes.refresh();
            this.close();
        });
        vbox = new VBox(txtNomCte, txtDireccion, txtTelCte, txtEmail, btnGuardar);
        escena = new Scene(vbox, 120, 150);
    }

    public Cliente (TableView <ClientesDAO> tbvClientes, ClientesDAO clientesDAO)
    {
        this.tbvClientes = tbvClientes;
        crearUI();
        if (clientesDAO == null)
            new ClientesDAO();
        else
        {
            this.clientesDAO = clientesDAO;
            txtNomCte.setText(this.clientesDAO.getNomCte());
            txtDireccion.setText(this.clientesDAO.getDireccion());
            txtTelCte.setText(this.clientesDAO.getTelCte());
            txtEmail.setText(this.clientesDAO.getEmailCte());
        }

        this.setTitle("Cliente");
        this.setScene(escena);
        this.show();
    }
}
