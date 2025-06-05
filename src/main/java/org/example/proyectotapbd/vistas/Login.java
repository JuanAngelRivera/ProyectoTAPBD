package org.example.proyectotapbd.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.proyectotapbd.utils.Claves;
import org.example.proyectotapbd.utils.Query;
import org.example.proyectotapbd.utils.modelos.EmpleadoDAO;
import org.example.proyectotapbd.utils.modelos.Metodos;

import java.util.List;

public class Login extends Stage {
    Scene scene;
    HBox hbox, hboxBotones;
    Button ok, cancelar;
    VBox vbox, root;
    public void createUI(boolean admin){

        Label lblAdmin = new Label("Introduce " + (admin ? "la contraseña" : "tu RFC" + " para\niniciar sesión como "
                + (admin ? "Administrador" : "Empleado")));
        TextField tfAdmin = new TextField();
        ok = new Button("Ok");
        ok.setOnAction(e -> {

            if(admin && tfAdmin.getText().equals("adminContraseña")){//admin y contraseña correcta
                new VistaAdmin();
                Claves.accesoAdmin = true;
                this.close();
            }
            else if(admin && !tfAdmin.getText().equals("adminContraseña")){//admin no contraseña correcta
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setContentText("Contraseña incorrecta");
                alerta.showAndWait().ifPresent(response -> {if(response == ButtonType.CLOSE){alerta.close();}});
            }
            else if(!admin) {//empleado contraseña correcta
                EmpleadoDAO empleado = Query.sesionEmpleado(tfAdmin.getText());
                System.out.println("BOTON OK NO ADMIN");
                if (empleado != null) {
                    new VistaEmpleado();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Clave incorrecta");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.CLOSE) {
                            alert.close();
                        }
                    });
                }
            }
        }
        );
        cancelar = new Button("Cancelar");
        cancelar.setOnAction(e -> this.close());
        hboxBotones = new HBox(10, ok, cancelar);
        hboxBotones.setAlignment(Pos.CENTER);
        vbox = new VBox(10, lblAdmin, tfAdmin, hboxBotones);
        vbox.setAlignment(Pos.CENTER);

        if(!admin){
            List<EmpleadoDAO> claves = Query.getClavesEmpleados();

            if (claves != null) {
                for(EmpleadoDAO clave : claves){
                    Label label = new Label(clave.getNomEmp() + ": ");
                    label.setAlignment(Pos.CENTER);
                    label.setTextAlignment(TextAlignment.LEFT);
                    TextField tf = new TextField(clave.getRfc());
                    HBox hboxEmp = new HBox(5, label, tf);
                    tf.setEditable(false);
                    vbox.getChildren().add(hboxEmp);
                }
            }
        }

        hbox = new HBox(vbox);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        root = new VBox(10, hbox);
        root.setPadding(new Insets(10));
        scene = new Scene(root);
        setScene(scene);
    }
    public Login(boolean admin) {
        if(admin && Claves.accesoAdmin){
            new VistaAdmin();
            this.close();
        }
        else {
            createUI(admin);
        }
        Metodos.configVista(this, root);
    }
}
