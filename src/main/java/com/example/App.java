package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        cargarMenuPrincipal();
    }

    private void cargarMenuPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Inicial.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Generador de Tickets - Menú Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void mostrarValidacionDPI(Stage ownerStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/DPI.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Validación DPI");
        stage.initOwner(ownerStage);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void mostrarValidacionPasaporte(Stage ownerStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/Pasaporte.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Validación Pasaporte");
        stage.initOwner(ownerStage);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void mostrarValidacionNIT(Stage ownerStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/NIT.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Validación NIT");
        stage.initOwner(ownerStage);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void mostrarVentanaChoose(Stage ownerStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/Choose.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Selección de Opciones");
        stage.initOwner(ownerStage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}