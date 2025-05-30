package com.example.modelo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BancoCliente extends Application {
    private static final String SERVER_HOST = "172.25.210.81";
    private static final int SERVER_PORT = 54321;
    public static boolean servidorDisponible = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        verificarServidor();

        if (servidorDisponible) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Inicial.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Genere su ticket");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.setResizable(false);
            primaryStage.show();
        } else {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error de conexión");
            alert.setHeaderText(null);
            alert.setContentText("El servidor no está disponible. Por favor inicie el servidor primero.");
            alert.showAndWait();
            System.exit(0);
        }
    }

    public static void verificarServidor() {
        try (java.net.Socket socket = new java.net.Socket()) {
            socket.connect(new java.net.InetSocketAddress(SERVER_HOST, SERVER_PORT), 2000);
            servidorDisponible = true;
        } catch (java.io.IOException e) {
            servidorDisponible = false;
        }
    }
}

