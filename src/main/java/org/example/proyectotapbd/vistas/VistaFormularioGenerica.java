package org.example.proyectotapbd.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.proyectotapbd.utils.modelos.DAO;
import org.example.proyectotapbd.utils.modelos.Metodos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class VistaFormularioGenerica<T extends DAO<T>> extends Stage {

    private T dao;
    private TableView<T> tabla;
    private VBox root, vbox;
    private Scene escena;
    private HashMap<String, TextField> camposTexto = new HashMap<>();

    public VistaFormularioGenerica(TableView<T> tabla, T objetoExistente) {
        this.tabla = tabla;

        try {
            this.dao = (objetoExistente != null) ? objetoExistente : (T) objetoExistente.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        crearUI();
        if (objetoExistente != null) {
            llenarCamposDesdeObjeto();
        }

        this.setTitle("Formulario de " + dao.getClass().getSimpleName());
        this.setScene(escena);
        Metodos.configVista(this, root);
    }

    private void crearUI() {
        VBox vboxLabels = new VBox(13.5);
        vboxLabels.setAlignment(Pos.TOP_CENTER);
        VBox vboxFields = new VBox(5);

        for (Field field : dao.getClass().getDeclaredFields()) {
            String nombre = field.getName();

            if (nombre.toLowerCase().contains("id")) continue;
            Label label = new Label(capitalize(nombre) + ":");
            label.setTextAlignment(TextAlignment.LEFT);
            TextField txt = new TextField();

            vboxLabels.getChildren().add(label);
            vboxFields.getChildren().add(txt);

            camposTexto.put(nombre, txt);
        }

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> guardarDatos());

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> this.close());

        HBox hboxBotones = new HBox(10, btnGuardar, btnCancelar);
        hboxBotones.setAlignment(Pos.CENTER);

        vboxFields.getChildren().add(hboxBotones);

        HBox hbox = new HBox(10, vboxLabels, vboxFields);
        hbox.setAlignment(Pos.CENTER);

        vbox = new VBox(20, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        root = new VBox(10, vbox);
        escena = new Scene(root);
    }

    private void llenarCamposDesdeObjeto() {
        for (Field field : dao.getClass().getDeclaredFields()) {
            String nombre = field.getName();
            if (camposTexto.containsKey(nombre)) {
                try {
                    String getter = "get" + capitalize(nombre);
                    Method metodo = dao.getClass().getMethod(getter);
                    Object valor = metodo.invoke(dao);
                    camposTexto.get(nombre).setText(valor != null ? valor.toString() : "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void guardarDatos() {
        try {
            for (String nombre : camposTexto.keySet()) {
                String texto = camposTexto.get(nombre).getText();
                String setter = "set" + capitalize(nombre);
                Field campo = dao.getClass().getDeclaredField(nombre);
                Method metodoSet = dao.getClass().getMethod(setter, campo.getType());

                if (campo.getType() == int.class || campo.getType() == Integer.class)
                    metodoSet.invoke(dao, Integer.parseInt(texto));
                else
                    metodoSet.invoke(dao, texto);
            }

            Method metodoId = dao.getClass().getMethod("getId" + dao.getSufijo());
            int id = (int) metodoId.invoke(dao);

            if (id > 0) {
                dao.UPDATE();
            } else {
                dao.INSERT();
            }

            tabla.setItems(dao.SELECT());
            tabla.refresh();
            new VentanaError("Registro guardado correctamente");
            this.close();

        } catch (Exception e) {
            e.printStackTrace();
            new VentanaError("Error al guardar el registro");
        }
    }

    private String capitalize(String str) {
        if (str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
