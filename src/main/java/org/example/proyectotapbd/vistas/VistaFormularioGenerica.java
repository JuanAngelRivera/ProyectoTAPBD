package org.example.proyectotapbd.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.DAO;
import org.example.proyectotapbd.componentes.VentanaError;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class VistaFormularioGenerica<T extends DAO<T>> extends Stage {

    private T dao;
    private TableView<T> tabla;
    private VBox root;
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
        this.show();
    }

    private void crearUI() {
        VBox vboxLabels = new VBox(10);
        VBox vboxFields = new VBox(10);

        for (Field field : dao.getClass().getDeclaredFields()) {
            String nombre = field.getName();

            if (nombre.toLowerCase().contains("id")) continue; // Ignorar campos ID

            Label label = new Label(capitalize(nombre) + ":");
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

        root = new VBox(20, hbox);
        root.setAlignment(Pos.CENTER);
        escena = new Scene(root, 400, 300);
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

            Method metodoId = dao.getClass().getMethod("getId" + dao.getClass().getSimpleName().replace("DAO", ""));
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
