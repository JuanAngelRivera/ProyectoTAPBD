package org.example.proyectotapbd.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.proyectotapbd.modelos.ProductoDAO;

public class TarjetaProducto extends VBox {
    public TarjetaProducto(ProductoDAO productoDAO) {
        try{
            ProductoDAO producto = productoDAO;
            ImageView imagen = new ImageView(getClass().getResource(productoDAO.getImagen()).toExternalForm());
            imagen.setFitHeight(50);
            imagen.setFitWidth(50);
            Label titulo = new Label(productoDAO.getNomProd());
            Label precio = new Label("$" + productoDAO.getPrecio());
            VBox vbox = new VBox(5, imagen, titulo, precio);
            vbox.setAlignment(Pos.CENTER);
            this.getChildren().add(vbox);
            System.out.println("SE CREÓ LA TARJETA PRODUCTO");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
