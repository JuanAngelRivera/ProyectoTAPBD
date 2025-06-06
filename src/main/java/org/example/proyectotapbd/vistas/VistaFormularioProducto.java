package org.example.proyectotapbd.vistas;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.proyectotapbd.modelos.*;
import org.example.proyectotapbd.utils.Query;
import org.w3c.dom.Text;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class VistaFormularioProducto extends Stage {
    VBox root, vbox;
    HBox hbox;
    Scene scene;
    Path pathImagen;

    void createUI() {
        pathImagen = null;
        Label titulo = new Label("Formulario de Producto");

        Label nombreLbl = new Label("Nombre: ");
        TextField txtNombre = new TextField();
        HBox nombreHbox = new HBox(nombreLbl, txtNombre);

        Label precioLbl = new Label("Precio: ");
        TextField txtPrecio = new TextField();
        HBox precioHbox = new HBox(precioLbl, txtPrecio);

        Label costoLbl = new Label("Costo: ");
        TextField txtCosto = new TextField();
        HBox costoHbox = new HBox(costoLbl, txtCosto);

        Label categoriaLbl = new Label("Categoria: ");
        ComboBox comboBoxCat = new ComboBox();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ObservableList<CategoriaDAO> listaCategorias = categoriaDAO.SELECT();
        comboBoxCat.setItems(listaCategorias);
        HBox categoriaHbox = new HBox(categoriaLbl, comboBoxCat);

        Label imagenLbl = new Label("Imagen: ");
        Button btnImagen = new Button("Seleccionar");
        HBox imagenHbox = new HBox(imagenLbl, btnImagen);
        Label nombreImagenLbl = new Label("Nombre Imagen: ");
        VBox izquierda = new VBox(10, nombreHbox, precioHbox, costoHbox, categoriaHbox, imagenHbox, nombreImagenLbl);

        Button btnGuardar = new Button("Guardar");

        btnImagen.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
            fileChooser.setTitle("Seleccione una imagen");

            File selectedFile = fileChooser.showOpenDialog(this);
            if(selectedFile != null){
                try{
                    Path appPath = Paths.get(System.getProperty("user.dir")); // donde se ejecuta el JAR
                    Path carpetaImagenes = appPath.resolve("imagenes");
                    Files.createDirectories(carpetaImagenes);
                    pathImagen = carpetaImagenes.resolve(selectedFile.getName());
                    System.out.println(pathImagen);
                    String nombreImagen = selectedFile.getName();
                    nombreImagenLbl.setText("Nombre Imagen: " + nombreImagen);
                    Files.copy(selectedFile.toPath(), pathImagen, StandardCopyOption.REPLACE_EXISTING);
                    Image img = new Image(pathImagen.toUri().toString());
                    ImageView imagen = new ImageView(img);
                    imagen = new ImageView(img);
                    imagen.setFitHeight(200);
                    imagen.setFitWidth(200);
                    izquierda.getChildren().add(imagen);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        InsumoDAO insumoDAO = new InsumoDAO();
        ObservableList<InsumoDAO> listaInsumos = insumoDAO.SELECT();
        VBox derecha = new VBox(5, new Label("Selecciona todos los ingredientes del producto: "));
        HBox derechaHbox = new HBox();
        VBox checkboxes = new VBox(13);
        VBox textFields = new VBox(5);

        for(InsumoDAO insumo : listaInsumos){
            CheckBox checkBox = new CheckBox(insumo.getNomIns());
            checkBox.setUserData(insumo);
            TextField textField = new TextField();
            checkboxes.getChildren().add(checkBox);
            textFields.getChildren().add(textField);
        }
        derechaHbox.getChildren().addAll(checkboxes, textFields);
        derecha.getChildren().addAll(derechaHbox, btnGuardar);
        btnGuardar.setOnAction(e -> {
            List<Integer> cantidades = new ArrayList<>();
            List<InsumoDAO> insumosSeleccionados = new ArrayList<>();
            int j = 0;
            for (Node node : checkboxes.getChildren()) {

                if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                    TextField textField = (TextField) textFields.getChildren().get(j);
                    if(textField.getText().isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.ERROR, "No cuadran los ingredientes con cantidad, revisalo");
                        alert.showAndWait();
                        return;
                    }
                    InsumoDAO insumo = (InsumoDAO) checkBox.getUserData();
                    cantidades.add(Integer.parseInt(textField.getText()));
                    insumosSeleccionados.add(insumo);
                }
                j++;
            }
            CategoriaDAO seleccionadoCategoria = (CategoriaDAO) comboBoxCat.getSelectionModel().getSelectedItem();
            if(txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtCosto.getText().isEmpty()
                || insumosSeleccionados.isEmpty() || seleccionadoCategoria == null || pathImagen == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Llena todos los campos, una imagen y añade almenos un ingrediente");
                alert.showAndWait();
                return;
            }
            String aux = "";
            int indice = 0;
            for(InsumoDAO insumo : insumosSeleccionados){
                aux = aux + cantidades.get(indice).toString() + " - " + insumo.getNomIns() + "\n";
                indice++;
            }
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setHeaderText("Seguro que quieres registrar: ");
            alerta.setTitle("Confirmacion");
            alerta.setContentText("Nombre: " + txtNombre.getText() + "\nPrecio: " + txtPrecio.getText() + "\nCosto: " + txtCosto.getText()
            + "\n Ingredientes:\n" + aux + "\n");
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ProductoDAO productoDAO = new ProductoDAO();
                    productoDAO.setNomProd(txtNombre.getText());
                    productoDAO.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    productoDAO.setCosto(Double.parseDouble(txtCosto.getText()));
                    productoDAO.setIdCat(seleccionadoCategoria.getIdCategoria());
                    productoDAO.setImagen(escaparContrabarras(pathImagen.toUri().toString()));
                    productoDAO.INSERT();

                    productoDAO.setIdProd(Query.obtenerIdProducto(productoDAO.getPrecio(), productoDAO.getCosto(), productoDAO.getNomProd(),  productoDAO.getIdCat(), productoDAO.getImagen()));
                    if(productoDAO.getImagen() == null){
                        System.out.println("LA ID DEL PRODUCTO NO EXISTE");
                    }
                    IngredientesDAO ingredientesDAO = new IngredientesDAO();
                    ingredientesDAO.setIdProd(productoDAO.getIdProd());
                    int i = 0;
                    for(InsumoDAO insumo : insumosSeleccionados){
                        ingredientesDAO.setIdIns(insumo.getIdIns());
                        ingredientesDAO.setCantidad(cantidades.get(i));
                        ingredientesDAO.INSERT();
                        i++;
                    }
                    Alert alerta2 = new Alert(Alert.AlertType.INFORMATION, "Registro realizado exitosamente");
                }
            });
        });

        hbox = new HBox(10, izquierda, derecha);
        vbox = new VBox(titulo, hbox);
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        root = new VBox(vbox);
        scene = new Scene(root);
    }

    public static String escaparContrabarras(String rutaOriginal) {
        if (rutaOriginal == null) return null;
        return rutaOriginal.replace("\\", "\\\\");
    }

    public VistaFormularioProducto() {
        try{
            createUI();
            setScene(scene);
            Metodos.configVista(this, root);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
