package org.example.proyectotapbd.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.ClienteDAO;

public class Cliente extends Stage
{
    private Button btnGuardar;
    private TextField txtNomCte, txtDireccion, txtTelCte, txtEmail;
    private VBox vbox;
    private Scene escena;
    private ClienteDAO clienteDAO;
    private TableView <ClienteDAO> tbvClientes;
    public void crearUI()
    {
        txtNomCte = new TextField();
        txtDireccion = new TextField();
        txtTelCte = new TextField();
        txtEmail = new TextField();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            clienteDAO.setNomCte(txtNomCte.getText());
            clienteDAO.setDireccion(txtDireccion.getText());
            clienteDAO.setTelCte(txtTelCte.getText());
            clienteDAO.setEmailCte(txtEmail.getText());

            if (clienteDAO.getIdCte() > 0)
                clienteDAO.UPDATE();
            else
                clienteDAO.INSERT();

            tbvClientes.setItems(clienteDAO.SELECT());
            tbvClientes.refresh();
            this.close();
        });
        vbox = new VBox(txtNomCte, txtDireccion, txtTelCte, txtEmail, btnGuardar);
        escena = new Scene(vbox, 120, 150);
    }

    public Cliente (TableView <ClienteDAO> tbvClientes, ClienteDAO clienteDAO)
    {
        this.tbvClientes = tbvClientes;
        crearUI();
        if (clienteDAO == null)
            new ClienteDAO();
        else
        {
            this.clienteDAO = clienteDAO;
            txtNomCte.setText(this.clienteDAO.getNomCte());
            txtDireccion.setText(this.clienteDAO.getDireccion());
            txtTelCte.setText(this.clienteDAO.getTelCte());
            txtEmail.setText(this.clienteDAO.getEmailCte());
        }

        this.setTitle("Cliente");
        this.setScene(escena);
        this.show();
    }
}
