package com.example.controlador;

import com.example.App;
import com.example.modelo.NIT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class NITController {
    @FXML
    private TextArea NIT;

    @FXML
    private void initialize() {
        NIT.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().matches("[a-zA-Z0-9 ]*")) {
                return change;
            } else {
                return null;
            }
        }));
    }

    @FXML
    private void verificarNIT(ActionEvent event) {
        String nitIngresado = NIT.getText().trim();

        if (nitIngresado.isEmpty()) {
            mostrarError("Campo vacío", "Debe ingresar un NIT.");
            return;
        }

        if (nitIngresado.length() >= 8 && nitIngresado.length() <= 12) {
            abrirVentanaChoose(event);
        } else {
            mostrarError("NIT inválido", "El NIT debe tener entre 8 y 12 caracteres.");
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