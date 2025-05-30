package com.example.servicio;

import com.example.modelo.Cliente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.Observable;
import java.util.Observer;

public class GeneradorCaja implements Observer {

    @FXML
    private TextArea txtTexto;
    private Server server;

    @FXML
    public void initialize() {
        server = new Server(15000);
        server.addObserver(this);
        new Thread(server).start();
    }

    @FXML
    private void btnEnviarAction() {
        int randomNumber = 1000 + (int)(Math.random() * 9000);
        String mensaje = "C" + randomNumber + "\n";

        Cliente c = new Cliente("172.25.210.81", 6000, mensaje);
        new Thread(c).start();
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> txtTexto.appendText((String) arg));
    }
}