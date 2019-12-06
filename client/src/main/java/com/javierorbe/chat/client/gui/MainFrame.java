package com.javierorbe.chat.client.gui;

import com.javierorbe.chat.client.net.PacketHandler;
import com.javierorbe.chat.client.net.SocketClient;
import com.javierorbe.chat.common.net.DisconnectPacket;
import com.javierorbe.chat.common.net.MessagePacket;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainFrame extends JFrame {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);

    private final String username = getRandomString();

    public MainFrame(SocketClient socketClient) {
        super("Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeel();
        setLayout(new BorderLayout());

        MainPanel panel = new MainPanel(msg -> socketClient.send(new MessagePacket(username, msg)));
        add(panel, BorderLayout.CENTER);

        PacketHandler.INSTANCE.registerListener(MessagePacket.class, packet ->
                panel.addChatLine(packet.getUsername() + ": " + packet.getContent()));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (socketClient.isRunning()) {
                    socketClient.send(new DisconnectPacket());
                }
            }
        });

        pack();
        setLocation(getCenteredLocation(this));
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

    /** Returns the center location for the component, relative to the screen size. */
    public static Point getCenteredLocation(Component component) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point(
                dimension.width / 2 - component.getWidth() / 2,
                dimension.height / 2 - component.getHeight() / 2
        );
    }

    private String getRandomString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 12);
    }
}
