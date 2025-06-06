package org.example.proyectotapbd.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.proyectotapbd.componentes.ControladorProductos;
import org.example.proyectotapbd.componentes.TarjetaProducto;
import org.example.proyectotapbd.modelos.*;
import org.example.proyectotapbd.utils.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VistaEmpleado extends Stage {
    VBox root;
    Scene scene;
    double subtotal;
    List<ProductoDAO> productos;
    List<Integer> cantidades;
    ProductoDAO productoSeleccionado;
    ClienteDAO clienteSeleccionado;
    HBox hboxOrden;
    MesaDAO mesaSeleccionado;
    EmpleadoDAO empleadoSeleccionado;

    void createUI(EmpleadoDAO empleado)
    {
        subtotal = 0.0;
        productos = new ArrayList<>();
        cantidades = new ArrayList<>();
        productoSeleccionado = null;
        mesaSeleccionado = null;
        HBox hboxEmpleado = empleado(empleado);
        HBox hboxMesas = mesas();
        HBox hboxClientes = cliente();
        VBox vboxMesasCliente = new VBox(hboxMesas, hboxClientes);
        HBox hboxProductos = productos();
        HBox hboxIngredientes = ingredientes();
        hboxOrden = orden();
        HBox hboxCategorias = categorias(hboxProductos, hboxIngredientes);
        VBox vboxCategoriaIngredientes = new VBox(hboxIngredientes, hboxCategorias);
        HBox hboxOrdenIzquierda = new HBox(vboxCategoriaIngredientes, hboxOrden);
        VBox vboxProductosAbajo = new VBox(hboxProductos, hboxOrdenIzquierda);
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
        Label labelTurno = new Label(turno.getDescTurno() + " " + turno.getHoraInicio() + " - " + turno.getHoraFin());
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
                        vboxMesas.getChildren().setAll(mesas().getChildren().get(0));
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
                      clienteSeleccionado = cliente;
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
                        productoSeleccionado = null;
                        hboxProductos.getChildren().clear();
                        hboxProductos.getChildren().add(new Label("Productos para categoría: " + listaCategorias.get(finalIndice).getNombreCategoria()));
                        ControladorProductos controlador = new ControladorProductos();
                        hboxProductos.getChildren().add(controlador);
                        Button guardarPedido = new Button("Guardar");
                        guardarPedido.setOnAction(e -> {
                            if(productoSeleccionado == null ||  controlador.cantidad.getText().equals("0")){
                                Alert alerta = new Alert(Alert.AlertType.ERROR, "Selecciona un producto primero!");
                                alerta.showAndWait();
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirma con el cliente que" +
                                        " el pedido es correcto!");
                                alert.showAndWait().ifPresent(response -> {
                                    if(response == ButtonType.OK){
                                        int numero = Integer.parseInt(controlador.cantidad.getText());
                                        System.out.println("Cantidad: " + controlador.cantidad.getText());
                                        productos.add(productoSeleccionado);
                                        System.out.println("Producto: " + productoSeleccionado.getNomProd());
                                        cantidades.add(numero);
                                        subtotal = subtotal + productoSeleccionado.getPrecio() * numero;
                                        System.out.println("Subtotal: " + productoSeleccionado.getPrecio() * numero);
                                        productoSeleccionado = null;
                                        hboxOrden.getChildren().clear();
                                        hboxOrden.getChildren().setAll(orden().getChildren());
                                    }
                                });
                            }
                        });
                        System.out.println("SE PRESIONÓ EL BOTÓN");
                        List<ProductoDAO> listaProductos = Query.obtenerProductosPorCategoria(listaCategorias.get(finalIndice).getIdCategoria());
                        for (ProductoDAO producto : listaProductos) {
                            List<InsumoDAO> listaIngredientes = Query.obtenerIngredientes(producto.getIdProd());
                            TarjetaProducto prod = new TarjetaProducto(producto);
                            hboxProductos.getChildren().addAll(prod);
                            prod.setOnMouseClicked(e -> {
                                controlador.cantidad.setText("0");
                                productoSeleccionado = producto;
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
                        hboxProductos.getChildren().addAll(guardarPedido);
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

    public HBox orden(){
        Label titulo = new Label("Orden");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(new Label("Cantidad"), 0, 0);
        grid.add(new Label("Producto"), 1, 0);
        grid.add(new Label("Subtotal"), 2, 0);
        int indice = 1;
        double total = 0;
        VBox hboxbtntotal = new VBox();
        for(ProductoDAO producto : productos){
            grid.add(new Label(cantidades.get(indice - 1).toString()), 0, indice);
            grid.add(new Label(producto.getNomProd()), 1, indice);
            grid.add(new Label("$" + (cantidades.get(indice - 1) * producto.getPrecio())), 2, indice);
            total = total + producto.getPrecio() * cantidades.get(indice - 1);
            indice++;
        }
        Label totalLabel = new Label("Total: $" + total);
        Button btnOrden = new Button("Procesar orden");
        btnOrden.setOnAction(e -> {
            if(clienteSeleccionado == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Verifica que hayas puesto el Cliente");
                alert.showAndWait();
                return;
            }
            if(mesaSeleccionado == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Verifica que hayas puesto la Mesa");
                alert.showAndWait();
                return;
            }
            if(mesaSeleccionado.getOcupada())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Esta mesa ya esta ocupada");
                alert.showAndWait();
                return;
            }
            if(productos.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Verifica que hayas añadido los productos");
                alert.showAndWait();
                return;
            }
            try{
                String aux = new String();
                int j = 0;
                for(ProductoDAO producto : productos){
                    aux = aux + cantidades.get(j);
                    aux = aux + " - " +  producto.getNomProd();
                    aux = aux + " - " + producto.getPrecio() * cantidades.get(j);
                    aux = aux + "\n";
                    j++;
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText("Confirma que el pedido es correcto: ");
                alert.setContentText("Cliente: " + clienteSeleccionado.getNomCte() + "\n" +
                                    "Empleado: " + empleadoSeleccionado.getNomEmp() + "\n" +
                                    "Mesa: " + mesaSeleccionado.getIdMesa() + " capadidad: " + mesaSeleccionado.getCapacidad() + "\n" +
                                    "Cantidad Producto Subtotal\n" + aux +
                                    "Total: " + subtotal
                );
                alert.showAndWait().ifPresent(response -> {
                    if(response == ButtonType.OK){
                        OrdenDAO orden = new OrdenDAO();
                        orden.setIdCte(clienteSeleccionado.getIdCte());
                        orden.setIdMesa(mesaSeleccionado.getIdMesa());
                        orden.setIdEmp(empleadoSeleccionado.getIdEmp());
                        orden.setTotal(subtotal);
                        LocalDateTime fechaHora = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String fechaFormateada = fechaHora.format(formatter);
                        orden.setFecha(fechaFormateada);
                        orden.INSERT();

                        int idOrden = Query.obtenerIdOrden(orden.getIdEmp(), orden.getIdCte(), orden.getIdMesa(), orden.getFecha(), orden.getTotal());
                        ContieneDAO contiene = new ContieneDAO();
                        contiene.setIdOrd(idOrden);
                        int i = 0;
                        for(ProductoDAO producto : productos){
                            contiene.setIdProd(producto.getIdProd());
                            contiene.setCantidad(cantidades.get(i));
                            contiene.INSERT();
                            i++;
                        }
                        mesaSeleccionado.setOcupada(true);
                        mesaSeleccionado.UPDATE();

                        mesaSeleccionado = null;
                        clienteSeleccionado = null;
                        productoSeleccionado = null;
                        subtotal = 0.0;
                        productos.clear();
                        cantidades.clear();
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Registro realizado correctamente");
                        a.showAndWait().ifPresent(respuesta -> {
                            if(respuesta == ButtonType.OK){
                                new VistaEmpleado(empleadoSeleccionado);
                                this.close();
                            }
                        });
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        hboxbtntotal.getChildren().addAll(totalLabel, btnOrden);
        hboxbtntotal.setAlignment(Pos.BOTTOM_CENTER);
        VBox general = new VBox(titulo, grid, hboxbtntotal);
        return new HBox(general);

    }

    public VistaEmpleado(EmpleadoDAO empleado) {
        this.empleadoSeleccionado = empleado;
            createUI(empleado);
        this.setScene(scene);
        Metodos.configVista(this, root);
    }
}
