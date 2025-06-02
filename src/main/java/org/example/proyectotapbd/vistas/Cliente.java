package org.example.proyectotapbd.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.componentes.VentanaError;
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
        Label lblNombre = new Label("Nombre: ");
        Label lblDireccion = new Label("Direccion: ");
        Label lblTelCte = new Label("Telefono: ");
        Label lblEmail = new Label("Email: ");
        txtNomCte = new TextField();
        txtDireccion = new TextField();
        txtTelCte = new TextField();
        txtEmail = new TextField();
        VBox vboxLbl = new VBox(lblNombre, lblDireccion, lblTelCte, lblEmail);
        vboxLbl.setAlignment(Pos.TOP_CENTER);
        vboxLbl.setSpacing(15);

        btnGuardar = new Button("Guardar");
        btnGuardar.setAlignment(Pos.BOTTOM_RIGHT);
        btnGuardar.setOnAction(e -> {
            System.out.println("Datos del guardado: " + txtNomCte.getText() + " " + txtDireccion.getText() + " " + txtTelCte.getText() + " " + txtEmail.getText());
            clienteDAO.setNomCte(txtNomCte.getText());
            clienteDAO.setDireccion(txtDireccion.getText());
            clienteDAO.setTelCte(txtTelCte.getText());
            clienteDAO.setEmailCte(txtEmail.getText());

            if (clienteDAO.getIdCte() > 0){
                System.out.println("sE VA A ACTUALIZAR CLIENTE");
                clienteDAO.UPDATE();
            }
            else
            {
                System.out.println("SE VA A INSERTAR CLIENTE");
                clienteDAO.INSERT();
            }

            tbvClientes.setItems(clienteDAO.SELECT());
            tbvClientes.refresh();
            new VentanaError("Usuario registrado correctamente");
            this.close();
        });
        VBox vboxtxt = new VBox(txtNomCte, txtDireccion, txtTelCte, txtEmail, btnGuardar);
        vboxtxt.setSpacing(3);
        vboxtxt.setAlignment(Pos.CENTER_RIGHT);
        HBox hbox = new HBox(vboxLbl, vboxtxt);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        vbox = new VBox(hbox);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        escena = new Scene(vbox, 300, 150);
    }

    public Cliente (TableView <ClienteDAO> tbvClientes, ClienteDAO clienteDAO)
    {
        this.tbvClientes = tbvClientes;
        crearUI();
        if (clienteDAO == null)
            this.clienteDAO = new ClienteDAO();
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
