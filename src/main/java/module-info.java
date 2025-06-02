module org.example.proyectotapbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;


    opens org.example.proyectotapbd to javafx.fxml;
    exports org.example.proyectotapbd;
}