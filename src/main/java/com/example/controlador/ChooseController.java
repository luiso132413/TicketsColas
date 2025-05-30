package com.example.controlador;

import com.example.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ChooseController {

    @FXML
    private void abrirGeneradorCaja(ActionEvent event) {
        try {
            int randomNumber = 1000 + (int)(Math.random() * 9000);
            String mensaje = "C" + randomNumber + "\n";

            Cliente cliente = new Cliente("172.25.210.81", 6000, mensaje);
            new Thread(cliente).start();

            mostrarInformacion("Ticket Generado", "Se ha generado el ticket: " + mensaje.trim());

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            mostrarError("Error al generar el ticket", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirServicioCliente(ActionEvent event) {
        try {
            int randomNumber = 1000 + (int)(Math.random() * 9000);
            String mensaje = "S" + randomNumber + "\n";

            Cliente cliente = new Cliente("172.25.210.81", 6000, mensaje);
            new Thread(cliente).start();

            mostrarInformacion("Ticket Generado", "Se ha generado el ticket: " + mensaje.trim());

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            mostrarError("Error al generar el ticket", e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}