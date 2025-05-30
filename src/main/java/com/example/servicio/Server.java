package com.example.servicio;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Observable implements Runnable {

    private int puerto;
    private volatile boolean running = true;

    public Server(int puerto) {
        this.puerto = puerto;
    }

    public void stopServer() {
        this.running = false;
    }

    @Override
    public void run() {
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");

            while (running) {
                try (Socket sc = servidor.accept();
                     DataInputStream in = new DataInputStream(sc.getInputStream())) {

                    System.out.println("Cliente conectado");
                    String mensaje = in.readUTF();
                    System.out.println(mensaje);

                    this.setChanged();
                    this.notifyObservers(mensaje);
                    this.clearChanged();

                    System.out.println("Cliente desconectado");
                } catch (IOException ex) {
                    if (running) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Servidor detenido");
        }
    }
}