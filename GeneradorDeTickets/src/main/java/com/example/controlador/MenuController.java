// MenuController.java
package com.example.controlador;

import com.example.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private void irA_DPI(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        App.mostrarValidacionDPI(currentStage);
    }

    @FXML
    private void irA_Pasaporte(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        App.mostrarValidacionPasaporte(currentStage);
    }

    @FXML
    private void irA_NIT(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        App.mostrarValidacionNIT(currentStage);
    }
}