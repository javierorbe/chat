package com.javierorbe.chat.client;

import com.javierorbe.chat.client.gui.MainFrame;
import com.javierorbe.chat.client.net.SocketClient;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;

public class ClientMain {

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 9315;

    public static void main(String[] args) {
        SocketClient client = new SocketClient(HOSTNAME, PORT);
        Executors.newSingleThreadExecutor().submit(client);
        SwingUtilities.invokeLater(() -> new MainFrame(client));
    }
}
