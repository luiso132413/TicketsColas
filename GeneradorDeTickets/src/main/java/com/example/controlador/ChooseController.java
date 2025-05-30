package com.example.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.Socket;

public class ChooseController {
    private static final String SERVER_HOST = "172.25.210.81";
    private static final int SERVER_PORT = 54321;
    public static boolean servidorDisponible = false;

    public static void mostrarVentana(Stage stage) {
        verificarServidor();

        if (servidorDisponible) {
            try {
                // Asegúrate que la ruta del FXML es correcta
                FXMLLoader loader = new FXMLLoader(ChooseController.class.getResource("/com/example/Choose.fxml"));
                Parent root = loader.load();

                stage.setTitle("Genere su ticket");
                stage.setScene(new Scene(root, 400, 300));
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                mostrarError("Error", "No se pudo cargar la ventana: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            mostrarError("Error de conexión",
                    "El servidor no está disponible. Por favor inicie el servidor primero.");
            System.exit(0);
        }
    }

    private static void verificarServidor() {
        try (java.net.Socket socket = new java.net.Socket()) {
            socket.connect(new java.net.InetSocketAddress(SERVER_HOST, SERVER_PORT), 2000);
            servidorDisponible = true;
        } catch (java.io.IOException e) {
            servidorDisponible = false;
        }
    }

    private static void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    private Button btnCaja;

    @FXML
    private Button btnServicio;

    @FXML
    private void initialize() {
        // Configurar acciones de los botones
        btnCaja.setOnAction(event -> generarTicketCaja());
        btnServicio.setOnAction(event -> generarTicketServicio());
    }

    @FXML
    private void generarTicketCaja() {
        String numeroTicket = "C" + String.format("%04d", contadorCaja.getAndIncrement());
        enviarTicket("OPERACIONES", "Ticket para operaciones en caja: " + numeroTicket);
        mostrarAlerta("Ticket para Caja", "Ticket generado: " + numeroTicket, Alert.AlertType.INFORMATION);
    }

    @FXML
    private void generarTicketServicio() {
        String numeroTicket = "S" + String.format("%04d", contadorServicio.getAndIncrement());
        enviarTicket("SERVICIO", "Ticket para servicio al cliente: " + numeroTicket);
        mostrarAlerta("Ticket para Servicio", "Ticket generado: " + numeroTicket, Alert.AlertType.INFORMATION);
    }

    private static final java.util.concurrent.atomic.AtomicInteger contadorCaja = new java.util.concurrent.atomic.AtomicInteger(1);
    private static final java.util.concurrent.atomic.AtomicInteger contadorServicio = new java.util.concurrent.atomic.AtomicInteger(1);

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void enviarTicket(String tipo, String descripcion) {
        new Thread(() -> {
            try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                out.println("NUEVO_TICKET|" + tipo + "|" + descripcion);

            } catch (java.io.IOException e) {
                javafx.application.Platform.runLater(() -> {
                    mostrarAlerta("Error de conexión",
                            "Error al conectar con el servidor: " + e.getMessage(),
                            Alert.AlertType.ERROR);
                });
            }
        }).start();
    }
}