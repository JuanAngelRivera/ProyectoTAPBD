package org.example.proyectotapbd.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.ClienteDAO;
import org.example.proyectotapbd.modelos.MesaDAO;
import org.example.proyectotapbd.modelos.Metodos;
import org.example.proyectotapbd.utils.Query;

import java.time.LocalDate;
import java.util.List;

public class VistaReservacion extends Stage {
    Scene scene;
    VBox vbox, root;
    HBox hbox;
    MesaDAO mesaSeleccionado;

    public void createUI(ClienteDAO clienteDAO) {
        mesaSeleccionado = null;
        HBox clienteHbox = clienteHbox(clienteDAO);
        HBox mesaHbox = mesasHbox();
        DatePicker calendario = new DatePicker();
        Label fechaLabel = new Label("Fecha :");
        VBox vboxCalendario = new VBox(10, calendario, fechaLabel);
        calendario.setOnAction(e -> {
            LocalDate fecha = calendario.getValue();
            fechaLabel.setText(fecha.toString());
        });
        vbox = new VBox(10, clienteHbox, mesaHbox, vboxCalendario);
        vbox.setAlignment(Pos.CENTER);
        hbox = new HBox(10, vbox);
        hbox.setAlignment(Pos.CENTER);
        root = new VBox(10, hbox);
        scene = new Scene(root);
    }

    public HBox clienteHbox(ClienteDAO clienteDAO) {
        Label lblNom = new Label("Nombre: " + clienteDAO.getNomCte());
        Label lblTelefono = new Label("Telefono: " + clienteDAO.getTelCte());
        Label lblEmail = new Label("Email: " + clienteDAO.getEmailCte());
        Label lblDireccion = new Label("Direccion: " + clienteDAO.getDireccion());
        VBox vbox = new VBox(lblNom, lblTelefono, lblEmail, lblDireccion);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(vbox);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    public HBox mesasHbox ()
    {
        VBox vboxMesas = new VBox(10, new Label("Mapeo de mesas"));
        List<MesaDAO> mesas = Query.obtenerMesas();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int indice = 0;
        Label seleccionada = new Label("Mesa seleccionada: ");
        Label capacidad;
        Label ocupada;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(indice < mesas.size()){
                    Button b = new Button(mesas.get(indice).getIdMesa() + "");
                    int finalIndice = indice;
                    b.setOnAction(e -> {
                        mesaSeleccionado = mesas.get(finalIndice);
                        vboxMesas.getChildren().setAll(mesasHbox().getChildren().get(0));
                    });
                    grid.add(b, j, i);
                    indice++;
                }
            }
        }
        vboxMesas.getChildren().addAll(grid, seleccionada);
        if(mesaSeleccionado != null)
        {
            ocupada = new Label("Mesa " + (mesaSeleccionado.getOcupada() ? "ocupada" : "libre"));
            capacidad = new Label("Capacidad: " + mesaSeleccionado.getCapacidad());
            seleccionada.setText("Mesa seleccionada: " + mesaSeleccionado.getIdMesa());
            vboxMesas.getChildren().addAll(capacidad, ocupada);
        }
        vboxMesas.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(vboxMesas);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        return hbox;
    }

    public VistaReservacion(ClienteDAO clienteDAO) {
        createUI(clienteDAO);
        setScene(scene);
        Metodos.configVista(this, root);
    }
}
