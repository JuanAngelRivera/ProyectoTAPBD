package org.example.proyectotapbd.componentes;

import javafx.scene.control.*;
import org.example.proyectotapbd.modelos.ClientesDAO;
import org.example.proyectotapbd.modelos.DAO;
import org.example.proyectotapbd.vistas.Cliente;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ButtonCell<T> extends TableCell<T, String>
{
    Button btnCelda;
    private String strLabelBtn;

    public ButtonCell(String label, BiConsumer<TableView<T>, T> action)
    {
        strLabelBtn = label;
        btnCelda = new Button(strLabelBtn);
        btnCelda.setOnAction(actionEvent ->
        {
            T obj = getTableView().getItems().get(getIndex());
            action.accept(getTableView(), obj);
        });
    }

    @Override
    protected void updateItem(String s, boolean b)
    {
        super.updateItem(s, b);
        if( !isEmpty())
            this.setGraphic(btnCelda);
    }
}

//un proceso es un conjunto de acciones al igual que un algoritmo
//un proceso tambien es un programa corriendo en la computadora
//cuando dos procesos estan bloqueando entre ellos se lamma interbloqueo
//como de termina? uno de los procesos mueren por inanicion, cuando se habla d eun rpoceso e los soitemas operativos se
//hablan de hilos
//Tecnicas de desmacho de procesos
//quantum
//exclusion mutua