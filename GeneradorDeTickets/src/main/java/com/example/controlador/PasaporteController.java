package com.example.controlador;

import com.example.App;
import com.example.modelo.Pasaporte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class PasaporteController {
    @FXML
    private TextArea Pasaporte;

    @FXML
    private void initialize() {
        // Formateador para permitir solo caracteres alfanuméricos y espacios
        Pasaporte.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().matches("[a-zA-Z0-9 ]*")) {
                return change;
            } else {
                return null;
            }
        }));
    }

    @FXML
    private void verificarPasaporte(ActionEvent event) {
        String pasaporteIngresado = Pasaporte.getText().trim();

        if (pasaporteIngresado.isEmpty()) {
            mostrarError("Campo vacío", "Debe ingresar un número de pasaporte.");
            return;
        }

        // Validación básica: Longitud típica de un pasaporte (varía por país, ejemplo: 6-12 caracteres)
        if (pasaporteIngresado.length() >= 6 && pasaporteIngresado.length() <= 12) {
            abrirVentanaChoose(event);
        } else {
            mostrarError("Pasaporte inválido", "El número de pasaporte debe tener entre 6 y 12 caracteres.");
        }
    }

    private void abrirVentanaChoose(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            App.mostrarVentanaChoose(currentStage);
        } catch (Exception e) {
            mostrarError("Error", "No se pudo abrir la ventana: " + e.getMessage());
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
}