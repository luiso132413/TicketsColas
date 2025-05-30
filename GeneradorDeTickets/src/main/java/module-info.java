module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    // Abre los paquetes necesarios para JavaFX
    opens com.example to javafx.fxml;
    opens com.example.controlador to javafx.fxml;
    opens com.example.modelo to javafx.fxml;
    opens com.example.servicio to javafx.fxml;

    // Exporta los paquetes necesarios
    exports com.example;
    exports com.example.controlador;
    exports com.example.modelo;
    exports com.example.servicio;
}