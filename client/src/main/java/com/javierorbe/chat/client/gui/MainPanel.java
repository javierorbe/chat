package com.javierorbe.chat.client.gui;

import static java.awt.GridBagConstraints.REMAINDER;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class MainPanel extends JPanel {

    private final GridBagBuilder gbb = new GridBagBuilder();

    private final Consumer<String> msgCallback;
    private final JTextField msgField = new JTextField(30);
    private final JTextArea chatArea = new JTextArea(10, 6);

    MainPanel(Consumer<String> msgCallback) {
        super(new GridBagLayout());
        this.msgCallback = msgCallback;

        chatArea.setEditable(false);
        JScrollPane chatAreaScroll = new JScrollPane(chatArea);
        JButton sendBtn = new JButton("Send");

        gbb.setFillAndAnchor(GridBagBuilder.Fill.BOTH, GridBagBuilder.Anchor.WEST)
                .setInsets(4, 4, 4, 4);

        gbb.setGridWidthAndWeightX(REMAINDER, 1)
                .setWeightY(1);
        put(chatAreaScroll);
        gbb.setGridWidthAndWeightX(1, 1)
                .setWeightY(0);
        put(msgField);
        gbb.setGridWidthAndWeightX(REMAINDER, 0);
        put(sendBtn);

        sendBtn.addActionListener(e -> sendMessage());
        msgField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
    }

    private void put(Component component) {
        add(component, gbb.getConstraints());
    }

    void addChatLine(String text) {
        chatArea.append(text + "\n");
    }

    private void sendMessage() {
        msgCallback.accept(msgField.getText());
        msgField.setText("");
    }
}
