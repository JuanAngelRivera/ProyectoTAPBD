package org.example.proyectotapbd.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.proyectotapbd.componentes.TarjetaEmpleado;
import org.example.proyectotapbd.utils.Claves;
import org.example.proyectotapbd.utils.Query;
import org.example.proyectotapbd.modelos.EmpleadoDAO;
import org.example.proyectotapbd.modelos.Metodos;

import java.util.List;

public class Login extends Stage {
    Scene scene;
    HBox hbox, hboxBotones;
    Button ok, cancelar;
    VBox vbox, root;
    public void createUI(boolean admin){

        Label lblAdmin = new Label(admin ? "Introduce la contraseña para entrar" : "Selecciona tu tarjeta de empleado para comenzar tu turno:");
       if(admin)
       {
           TextField tfAdmin = new TextField();
           TextField ejemplo = new TextField("'adminContraseña'");
           ejemplo.setEditable(false);
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
                   }
           );
           cancelar = new Button("Cancelar");
           cancelar.setOnAction(e -> this.close());
           hboxBotones = new HBox(10, ok, cancelar);
           hboxBotones.setAlignment(Pos.CENTER);
           vbox = new VBox(10, lblAdmin, tfAdmin, hboxBotones, ejemplo);
       }
       else
       {
           vbox = new VBox(10, lblAdmin);
           List<EmpleadoDAO> claves = Query.getEmpleados();
           if (claves != null) {
               for(EmpleadoDAO clave : claves){
                   TarjetaEmpleado tarjeta = new TarjetaEmpleado(clave);
                   vbox.getChildren().add(tarjeta);
               }
           }
       }

        vbox.setAlignment(Pos.CENTER);
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
