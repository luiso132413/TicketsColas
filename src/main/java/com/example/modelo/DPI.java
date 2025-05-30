package com.example.modelo;

import com.example.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.io.IOException;

public class DPI {
    @FXML
    private TextArea DPI;
    @FXML
    private Button btnAceptar;

    public void initialize() {
        DPI.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));
    }

    @FXML
    public void verificarDPI() {
        String dpiIngresado = DPI.getText();

        if (dpiIngresado.length() == 13) {
            abrirVentanaChoose();
        } else {
            mostrarError("DPI inválido", "Debe ingresar un DPI válido de 13 dígitos.");
        }
    }

    private void abrirVentanaChoose() {
        try {
            Stage currentStage = (Stage) btnAceptar.getScene().getWindow();
            App.mostrarVentanaChoose(currentStage);
            currentStage.close();
        } catch (IOException e) {
            mostrarError("Error", "No se pudo abrir la ventana de selección: " + e.getMessage());
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