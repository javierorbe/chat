package com.javierorbe.chat.client.gui;

import com.javierorbe.chat.client.net.PacketHandler;
import com.javierorbe.chat.client.net.SocketClient;
import com.javierorbe.chat.common.net.DisconnectPacket;
import com.javierorbe.chat.common.net.MessagePacket;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainFrame extends JFrame {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);

    public MainFrame(SocketClient socketClient) {
        super("Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeel();
        setLayout(new BorderLayout());

        MainPanel panel = new MainPanel(msg -> socketClient.send(new MessagePacket("randomUsername", msg)));
        add(panel, BorderLayout.CENTER);

        PacketHandler.INSTANCE.registerListener(MessagePacket.class, packet ->
                panel.addChatLine(packet.getUsername() + ": " + packet.getContent()));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                socketClient.send(new DisconnectPacket());
            }
        });

        pack();
        setVisible(true);
    }

    private static void setDefaultLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            LOGGER.error("Could not load the default look and feel.", e);
        }
    }
}
