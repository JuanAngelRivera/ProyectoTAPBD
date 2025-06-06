package org.example.proyectotapbd.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.proyectotapbd.utils.Query;
import org.example.proyectotapbd.modelos.ClienteDAO;
import org.example.proyectotapbd.modelos.Metodos;

public class Bienvenida extends Stage {
    VBox vb, root;
    HBox hb;
    Scene scene;

    void createUI(){
        Label titulo = new Label("Bienvenido\n¿Es tu primera vez en el restaurante?");
        Button botonSi = new Button("Sí, es mi primera vez");
        botonSi.setOnAction(e -> accionBoton(true));
        Button botonNo = new Button("No, ya he venido antes");
        botonNo.setOnAction(e -> accionBoton(false));
        vb = new VBox(10, titulo, botonSi, botonNo);
        vb.setAlignment(Pos.CENTER);
        hb = new HBox(vb);
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(10));
        root = new VBox(hb);
        scene = new Scene(root);
    }

    public void accionBoton(boolean seleccion){

        if(seleccion){
            new VistaFormularioGenerica<>(null, new ClienteDAO());
        }
        else {
            Label label = new Label("Introduce el correo electrónico\ncon el que te registraste:");
            label.setTextAlignment(TextAlignment.CENTER);
            TextField tf = new TextField();
            Button iniciarSesion = new Button("Iniciar sesión");
            TextField ejemplo = new TextField("'juangelrt24@gmail.com'");
            ejemplo.setEditable(false);
            iniciarSesion.setOnAction(e -> {
                ClienteDAO cliente = Query.sesionCliente(tf.getText());
                if(cliente == null)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("No se encontró un cliente con ese correo\nTrata de reescribirlo o registrate con otro");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.CLOSE) {
                            this.close();
                        }
                    });
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("Eres " + cliente.getNomCte() + "?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            new VistaReservacion(cliente);
                            this.close();
                        }
                    });
                }
            });
            Button cancelar = new Button("Cancelar");
            cancelar.setOnAction(e -> this.close());
            HBox hboxBtn = new HBox(10, iniciarSesion, cancelar);
            hboxBtn.setAlignment(Pos.CENTER);
            vb.getChildren().clear();
            vb.getChildren().addAll(
                    label,
                    tf,
                    hboxBtn,
                    ejemplo
            );
        }
    }

    public Bienvenida(){
        createUI();
        setTitle("Bienvenido");
        setScene(scene);
        Metodos.configVista(this, root);
    }
}
