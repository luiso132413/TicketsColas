package com.example.servicio;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.PrintWriter;
import java.net.Socket;

public class Generador {
    private static final String SERVER_HOST = "192.168.40.61";
    private static final int SERVER_PORT = 54321;

    // Contadores atómicos para cada tipo de ticket
    private static final java.util.concurrent.atomic.AtomicInteger contadorCaja = new java.util.concurrent.atomic.AtomicInteger(1);
    private static final java.util.concurrent.atomic.AtomicInteger contadorServicio = new java.util.concurrent.atomic.AtomicInteger(1);

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

    private void generarTicketCaja() {
        String numeroTicket = "C" + String.format("%04d", contadorCaja.getAndIncrement());
        enviarTicket("OPERACIONES", "Ticket para operaciones en caja: " + numeroTicket);
        mostrarAlerta("Ticket para Caja", "Ticket generado: " + numeroTicket, Alert.AlertType.INFORMATION);
    }

    private void generarTicketServicio() {
        String numeroTicket = "S" + String.format("%04d", contadorServicio.getAndIncrement());
        enviarTicket("SERVICIO", "Ticket para servicio al cliente: " + numeroTicket);
        mostrarAlerta("Ticket para Servicio", "Ticket generado: " + numeroTicket, Alert.AlertType.INFORMATION);
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

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
