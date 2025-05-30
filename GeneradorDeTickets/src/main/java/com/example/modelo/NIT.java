package com.example.modelo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;

public class NIT {
    @FXML
    private TextArea NIT;

    public void initialize() {
        NIT.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().matches("[a-zA-Z0-9 ]*")) {
                return change;
            } else {
                return null;
            }
        }));
    }
}