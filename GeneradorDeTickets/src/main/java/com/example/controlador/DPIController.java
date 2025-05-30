package com.example.controlador;

import com.example.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DPIController {
    @FXML
    private TextArea DPI;

    @FXML
    private void verificarDPI(ActionEvent event) {
        String dpiIngresado = DPI.getText();

        if (dpiIngresado.length() == 13) {
            abrirVentanaChoose(event);
        } else {
            mostrarError("DPI inválido", "Debe ingresar un DPI válido de 13 dígitos.");
        }
    }

    private void abrirVentanaChoose(ActionEvent event) {
        try {
            // Cerrar ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Crear y mostrar nueva ventana
            Stage newStage = new Stage();
            ChooseController.mostrarVentana(newStage);
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