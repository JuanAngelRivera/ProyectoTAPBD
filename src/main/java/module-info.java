module org.example.proyectotapbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires java.compiler;


    opens org.example.proyectotapbd to javafx.fxml;
    exports org.example.proyectotapbd;
    opens org.example.proyectotapbd.utils.modelos to javafx.base;

}