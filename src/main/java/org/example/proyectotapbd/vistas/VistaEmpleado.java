package org.example.proyectotapbd.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.proyectotapbd.componentes.ControladorProductos;
import org.example.proyectotapbd.componentes.TarjetaProducto;
import org.example.proyectotapbd.modelos.*;
import org.example.proyectotapbd.utils.Query;

import java.sql.SQLOutput;
import java.util.List;

public class VistaEmpleado extends Stage {
    VBox root;
    Scene scene;
    void createUI(EmpleadoDAO empleado)
    {
        HBox hboxEmpleado = empleado(empleado);
        HBox hboxMesas = mesas();
        HBox hboxClientes = cliente();
        VBox vboxMesasCliente = new VBox(hboxMesas, hboxClientes);
        HBox hboxProductos = productos();
        HBox hboxIngredientes = ingredientes();
        HBox hboxCategorias = categorias(hboxProductos, hboxIngredientes);
        VBox vboxProductosAbajo = new VBox(hboxProductos, hboxIngredientes, hboxCategorias);
        HBox hboxMesaClienteDerecha = new HBox(vboxMesasCliente, vboxProductosAbajo);
        VBox vboxEmpAbajo = new VBox(hboxEmpleado, hboxMesaClienteDerecha);
        vboxEmpAbajo.setPadding(new Insets(10));
        root = new VBox(vboxEmpAbajo);
        scene = new Scene(root);
    }
    public HBox empleado(EmpleadoDAO empleado){
        Label labelNom = new Label(empleado.getNomEmp() + " (" + empleado.getPuestoEmp() + ")");
        Label labelRFC = new Label(empleado.getRfc());
        TurnoDAO turno = Query.obtenerTurno(empleado.getIdTurno());
        Label labelTurno = new Label(turno.getDescripcion() + " " + turno.getHoraInicio() + " - " + turno.getHoraFin());
        HBox hbox = new HBox(10, labelNom, labelRFC, labelTurno);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    public HBox mesas ()
    {
        VBox vboxMesas = new VBox(10, new Label("Mapeo de mesas"));
        List<MesaDAO> mesas = Query.obtenerMesas();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int indice = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(indice < mesas.size()){
                    Button b = new Button(mesas.get(indice).getIdMesa() + "");
                    grid.add(b, j, i);
                    indice++;
                }
            }
        }
        vboxMesas.getChildren().add(grid);
        vboxMesas.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(vboxMesas);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        return hbox;
    }

    public HBox cliente ()
    {
        Label label = new Label("Información del cliente");
        VBox vbox = new VBox(label);
      ComboBox clientes = new ComboBox();
      List<ClienteDAO> listaClientes = Query.obtenerClientes();
      for (ClienteDAO cliente : listaClientes) {
          clientes.getItems().add(cliente.getNomCte());
      }
      vbox.getChildren().add(clientes);
      clientes.setOnAction(event -> {
          vbox.getChildren().clear();
          vbox.getChildren().addAll(label, clientes);
          for (ClienteDAO cliente : listaClientes) {
                  if(cliente.getNomCte().equals(clientes.getValue())){
                      Label lblNom = new Label(cliente.getNomCte());
                      Label lblTel = new Label(cliente.getTelCte());
                      Label lblEmail = new Label(cliente.getEmailCte());
                      vbox.getChildren().addAll(lblNom, lblTel, lblEmail);
                  }
          }
      });
      return new HBox(10, vbox);
    };

    public HBox productos()
    {
        return new HBox(10, new Label("Productos:"));
    }

    public HBox categorias(HBox hboxProductos, HBox hboxIngredientes){
        Label label = new Label("Categorias");
        VBox vbox = new VBox(label);
        List<CategoriaDAO> listaCategorias = Query.obtenerCategorias();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int indice = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(indice < listaCategorias.size()){
                    Button b = new Button(listaCategorias.get(indice).getNombreCategoria());
                    int finalIndice = indice;
                    b.setOnAction(event -> {
                        hboxProductos.getChildren().clear();
                        hboxProductos.getChildren().add(new Label("Productos para categoría: " + listaCategorias.get(finalIndice).getNombreCategoria()));
                        hboxProductos.getChildren().add(new ControladorProductos());
                        System.out.println("SE PRESIONÓ EL BOTÓN");
                        List<ProductoDAO> listaProductos = Query.obtenerProductosPorCategoria(listaCategorias.get(finalIndice).getIdCategoria());
                        for (ProductoDAO producto : listaProductos) {
                            List<InsumoDAO> listaIngredientes = Query.obtenerIngredientes(producto.getIdProd());
                            TarjetaProducto prod = new TarjetaProducto(producto);
                            hboxProductos.getChildren().add(prod);
                            prod.setOnMouseClicked(e -> {
                                hboxIngredientes.getChildren().clear();
                                VBox vboxIngredientes = new VBox(new Label("Ingredientes para " + producto.getNomProd()));
                                for (InsumoDAO insumo : listaIngredientes) {
                                    int cantidad = Query.obtenerCantidadIngrediente(producto.getIdProd(), insumo.getIdIns());
                                    Label lblCantidad = new Label(cantidad + "");
                                    lblCantidad.setTextAlignment(TextAlignment.LEFT);
                                    Label lblNombre = new Label(insumo.getNomIns());
                                    lblNombre.setTextAlignment(TextAlignment.LEFT);
                                    HBox cantidadNombre = new HBox(5, lblCantidad, lblNombre);
                                    cantidadNombre.setAlignment(Pos.CENTER);
                                    vboxIngredientes.getChildren().add(cantidadNombre);
                                }
                                hboxIngredientes.getChildren().addAll(vboxIngredientes);
                            });
                        }
                    });
                    grid.add(b, j, i);
                    indice++;
                }
            }
        }
        vbox.getChildren().addAll(grid);
        return new HBox(10, vbox);
    }

    public HBox ingredientes()
    {
        return new HBox(10, new Label("Ingredientes: "));
    }
    public VistaEmpleado(EmpleadoDAO empleado) {
        createUI(empleado);
        this.setScene(scene);
        Metodos.configVista(this, root);
    }
}
